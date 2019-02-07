package com.oboenikui.overlayclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.oboenikui.overlayservice.IOverlay

class MainActivity : AppCompatActivity(), ServiceConnection {

    private var overlay: IOverlay? = null

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        overlay = IOverlay.Stub.asInterface(service)
        findViewById<Button>(R.id.show_button).also {
            it.isEnabled = true
            it.setOnClickListener { overlay?.show(window?.attributes) }
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        findViewById<Button>(R.id.show_button).isEnabled = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent()
                .setClassName("com.oboenikui.overlayservice", "com.oboenikui.overlayservice.OverlayService")
        bindService(intent, this, Context.BIND_AUTO_CREATE)
    }
}
