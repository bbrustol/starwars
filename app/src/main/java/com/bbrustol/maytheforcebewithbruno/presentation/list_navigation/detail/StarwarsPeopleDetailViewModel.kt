package com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.detail

import android.app.Application
import com.bbrustol.maytheforcebewithbruno.BuildConfig
import com.bbrustol.maytheforcebewithbruno.business.StarwarsBusiness
import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.data.entity.ResultDetails
import com.bbrustol.maytheforcebewithbruno.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StarwarsPeopleDetailViewModel(application: Application) : BaseViewModel(application) {

    fun format(result: Result): ArrayList<ResultDetails> {

        val arResultDetails= arrayListOf<ResultDetails>()

        if (!result.name.isNullOrBlank()) { arResultDetails.add(ResultDetails("name", result.name)) }
        if (!result.birth_year.isNullOrBlank()) { arResultDetails.add(ResultDetails("birthYear", result.birth_year)) }
        if (!result.eye_color.isNullOrBlank()) { arResultDetails.add(ResultDetails("eyeColor", result.eye_color)) }
        if (!result.gender.isNullOrBlank()) {  arResultDetails.add(ResultDetails("gender", result.gender)) }
        if (!result.hair_color.isNullOrBlank()) { arResultDetails.add(ResultDetails("hairColor", result.hair_color)) }
        if (!result.height.isNullOrBlank()) { arResultDetails.add(ResultDetails("height", result.height)) }
        if (!result.homeworld.isNullOrBlank()) { arResultDetails.add(ResultDetails("homeworld", result.homeworld)) }
        if (!result.mass.isNullOrBlank()) { arResultDetails.add(ResultDetails("mass", result.mass)) }
        if (!result.skin_color.isNullOrBlank()) { arResultDetails.add(ResultDetails("skinColor", result.skin_color)) }

        return arResultDetails
    }


    override fun tryAgain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}