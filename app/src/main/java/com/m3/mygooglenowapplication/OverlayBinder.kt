package com.m3.mygooglenowapplication

import android.app.Dialog
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.view.WindowManager
import android.widget.TextView

class OverlayBinder(private val context: Context) : IOverlay.Stub() {
    override fun showNormally(params: WindowManager.LayoutParams?) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val textView = TextView(context)
        textView.text = "Hello, world!"
        textView.setTextColor(0xffffff)
        Log.d("OverlayBinder", "showNormally ${params?.flags}")
        windowManager.addView(textView, params?.also { it.flags = -2122252032 })
    }

    override fun show(layoutParams: WindowManager.LayoutParams) {
        val dialog = Dialog(context)
        val window = dialog.window ?: return
        window.addFlags(201326592)
        window.setWindowManager(
            null,
            layoutParams.token,
            ComponentName(context, context::class.java).flattenToShortString(),
            true
        )
        Log.d("OverlayBinder#show", layoutParams.flags.toString())
        window.attributes = layoutParams
        val textView = TextView(context)
        textView.text = "Hello, world!"
        window.setContentView(textView)
        window.windowManager.addView(window.decorView, window.attributes)
    }
}