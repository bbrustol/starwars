package com.bbrustol.maytheforcebewithbruno.data.di

import com.bbrustol.maytheforcebewithbruno.BuildConfig
import org.koin.dsl.module.module

val urlModule = module {
    factory { BuildConfig.BASE_URL }
}