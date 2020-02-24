package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.SimilarMovie
import com.gmail.eamosse.idbdata.data.Token
import com.google.gson.annotations.SerializedName

internal data class SimilarMoviesResponse(
    @SerializedName("results")
    val movies: List<Similar>
) {
    data class Similar(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val poster_path: String?
    )
}


internal fun SimilarMoviesResponse.Similar.toSimilarMovie() = SimilarMovie(
    id = id,
    title = title,
    poster_path = poster_path,
    overview = overview
)
