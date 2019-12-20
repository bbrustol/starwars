package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.detail

import android.app.Application
import com.bbrustol.maytheforcebewithbruno.BuildConfig
import com.bbrustol.maytheforcebewithbruno.business.StarwarsBusiness
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.data.entity.ResultDetails
import com.bbrustol.maytheforcebewithbruno.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StarwarsPeopleDetailViewModel(
    private val starwarsBusiness: StarwarsBusiness,
    application: Application,
    private val coroutineScope: CoroutineScope) : BaseViewModel(application) {

    fun format(result: Result): ArrayList<ResultDetails> {

        val temp = arrayListOf<ResultDetails>()

        temp.add(ResultDetails("name", result.name))
        temp.add(ResultDetails("birthYear", result.birth_year))
        temp.add(ResultDetails("eyeColor", result.eye_color))
        temp.add(ResultDetails("gender", result.gender))
        temp.add(ResultDetails("hairColor", result.hair_color))
        temp.add(ResultDetails("height", result.height))
        temp.add(ResultDetails("homeworld", result.homeworld))
        temp.add(ResultDetails("mass", result.mass))
        temp.add(ResultDetails("skinColor", result.skin_color))

        analitycs()

        return temp
    }


    private fun analitycs() {

        coroutineScope.launch {
            starwarsBusiness.callAnalytics(BuildConfig.WEBHOOK_URL)
        }
    }


    override fun tryAgain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}