package com.akashapplications.editd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.akashapplications.editd.Utils.Constants;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.tedpark.tedpermission.rx2.TedRx2Permission;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropFragment;
import com.yalantis.ucrop.UCropFragmentCallback;

import org.firezenk.bubbleemitter.BubbleEmitterView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements UCropFragmentCallback {

    BubbleEmitterView bubbleEmitterView;
    private ArrayList<Image> galleryImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bubbleEmitterView = findViewById(R.id.bubbleEmitter);
        emitBubbles();
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        findViewById(R.id.cameraButton).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View view) {
                TedRx2Permission.with(MainActivity.this)
                        .setRationaleTitle("Can we access you camera?")
                        .setRationaleMessage("We need your permission to access your camera and click image")
                        .setPermissions(Manifest.permission.CAMERA)
                        .request()
                        .subscribe(permissionResult -> {
                                    if (permissionResult.isGranted()) {
                                        getImageFromCamera();
                                    } else {
                                        Toast.makeText(getBaseContext(),
                                                "Permission Denied\n" + permissionResult.getDeniedPermissions().toString(), Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }, throwable -> {
                                }
                        );
            }
        });

        findViewById(R.id.galleryButton).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View view) {
                TedRx2Permission.with(MainActivity.this)
                        .setRationaleTitle("Can we read your storage?")
                        .setRationaleMessage("We need your permission to access your storage and pick image")
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .request()
                        .subscribe(permissionResult -> {
                                    if (permissionResult.isGranted()) {
                                        getImageFromGallery();
                                    } else {
                                        Toast.makeText(getBaseContext(),
                                                "Permission Denied\n" + permissionResult.getDeniedPermissions().toString(), Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }, throwable -> {
                                }
                        );
            }
        });

        findViewById(R.id.movieButton).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View view) {
                TedRx2Permission.with(MainActivity.this)
                        .setRationaleTitle("Can we read your storage?")
                        .setRationaleMessage("We need your permission to access your storage and pick image")
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .request()
                        .subscribe(permissionResult -> {
                                    if (permissionResult.isGranted()) {
                                        startActivity(new Intent(getBaseContext(),MovieMaker.class));

                                    } else {
                                        Toast.makeText(getBaseContext(),
                                                "Permission Denied\n" + permissionResult.getDeniedPermissions().toString(), Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }, throwable -> {
                                }
                        );
            }
        });

    }

    private void getImageFromCamera() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, getResources().getString(R.string.file_provider_authorities), photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        888);
            }
        }

    }

    String imageFilePath;

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void getImageFromGallery() {
        ImagePicker.with(MainActivity.this)                         //  Initialize ImagePicker with activity or fragment context
                .setToolbarColor("#ffffff")         //  Toolbar color
                .setStatusBarColor("#ffffff")       //  StatusBar color (works with SDK >= 21  )
                .setToolbarTextColor("#4B4D4F")     //  Toolbar text color (Title and Done button)
                .setToolbarIconColor("#4B4D4F")     //  Toolbar icon color (Back and Camera button)
                .setProgressBarColor("#4CAF50")     //  ProgressBar color
                .setBackgroundColor("#ffffff")      //  Background color
                .setCameraOnly(false)               //  Camera mode
                .setMultipleMode(false)              //  Select multiple images or single image
                .setFolderMode(true)                //  Folder mode
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

    private void emitBubbles() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int size = new Random().nextInt(81) + 20;
                bubbleEmitterView.emitBubble(size);
                emitBubbles();
            }
        }, new Random().nextInt(500));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            galleryImageList = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            Image choosenImage = galleryImageList.get(0);
            Log.e(Constants.DEBUG_KEY, choosenImage.getPath());
            Uri imageUri = Uri.fromFile(new File(choosenImage.getPath()));
            if (imageUri != null)
                basicImageEdit(imageUri);
            else
                Toast.makeText(getBaseContext(), "Could not retrieve image", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
            basicImageEdit(Uri.fromFile(new File(imageFilePath)));
        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            handleCropResult(data);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            handleCropError(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void basicImageEdit(Uri imageUri) {
        Log.e(Constants.DEBUG_KEY, "basicImageEdit");

        String destinationFileName = Constants.BASIC_EDITED_IMAGE_NAME;
        UCrop uCrop = UCrop.of(imageUri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));

        uCrop = basisConfig(uCrop);
        uCrop = advancedConfig(uCrop);
        uCrop.start(MainActivity.this);

    }

    private UCrop basisConfig(@NonNull UCrop uCrop) {
        Log.e(Constants.DEBUG_KEY, "basicConfig");

        uCrop = uCrop.withAspectRatio(1,1);
        return uCrop;
    }

    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        Log.e(Constants.DEBUG_KEY, "advanceConfig");

        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.PNG);
        options.setCompressionQuality(100);
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(true);
        options.setShowCropFrame(true);
        options.setBrightnessEnabled(true);
        options.setContrastEnabled(true);
        options.setSaturationEnabled(true);
        options.setSharpnessEnabled(true);
        options.setShowCropGrid(true);

        return uCrop.withOptions(options);
    }

    @Override
    public void loadingProgress(boolean b) {
        Log.e(Constants.DEBUG_KEY, "loadingProgress : " + b);

    }

    @Override
    public void onCropFinish(UCropFragment.UCropResult result) {
        Log.e(Constants.DEBUG_KEY, "onCropFinish");
        switch (result.mResultCode) {
            case RESULT_OK:
                handleCropResult(result.mResultData);
                break;
            case UCrop.RESULT_ERROR:
                handleCropError(result.mResultData);
                break;
            default:
                Log.e(Constants.DEBUG_KEY, "DEFAULT CASE EXECUTED");
        }
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private void handleCropError(@NonNull Intent result) {
        Log.e(Constants.DEBUG_KEY, "handleCropError");

        final Throwable cropError = UCrop.getError(result);
        Log.e(Constants.DEBUG_KEY, "**** Error ****");
        if (cropError != null) {
            Log.e(Constants.DEBUG_KEY, "handleCropError: ", cropError);
            Toast.makeText(getBaseContext(), cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);
        Log.e(Constants.DEBUG_KEY, resultUri.toString());
        if (resultUri != null) {
//            CroppedResultActivity.startWithUri(MainActivity.this,resultUri);
            startActivity(new Intent(getBaseContext(), EditorMainActivity.class).putExtra("uri", resultUri.toString()));
        } else {
            Toast.makeText(getBaseContext(), "Could not retrieve edited image", Toast.LENGTH_SHORT).show();
        }
    }
}
