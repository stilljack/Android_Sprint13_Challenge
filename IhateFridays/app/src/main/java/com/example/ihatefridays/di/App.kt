package com.example.ihatefridays.di

import android.app.Application



class App:Application() {

        val appComponent by lazy {
                DaggerAppComponent
                        .builder()
                        .bindApplication(this)
        }

        init {
            appComponent
        }

}