package com.example.cupcake

import android.app.Application
import com.example.cupcake.data.AppContainer
import com.example.cupcake.data.AppDataContainer

class CupcakeApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}