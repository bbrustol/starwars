package com.bbrustol.maytheforcebewithbruno.data.infraestructure

import retrofit2.Retrofit

class ServiceFactory(private val retrofit: Retrofit){

    fun makeStarwarsListService(): StarwarsListService = retrofit.create(StarwarsListService::class.java)
}