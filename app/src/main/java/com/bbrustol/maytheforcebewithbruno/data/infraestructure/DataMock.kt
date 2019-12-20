package com.bbrustol.maytheforcebewithbruno.data.infraestructure

import com.bbrustol.maytheforcebewithbruno.data.entity.Result
import com.bbrustol.maytheforcebewithbruno.data.entity.StarwarsPeopleResponse

class DataMock {

    companion object {
        fun createList() = StarwarsPeopleResponse(
            0,
            "",
            "",
            listOf(emptyResultModel())
        )

        fun emptyResultModel() = Result(
            "birthYear",
            "created",
            "edited",
            "eyeColor",
            emptyList(),
            "gender",
            "hairColor",
            "height",
            "homeworld",
            "mass",
            "name",
            "skinColor",
            emptyList(),
            emptyList(),
            "url",
            emptyList()
        )

        const val ILLEGAL_ARGUMENT : String = "Illegal argument"
    }
}