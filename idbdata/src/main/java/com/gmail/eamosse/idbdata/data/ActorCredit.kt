package com.gmail.eamosse.idbdata.data

import com.google.gson.annotations.SerializedName

class ActorCredit (
    val adult: Boolean,
    val backdropPath: String,
    val character: String,
    val creditId: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Int,
    val voteCount: Int
)