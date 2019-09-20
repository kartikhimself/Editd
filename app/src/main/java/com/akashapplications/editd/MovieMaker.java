package com.akashapplications.editd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.akashapplications.editd.Utils.Constants;
import com.akashapplications.editd.widget.FilterItem;
import com.akashapplications.editd.widget.MovieBottomView;
import com.akashapplications.editd.widget.MovieFilterView;
import com.akashapplications.editd.widget.MovieTransferView;
import com.akashapplications.editd.widget.TransferItem;
import com.hw.photomovie.render.GLTextureView;
import com.hw.photomovie.util.AppResources;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;

public class MovieMaker extends AppCompatActivity implements IDemoView, MovieBottomView.MovieBottomCallback {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final int REQUEST_MUSIC = 234;
    private DemoPresenter mDemoPresenter = new DemoPresenter(MovieMaker.this);
    private GLTextureView mGLTextureView;
    private MovieFilterView mFilterView;
    private MovieTransferView mTransferView;
    private MovieBottomView mBottomView;
    private View mSelectView;
    private List<FilterItem> mFilters;
    private List<TransferItem> mTransfers;
    private View mFloatAddView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppResources.getInstance().init(getResources());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_maker);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mGLTextureView = findViewById(R.id.gl_texture);
        mBottomView = findViewById(R.id.movie_bottom_layout);
        mSelectView = findViewById(R.id.movie_add);
        mFloatAddView = findViewById(R.id.movie_add_float);
        mDemoPresenter.attachView(this);
        mBottomView.setCallback(this);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPhotos();
            }
        };
        mSelectView.setOnClickListener(onClickListener);
        mFloatAddView.setOnClickListener(onClickListener);


    }

    private ArrayList<Image> galleryImageList = new ArrayList<>();

    private void requestPhotos() {

        ImagePicker.with(MovieMaker.this)                         //  Initialize ImagePicker with activity or fragment context
                .setToolbarColor("#ffffff")         //  Toolbar color
                .setStatusBarColor("#ffffff")       //  StatusBar color (works with SDK >= 21  )
                .setToolbarTextColor("#4B4D4F")     //  Toolbar text color (Title and Done button)
                .setToolbarIconColor("#4B4D4F")     //  Toolbar icon color (Back and Camera button)
                .setProgressBarColor("#4CAF50")     //  ProgressBar color
                .setBackgroundColor("#ffffff")      //  Background color
                .setCameraOnly(false)               //  Camera mode
                .setMultipleMode(true)              //  Select multiple images or single image
                .setFolderMode(false)                //  Folder mode
                .setShowCamera(false)                //  Show camera button
                .setFolderTitle("Select Image")           //  Folder title (works with FolderMode = true)
                .setImageTitle("Select Image")         //  Image title (works with FolderMode = false)
                .setDoneTitle("EDIT")               //  Done button title
                .setLimitMessage("You have reached selection limit")    // Selection limit message
                .setMaxSize(10)                     //  Max images can be selected
                .setSavePath("Editd")         //  Image capture folder name
                .setSelectedImages(galleryImageList)          //  Selected images
                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
                .setRequestCode(100)                //  Set request code, default Config.RC_PICK_IMAGES
                .setKeepScreenOn(true)              //  Keep screen on when selecting images
                .start();




    }

    @Override
    public GLTextureView getGLView() {
        return mGLTextureView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDemoPresenter.detachView();
    }

    private boolean checkInit() {
        if (mSelectView.getVisibility() == View.VISIBLE) {
            Toast.makeText(this, "please select photos", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    @Override
    public void onNextClick() {
        if (checkInit()) {
            return;
        }
        mDemoPresenter.saveVideo();
    }

    @Override
    public void onMusicClick() {
        if (checkInit()) {
            return;
        }
        Intent i = new Intent();
        i.setType("audio/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, REQUEST_MUSIC);
    }

    @Override
    public void onTransferClick() {
        if (checkInit()) {
            return;
        }
        if (mTransferView == null) {
            ViewStub stub = findViewById(R.id.movie_menu_transfer_stub);
            mTransferView = (MovieTransferView) stub.inflate();
            mTransferView.setVisibility(View.GONE);
            mTransferView.setItemList(mTransfers);
            mTransferView.setTransferCallback(mDemoPresenter);
        }
        mBottomView.setVisibility(View.GONE);
        mTransferView.show();
    }

    @Override
    public void onFilterClick() {
        if (checkInit()) {
            return;
        }
        if (mFilterView == null) {
            ViewStub stub = findViewById(R.id.movie_menu_filter_stub);
            mFilterView = (MovieFilterView) stub.inflate();
            mFilterView.setVisibility(View.GONE);
            mFilterView.setItemList(mFilters);
            mFilterView.setFilterCallback(mDemoPresenter);
        }
        mBottomView.setVisibility(View.GONE);
        mFilterView.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_MUSIC) {
            Uri uri = data.getData();
            mDemoPresenter.setMusic(uri);
        } else
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            galleryImageList = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            if(galleryImageList.size() == 0)
                return;
            ArrayList<String> photos = new ArrayList<>();
            for(Image image : galleryImageList)
                photos.add(image.getPath());
            mDemoPresenter.onPhotoPick(photos);
            mFloatAddView.setVisibility(View.VISIBLE);
            mSelectView.setVisibility(View.GONE);
            photos.clear();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (mFilterView != null && mFilterView.getVisibility() == View.VISIBLE
                    && !checkInArea(mFilterView, ev)) {
                mFilterView.hide();
                mBottomView.setVisibility(View.VISIBLE);
                return true;
            } else if (mTransferView != null && mTransferView.getVisibility() == View.VISIBLE
                    && !checkInArea(mTransferView, ev)) {
                mTransferView.hide();
                mBottomView.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean checkInArea(View view, MotionEvent event) {
        int loc[] = new int[2];
        view.getLocationInWindow(loc);
        return event.getRawY() > loc[1];
    }

    @Override
    public void setFilters(List<FilterItem> filters) {
        mFilters = filters;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setTransfers(List<TransferItem> items) {
        mTransfers = items;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDemoPresenter.onPause();
        mGLTextureView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDemoPresenter.onResume();
        mGLTextureView.onResume();
    }
}

