package com.oboenikui.mygooglenowapplication

import android.app.Dialog
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.widget.FrameLayout
import android.widget.TextView

class OverlayBinder(private val context: Context) : IOverlay.Stub() {

    private var mainThreadHandler = Handler(Looper.getMainLooper(), Handler.Callback { false })

    override fun show(layoutParams: WindowManager.LayoutParams) {
        mainThreadHandler.post {
            val dialog = Dialog(context)
            val window = dialog.window ?: return@post
            window.addFlags(FLAG_TRANSLUCENT_STATUS or FLAG_TRANSLUCENT_NAVIGATION)
            window.setWindowManager(
                    null,
                    layoutParams.token,
                    ComponentName(context, context::class.java).flattenToShortString(),
                    true
            )
            layoutParams.flags = layoutParams.flags or 8650752
            layoutParams.type = if (Build.VERSION.SDK_INT >= 25) TYPE_DRAWN_APPLICATION else TYPE_APPLICATION
            window.attributes = layoutParams
            window.clearFlags(FLAG_SHOW_WALLPAPER)
            val textView = TextView(context)
            textView.text = "Hello, overlay!"
            val frameLayout = FrameLayout(context).apply {
                addView(textView)
                fitsSystemWindows = true
            }
            window.setContentView(frameLayout)
            window.windowManager.addView(window.decorView, window.attributes)
        }
    }

}