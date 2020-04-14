package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.ActorMovies
import com.google.gson.annotations.SerializedName

internal data class ActorMoviesResponse(
    @SerializedName("cast")
    val cast: List<ActorMovieItem>
) {
    data class ActorMovieItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        val overview: String ?,
        @SerializedName("poster_path")
        val poster_path: String ?
    )
}

internal fun ActorMoviesResponse.ActorMovieItem.toMovie() = ActorMovies(
    id = id,
    title = title,
    overview = overview ?: "",
    poster_path = poster_path ?: ""
)