package app.estat.mob.mvp.model.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ImageManager {

    private static final String TAG = ImageManager.class.getName();

    public static final int USER_IMAGE = 0;

    public static final int FARM_IMAGE = 1;

    private static final float MAX_USER_IMAGE_SIZE = 768;

    private static final int IMAGE_SAVE_QUALITY = 75;

    private static final String USER_IMAGE_NAME = "user_image.jpg";

    private static final String FARM_IMAGE_NAME = "farm_image.jpg";

    private static final String AUTHORITY = "app.estat.mob.fileprovider";

    private String mUserImageTempPath;

    private Uri mUserImageTempUri;

    private String mFarmImageTempPath;

    private Uri mFarmImageTempUri;

    public boolean isFarmImageExists(Context context) {
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), FARM_IMAGE_NAME).exists();
    }

    public boolean isUserImageExists(Context context) {
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), USER_IMAGE_NAME).exists();
    }

    public void copyTempImage(Context context, int imageType) throws IOException {
        File mainDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (imageType == USER_IMAGE) {
            if (mUserImageTempPath == null || mUserImageTempPath.isEmpty()) {
                return;
            }
            copyImage(new File(mUserImageTempPath), new File(mainDirectory, USER_IMAGE_NAME));
        } else if (imageType == FARM_IMAGE) {
            if (mFarmImageTempPath == null || mFarmImageTempPath.isEmpty()) {
                return;
            }
            copyImage(new File(mFarmImageTempPath), new File(mainDirectory, FARM_IMAGE_NAME));
        }
    }

    private void copyImage(File tempImage, File destImage) throws IOException {
        FileChannel tempChannel = new FileInputStream(tempImage).getChannel();
        FileChannel destChannel = new FileOutputStream(destImage).getChannel();
        destChannel.transferFrom(tempChannel, 0, tempChannel.size());
        tempChannel.close();
        destChannel.close();
    }

    public void scaleTempImage(Context context, int imageType) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),
                imageType == USER_IMAGE ? mUserImageTempUri :
                        (imageType == FARM_IMAGE) ? mFarmImageTempUri : null);
        float ratio = Math.min(MAX_USER_IMAGE_SIZE / bitmap.getWidth(),
                MAX_USER_IMAGE_SIZE / bitmap.getHeight());
        int width = Math.round(ratio * bitmap.getWidth());
        int height = Math.round(ratio * bitmap.getHeight());
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

        FileOutputStream fileOutputStream = new FileOutputStream(imageType == USER_IMAGE ?
                mUserImageTempPath : (imageType == FARM_IMAGE) ? mFarmImageTempPath : "");
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, IMAGE_SAVE_QUALITY, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public Uri createImageTemp(Context context, int imageType) throws IOException {
        if (ImageManager.USER_IMAGE == imageType) {
            File imageTempFile = File.createTempFile(USER_IMAGE_NAME, null,
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            mUserImageTempPath = imageTempFile.getAbsolutePath();
            mUserImageTempUri = FileProvider.getUriForFile(context, AUTHORITY, imageTempFile);

            return mUserImageTempUri;
        } else if (ImageManager.FARM_IMAGE == imageType) {
            File imageTempFile = File.createTempFile(FARM_IMAGE_NAME, null,
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            mFarmImageTempPath = imageTempFile.getAbsolutePath();
            mFarmImageTempUri = FileProvider.getUriForFile(context, AUTHORITY, imageTempFile);

            return mFarmImageTempUri;
        }

        Log.e(TAG, "unknown image type");
        return null;
    }

    public Uri getUserImageTempUri() {
        return mUserImageTempUri;
    }

    public Uri getFarmImageTempUri() {
        return mFarmImageTempUri;
    }

    public Uri getUserImageUri(Context context) {
        return getImageUri(context, USER_IMAGE);
    }

    public Uri getFarmImageUri(Context context) {
        return getImageUri(context, FARM_IMAGE);
    }

    private Uri getImageUri(Context context, int imageType) {
        File directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;

        if (imageType == USER_IMAGE) {
            image = new File(directory, USER_IMAGE_NAME);
        } else if (imageType == FARM_IMAGE) {
            image = new File(directory, FARM_IMAGE_NAME);
        }

        return FileProvider.getUriForFile(context, AUTHORITY, image);
    }
}
