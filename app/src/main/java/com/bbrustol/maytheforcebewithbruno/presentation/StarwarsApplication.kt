package com.bbrustol.maytheforcebewithbruno.presentation

import android.app.Application
import com.bbrustol.maytheforcebewithbruno.data.di.*
import com.bbrustol.maytheforcebewithbruno.di.businessModule
import com.bbrustol.maytheforcebewithbruno.di.viewModelModule
import org.koin.android.ext.android.startKoin


class StarwarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                viewModelModule,
                businessModule,
                provideModule,
                serviceModule,
                networkModule,
                urlModule,
                systemModule
            )
        )
    }
}