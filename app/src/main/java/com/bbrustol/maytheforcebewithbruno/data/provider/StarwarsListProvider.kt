package com.bbrustol.maytheforcebewithbruno.data.provider

import com.bbrustol.maytheforcebewithbruno.data.infraestructure.AnalyticsService
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.StarwarsListService
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.callAsync

class StarwarsListProvider(private val service: StarwarsListService, private val serviceAnalytics: AnalyticsService) {

    suspend fun fetchStarwarsPeopleList(page: Int, search: String) = callAsync { service.fetchStarwarsPeopleListAsync(page, search) }.await()

    suspend fun callAnalytics(webhookUrl: String) = callAsync { serviceAnalytics.fetchAnalyticsAsync(webhookUrl) }.await()

}