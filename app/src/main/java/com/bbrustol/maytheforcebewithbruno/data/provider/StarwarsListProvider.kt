package com.bbrustol.maytheforcebewithbruno.data.provider

import android.util.Log
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.StarwarsListService
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.callAsync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

class StarwarsListProvider(private val service: StarwarsListService) {

    companion object {
        private val TAG = this::class.java.simpleName
    }

    suspend fun fetchStarwarsPeopleList(page: Int) = callAsync { service.fetchStarwarsPeopleListAsync(page) }.await()

    suspend fun callAnalytics(webhookUrl: String) = withContext(Dispatchers.IO) {
        try {
            URL(webhookUrl).readText()
        } catch (e: IOException) {
            Log.e(TAG," msg - ${e.message}")
            return@withContext e.message ?: ""
        }
    }

}