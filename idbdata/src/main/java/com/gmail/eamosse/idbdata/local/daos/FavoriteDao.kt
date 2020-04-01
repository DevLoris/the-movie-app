package com.gmail.eamosse.idbdata.local.daos

import androidx.room.*
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovie
import com.gmail.eamosse.idbdata.local.entities.TokenEntity

@Dao
internal interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoriteMovie)

    @Query("SELECT * from favorite_movie LIMIT 1")
    fun retrieve(): List<FavoriteMovie>

    @Delete
    fun delete(movie: FavoriteMovie)
}