package com.nosti.animo.ui

import android.app.Application
import androidx.room.Room
import com.nosti.animo.R
import com.nosti.animo.model.database.AnimeDatabase
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class AnimoApp : Application() {

    lateinit var db: AnimeDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CircularStd-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        db = Room.databaseBuilder(
            this,
            AnimeDatabase::class.java, "anime-db"
        ).build()
    }
}