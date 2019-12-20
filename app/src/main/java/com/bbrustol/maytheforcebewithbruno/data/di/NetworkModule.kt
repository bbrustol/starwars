package com.bbrustol.maytheforcebewithbruno.data.di

import com.bbrustol.maytheforcebewithbruno.data.infraestructure.RetrofitFactory
import org.koin.dsl.module.module
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module(override = true) {
    factory {
        RetrofitFactory.makeServiceBuilder(get(), MoshiConverterFactory.create())
    }
}


