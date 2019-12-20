package com.bbrustol.maytheforcebewithbruno.di

import com.bbrustol.maytheforcebewithbruno.business.StarwarsBusiness
import org.koin.dsl.module.module

val businessModule = module {

    factory { StarwarsBusiness(get()) }
}
