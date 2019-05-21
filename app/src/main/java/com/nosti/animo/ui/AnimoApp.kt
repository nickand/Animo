package com.nosti.animo.ui

import android.app.Application
import android.content.Context
import com.nosti.animo.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class AnimoApp : Application() {

    private var context: Context = applicationContext

    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CircularStd-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}