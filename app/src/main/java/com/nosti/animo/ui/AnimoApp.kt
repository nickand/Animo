package com.nosti.animo.ui

import android.app.Application
import com.nosti.animo.R
import com.nosti.animo.initDI
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class AnimoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CircularStd-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        initDI()
    }
}