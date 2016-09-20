package app.estat.mob.mvp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageManager {
    private static final float MAX_USER_IMAGE_SIZE = 512;

    private static final int IMAGE_SAVE_QUALITY = 85;

    private static final String FILE_NAME = "user_image.jpg";

    private static final String AUTHORITY = "app.estat.mob.fileprovider";

    private static final String TAG = ImageManager.class.getName();

    public boolean isUserImageExists(Context context) {
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), FILE_NAME).exists();
    }

    public void scaleUserImage(Context context, Uri imageUri) {
        FileOutputStream fileOutputStream = null;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            float ratio = Math.min(MAX_USER_IMAGE_SIZE / bitmap.getWidth(), MAX_USER_IMAGE_SIZE / bitmap.getHeight());
            int width = Math.round(ratio * bitmap.getWidth());
            int height = Math.round(ratio * bitmap.getHeight());
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

            File image = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), FILE_NAME);
            fileOutputStream = new FileOutputStream(image);
            scaledBitmap.compress(Bitmap.CompressFormat.PNG, IMAGE_SAVE_QUALITY, fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            Log.w(TAG, "Unable to scale image", e);
        }  finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.w(TAG, "Unable to close stream", e);
                }
            }
        }
    }

    public Uri getUserImageUri(Context context) {
        File directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(directory, FILE_NAME);

        try {
            if (image.createNewFile()) {
                Log.d(TAG, "New empty file created [" + image.getAbsolutePath() + "]");
            }
        } catch (IOException e) {
            Log.w(TAG, "Unable to create file for photo", e);
        }

        return FileProvider.getUriForFile(context, AUTHORITY, image);
    }
}
