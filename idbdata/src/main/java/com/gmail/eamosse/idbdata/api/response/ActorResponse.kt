package com.gmail.eamosse.idbdata.api.response


import com.gmail.eamosse.idbdata.data.Actor
import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("deathday")
    val deathday: Any,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("homepage")
    val homepage: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)

internal fun ActorResponse.toActor() = Actor(
    adult, alsoKnownAs, biography, birthday, deathday, gender, homepage, id, imdbId, knownForDepartment, name, placeOfBirth, popularity, profilePath
)