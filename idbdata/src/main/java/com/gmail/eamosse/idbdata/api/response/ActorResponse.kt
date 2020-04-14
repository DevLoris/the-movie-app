package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Actor
import com.google.gson.annotations.SerializedName

internal data class ActorResponse(
    @SerializedName("cast")
    val results: List<ActorItem>
) {
    data class ActorItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("profile_path")
        val profile_path: String ?,
        @SerializedName("birthday")
        val birthday: String ?,
        @SerializedName("place_of_birth")
        val place_of_birth: String ?,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("biography")
        val biography: String ?
    )
}

internal fun ActorResponse.ActorItem.toActor() = Actor(
    id = id,
    name = name,
    profile_path = profile_path ?: "",
    birthday = birthday ?: "Unknown",
    place_of_birth = place_of_birth ?: "Unknown",
    popularity = (popularity * 100 / 20).toInt(),
    biography = biography ?: "Nothing to say"

)