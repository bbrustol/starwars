package com.bbrustol.maytheforcebewithbruno.data.di

import com.bbrustol.maytheforcebewithbruno.data.infraestructure.ServiceFactory
import org.koin.dsl.module.module

val serviceModule = module(override = true) {
    factory { ServiceFactory(get("retrofit_starwars")).makeStarwarsListService() }

    factory { ServiceFactory(get("retrofit_analytics")).makeAnalyticsService() }
}
