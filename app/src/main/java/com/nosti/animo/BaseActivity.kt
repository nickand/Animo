package com.nosti.animo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkNetworkConnection()
    }

    protected fun checkNetworkConnection() {
        ReactiveNetwork.observeNetworkConnectivity(this)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { connectivity ->
                if (!connectivity.isAvailable) {
                    SuperActivityToast.create(this@BaseActivity, Style(), Style.TYPE_STANDARD)
                        .setText(getString(R.string.error_network))
                        .setDuration(Style.DURATION_LONG)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                        .setAnimations(Style.ANIMATIONS_POP).show()
                }
            }
    }
}