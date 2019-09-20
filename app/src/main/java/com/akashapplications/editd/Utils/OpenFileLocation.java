package com.akashapplications.editd.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.akashapplications.editd.R;

import java.io.File;

public class OpenFileLocation {
    Context context;

    public OpenFileLocation(Context context) {
        this.context = context;
    }

    public void openAdvanceEdit()
    {
        File pictureFolder = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
        );
        File imagesFolder = new File(pictureFolder, "Editd");
        if (imagesFolder.exists()) {
            Toast.makeText(context, "File location not found", Toast.LENGTH_SHORT).show();
            return;
        }

        File imagesSubFolder = new File(imagesFolder, "AdvanceEditd");
        if (!imagesSubFolder.exists()) {
            Toast.makeText(context, "File location not found", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uri = FileProvider.getUriForFile(context, context.getResources().getString(R.string.file_provider_authorities), imagesSubFolder);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "resource/folder");
        context.startActivity(intent);

    }
}
