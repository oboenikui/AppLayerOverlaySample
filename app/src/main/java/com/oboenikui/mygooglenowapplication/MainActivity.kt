package com.oboenikui.mygooglenowapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity(), ServiceConnection {

    private var overlay: IOverlay? = null

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
       overlay = IOverlay.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        overlay = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindService(Intent(this, OverlayService::class.java), this, Context.BIND_AUTO_CREATE)
        findViewById<Button>(R.id.show_button).setOnClickListener { overlay?.show(window?.attributes) }
    }
}
