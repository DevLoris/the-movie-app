package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.eamosse.idbdata.api.response.DiscoverResponse
import com.gmail.eamosse.idbdata.data.Discover

@Entity(
    tableName = "favorite_movie"
)
data class FavoriteMovie(
    @PrimaryKey
    val id: Int,
    val budget: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)


fun FavoriteMovie.toDiscover() = Discover(
    id = id,
    title = title,
    overview = overview,
    poster_path = poster_path ?: ""
)
