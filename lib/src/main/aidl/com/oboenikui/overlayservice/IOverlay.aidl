// IOverlay.aidl
package com.oboenikui.overlayservice;

// Declare any non-default types here with import statements
import android.view.WindowManager.LayoutParams;

interface IOverlay {
    void show(in LayoutParams params);
}
