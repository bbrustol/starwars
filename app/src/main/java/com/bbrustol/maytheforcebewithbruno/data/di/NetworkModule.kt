package com.bbrustol.maytheforcebewithbruno.data.di

import com.bbrustol.maytheforcebewithbruno.BuildConfig
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.RetrofitFactory
import org.koin.dsl.module.module
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module(override = true) {
    single("retrofit_starwars") {
        RetrofitFactory.makeServiceBuilder(BuildConfig.BASE_URL, MoshiConverterFactory.create())
    }

    single("retrofit_analytics") {
        RetrofitFactory.makeServiceBuilder(BuildConfig.WEBHOOK_URL, MoshiConverterFactory.create())
    }
}