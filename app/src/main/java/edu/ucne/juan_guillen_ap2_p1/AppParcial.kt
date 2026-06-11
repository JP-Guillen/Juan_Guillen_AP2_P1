package edu.ucne.juan_guillen_ap2_p1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppParcial : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}