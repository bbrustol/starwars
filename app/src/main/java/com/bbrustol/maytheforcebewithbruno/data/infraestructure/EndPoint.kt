package com.bbrustol.maytheforcebewithbruno.data.infraestructure

import com.bbrustol.maytheforcebewithbruno.data.entity.StarwarsPeopleResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StarwarsListService {
    @GET("people/")
    fun fetchStarwarsPeopleListAsync(@Query("page") offset: Int, @Query("search") search: String): Deferred<Response<StarwarsPeopleResponse>>
}

interface AnalyticsService {
    @POST("content/")
    fun fetchAnalyticsAsync(@Query("content") offset: String): Deferred<Response<String>>
}