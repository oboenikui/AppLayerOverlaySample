package com.m3.mygooglenowapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.ContextThemeWrapper

class OverlayService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return OverlayBinder(ContextThemeWrapper(this, R.style.AppTheme))
    }

}