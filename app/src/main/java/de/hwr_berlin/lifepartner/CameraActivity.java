package de.hwr_berlin.lifepartner;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import java.io.IOException;


public class CameraActivity extends LifePartnerActivity {

    private Camera mCamera;
    private CameraPreview mPreview;

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCameraState();
        releaseCamera();
    }

    /*
     * CAMERA Stuff
     * http://developer.android.com/guide/topics/media/camera.html
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private void saveCameraState() {
        if (mCamera != null) {
            SharedPreferences settings = getSharedPreferences(PREFS, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(PREF_CAMERA_ZOOM, mCamera.getParameters().getZoom());
            editor.commit();
        }
    }

    public void zoomIn(View view) {
        Camera.Parameters p = mCamera.getParameters();
        if (p.getZoom() < p.getMaxZoom()) {
            p.setZoom(p.getZoom() + 10);
        }
        mCamera.setParameters(p);
    }

    public void zoomOut(View view) {
        Camera.Parameters p = mCamera.getParameters();
        if (p.getZoom() > 10) {
            p.setZoom(p.getZoom() - 10);
        }
        mCamera.setParameters(p);
    }
}

/**
 * A basic Camera preview class
 */
class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("error", "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        mCamera.setDisplayOrientation(90);

        Camera.Parameters p = mCamera.getParameters();
        SharedPreferences settings = getContext().getSharedPreferences(LifePartnerActivity.PREFS, 0);
        p.setZoom(settings.getInt(LifePartnerActivity.PREF_CAMERA_ZOOM, p.getMaxZoom() / 2));
        mCamera.setParameters(p);

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e) {
            Log.d("error", "Error starting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }
}
