package com.whg.firstopenglproject;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class FirstOpenGLProjectActivity extends AppCompatActivity {

    private boolean rendererSet = false;
    private GLSurfaceView glSurfaceView;


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);

        if (isCheckSupprotsEs2()) {
            //配置surface试图
            glSurfaceView.setEGLContextClientVersion(2);
            glSurfaceView.setRenderer(new FirstOpenGLProjectRenderer());
            rendererSet = true;
        } else {
            Toast.makeText(this, "This device dose not support OpenGL ES 2.0", Toast.LENGTH_SHORT).show();
            return;
        }
        setContentView(glSurfaceView);
    }

    /**
     * 检查系统是否支持opengl es 2.0
     *
     * @return
     */
    private boolean isCheckSupprotsEs2() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
        return deviceConfigurationInfo.reqGlEsVersion >= 0x20000
                || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                | Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK build for x86")));
    }


    //处理Android的生命周期,及时释放OpenGl 上下文
    @Override
    protected void onPause() {
        super.onPause();
        if (rendererSet) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rendererSet) {
            glSurfaceView.onResume();
        }
    }
}
