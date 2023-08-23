package com.giseys.assessment

import android.app.Application
import android.content.Context
import java.math.MathContext

class BillzApp : Application(){
    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}