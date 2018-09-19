package com.whg.firstopenglproject;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES10.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES10.glClear;
import static android.opengl.GLES10.glClearColor;
import static android.opengl.GLES10.glViewport;

public class FirstOpenGLProjectRenderer implements GLSurfaceView.Renderer {

    /**
     * surface被创建
     *
     * @param gl
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //清空屏幕用的颜色
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * surface尺寸发生变化
     *
     * @param gl
     * @param width
     * @param height
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置视口尺寸
        glViewport(0, 0, width, height);

    }

    /**
     * 每一帧渲染和清空操作
     *
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        //清空屏幕
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
