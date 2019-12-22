package com.bbrustol.maytheforcebewithbruno.data.di

import com.bbrustol.maytheforcebewithbruno.data.provider.StarwarsListProvider
import org.koin.dsl.module.module

val provideModule = module(override = true) {
    factory { StarwarsListProvider(get(), get()) }
}