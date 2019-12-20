package com.bbrustol.maytheforcebewithbruno.data.entity
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class StarwarsPeopleResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String,
    @Json(name = "previous")
    val previous: String,
    @Json(name = "results")
    val results: List<Result>
)

@Parcelize
data class Result(
    @Json(name = "birth_year")
    val birth_year: String,
    @Json(name = "created")
    val created: String,
    @Json(name = "edited")
    val edited: String,
    @Json(name = "eye_color")
    val eye_color: String,
    @Json(name = "films")
    val films: List<String>,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "hair_color")
    val hair_color: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "homeworld")
    val homeworld: String,
    @Json(name = "mass")
    val mass: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "skin_color")
    val skin_color: String,
    @Json(name = "species")
    val species: List<String>,
    @Json(name = "starships")
    val starships: List<String>,
    @Json(name = "url")
    val url: String,
    @Json(name = "vehicles")
    val vehicles: List<String>
): Parcelable

data class ResultDetails(
    val key: String,
    val value: String)