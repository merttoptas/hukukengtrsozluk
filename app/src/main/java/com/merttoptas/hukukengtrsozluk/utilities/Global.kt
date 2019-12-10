package com.merttoptas.hukukengtrsozluk.utilities

import android.app.Application
import android.content.Context

class Global: Application() {
    init {
        instance = this
    }

    companion object{

        lateinit var appContext : Context
        var instance : Global? =null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext()
    }
}