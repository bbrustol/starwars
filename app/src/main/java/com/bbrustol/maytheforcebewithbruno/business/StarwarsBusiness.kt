package com.bbrustol.maytheforcebewithbruno.business

import com.bbrustol.maytheforcebewithbruno.data.entity.StarwarsPeopleResponse
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.Failure
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.Resource
import com.bbrustol.maytheforcebewithbruno.data.infraestructure.Success
import com.bbrustol.maytheforcebewithbruno.data.provider.StarwarsListProvider

class StarwarsBusiness(private val starwarsPeopleListProvider: StarwarsListProvider) {

    suspend fun fetchStarwarswPeoplelist(page: Int): Resource<StarwarsPeopleResponse> {
        return when (val response: Resource<StarwarsPeopleResponse> = starwarsPeopleListProvider.fetchStarwarsPeopleList(page)) {
            is Success -> Success(response.data)
            is Failure -> Failure(response.data, response.networkState)
        }
    }

    suspend fun callAnalytics(webhookUrl: String): String {
        return starwarsPeopleListProvider.callAnalytics(webhookUrl)
    }
}