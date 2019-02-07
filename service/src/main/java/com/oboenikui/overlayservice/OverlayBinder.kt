package com.oboenikui.overlayservice

import android.app.Dialog
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.widget.Button

class OverlayBinder(private val context: Context) : IOverlay.Stub() {

    private var mainThreadHandler = Handler(Looper.getMainLooper(), Handler.Callback { false })

    override fun show(layoutParams: WindowManager.LayoutParams) {
        mainThreadHandler.post {
            // TODO: reuse window instance
            val window = Dialog(context, R.style.Theme_Overlay).window ?: return@post

            window.addFlags(FLAG_TRANSLUCENT_STATUS or FLAG_TRANSLUCENT_NAVIGATION)
            window.setWindowManager(
                    null,
                    layoutParams.token,
                    ComponentName(context, context::class.java).flattenToShortString(),
                    true
            )
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.flags = layoutParams.flags or FLAG_WATCH_OUTSIDE_TOUCH or FLAG_SPLIT_TOUCH
            layoutParams.type = if (Build.VERSION.SDK_INT >= 25) TYPE_DRAWN_APPLICATION else TYPE_APPLICATION
            window.attributes = layoutParams
            window.clearFlags(FLAG_SHOW_WALLPAPER)
            window.setContentView(R.layout.overlay)
            window.findViewById<Button>(R.id.close_button).setOnClickListener {
                window.windowManager.removeView(window.decorView)
            }
            window.windowManager.addView(window.decorView, window.attributes)
        }
    }

}