package com.akashapplications.editd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.akashapplications.editd.Utils.Constants;
import com.github.nikartm.button.FitButton;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class EditorMainActivity extends AppCompatActivity {

    PhotoEditorView editorView;
    FitButton saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_main);

        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Uri sourceImage = Uri.parse(getIntent().getStringExtra("uri"));
        editorView = findViewById(R.id.photoEditorView);
        editorView.getSource().setImageURI(sourceImage);
        saveBtn = findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savefile(sourceImage, false);
            }
        });

        findViewById(R.id.advanceEditButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AdvanceEdit.class).putExtra("uri", sourceImage.toString()));
                finish();
            }
        });

        findViewById(R.id.shareButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savefile(sourceImage, true);
            }
        });
    }

    void savefile(Uri sourceuri, boolean isShare)
    {
        String sourceFilename= sourceuri.getPath();
        File pictureFolder = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
        );
        File imagesFolder = new File(pictureFolder, "Editd");
        if (!imagesFolder.exists()) {
            imagesFolder.mkdirs();
        }


        String destinationFilename = imagesFolder.getAbsolutePath() + File.separatorChar+"Editd_"+System.currentTimeMillis()+".png";

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while(bis.read(buf) != -1);
            Log.e(Constants.DEBUG_KEY,"sourceFilename : "+sourceFilename);
            Log.e(Constants.DEBUG_KEY,"Saved : "+destinationFilename);
            saveBtn.setEnabled(false);
            Toast.makeText(getBaseContext(),"Image saved", Toast.LENGTH_SHORT).show();
            if(isShare)
            {
                final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                final File photoFile = new File(destinationFilename);
                shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this, getResources().getString(R.string.file_provider_authorities), photoFile));
                startActivity(Intent.createChooser(shareIntent, "Share image using"));
            }
            else
            {
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(Constants.DEBUG_KEY,e.toString());
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(Constants.DEBUG_KEY,e.toString());
            }
        }
    }
}
