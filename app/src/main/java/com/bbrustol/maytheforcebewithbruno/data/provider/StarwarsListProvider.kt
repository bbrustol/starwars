package com.bbrustol.maytheforcebewithbruno.data.provider

import com.bbrustol.maytheforcebewithbruno.data.infraestructure.StarwarsListService
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.callAsync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class StarwarsListProvider(private val service: StarwarsListService) {

    suspend fun fetchStarwarsPeopleList(page: Int) = callAsync { service.fetchStarwarsPeopleListAsync(page) }.await()

    suspend fun callAnalytics(webhookUrl: String) = withContext(Dispatchers.IO) { URL(webhookUrl).readText() }

}