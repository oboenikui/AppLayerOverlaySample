// IOverlay.aidl
package com.m3.mygooglenowapplication;

// Declare any non-default types here with import statements
import android.view.WindowManager.LayoutParams;

interface IOverlay {
    void show(in LayoutParams params);

    void showNormally(in LayoutParams params);
}
