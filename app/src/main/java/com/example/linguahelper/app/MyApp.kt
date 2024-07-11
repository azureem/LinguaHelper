package com.example.linguahelper.app

import android.app.Application
import com.example.linguahelper.data.source.AppDatabase
import com.example.linguahelper.domain.AppRepository
import com.example.linguahelper.domain.AppRepositoryImpl

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        AppRepositoryImpl.init()
    }
}