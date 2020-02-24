package com.gmail.eamosse.idbdata.data

data class Movie(
    val id: Int,
    val budget: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)