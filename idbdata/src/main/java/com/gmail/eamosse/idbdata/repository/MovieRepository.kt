package com.gmail.eamosse.idbdata.repository

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toCategory
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.extensions.safeCall
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    //Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    //Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when(val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data.toEntity())
                //return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when(val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getDiscover(id:Int): Result<List<Discover>> {
        return when(val result = online.getDiscover(id)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val discover = result.data.map {
                    it.toDiscover()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    /**
     * Get a movie by ID
     * @param int id
     * @return Result<Movie>
     */
    suspend fun getMovie(id:Int): Result<Movie> {
        return when(val result = online.getMovie(id)) {
            is Result.Succes -> {
                Result.Succes(result.data.toMovie())
            }
            is Result.Error -> result
        }
    }

    /**
     * Get a movie by ID
     * @param int id
     */
    suspend fun getSimilarMovies(id:Int): Result<List<SimilarMovie>> {
        return when(val result = online.getSimilarMovies(id)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val discover = result.data.map {
                    it.toSimilarMovie()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    /**
     * Get trending movies
     */
    suspend fun getTrendingMovies(): Result<List<Trending>> {
        return when(val result = online.getTrendingMovies()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val discover = result.data.map {
                    it.toTrending()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    /**
     * Get trending movies
     */
    suspend fun getTrendingPeoples(): Result<List<Person>> {
        return when(val result = online.getTrendingPeoples()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val discover = result.data.map {
                    it.toPerson()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }
}