package com.oboenikui.mygooglenowapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ServiceConnection {

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
       IOverlay.Stub.asInterface(service).show(window?.attributes)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindService(Intent(this, OverlayService::class.java), this, Context.BIND_AUTO_CREATE)
    }
}
