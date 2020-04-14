package com.gmail.eamosse.idbdata.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.eamosse.idbdata.local.daos.FavoriteDao
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovie
import com.gmail.eamosse.idbdata.local.entities.TokenEntity

/**
 * Modélise la base de données ainsi que les tables de la BDD
 */
@Database(
    entities = [TokenEntity::class, FavoriteMovie::class],
    version = 2
)
internal abstract class IdbDataBase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun favoriteDao(): FavoriteDao
}