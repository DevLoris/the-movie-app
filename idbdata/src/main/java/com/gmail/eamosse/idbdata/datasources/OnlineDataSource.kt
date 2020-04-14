package com.gmail.eamosse.idbdata.datasources

import android.util.Log
import com.gmail.eamosse.idbdata.api.body.RateBody
import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.DiscoverResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.extensions.parse
import com.gmail.eamosse.idbdata.extensions.safeCall
import com.gmail.eamosse.idbdata.utils.Result

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }

    suspend fun getMovie(id:Int): Result<MovieResponse> {
        return safeCall {
            val response = service.getMovie(id)
            response.parse()
        }
    }

    suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return safeCall {
            val response = service.getCategories()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.genres)
                is Result.Error -> result
            }
        }
    }


    suspend fun getSimilarMovies(movieId: Int): Result<List<SimilarMoviesResponse.Similar>> {
        return safeCall {
            val response = service.getSimilarMovies(movieId)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.movies)
                is Result.Error -> result
            }
        }
    }


    suspend fun getDiscover(genreId: Int): Result<List<DiscoverResponse.DiscoverItem>> {
        return safeCall {
            val response = service.getDiscover(genreId)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTrendingMovies(): Result<List<TrendingMovieResponse.Result>> {
        return safeCall {
            val response = service.getTrendingsMovies()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTrendingPeoples(): Result<List<TrendingPersonResponse.Person>> {
        return safeCall {
            val response = service.getTrendinsPersons()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getSearch(query:String): Result<List<SearchResponse.Result>> {
        return safeCall {
            val response = service.getSearch(query)
            when (val result = response.parse()) {
                is Result.Succes -> {
                    Result.Succes(result.data.results)
                }
                is Result.Error -> result
            }
        }
    }

    suspend fun getMovieActor(movie:Int): Result<List<ActorResponse.ActorItem>> {
        return safeCall {
            val response = service.getMovieActors(movie)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getActor(id:Int): Result<ActorResponse.ActorItem> {
        return safeCall {
            val response = service.getActor(id)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data)
                is Result.Error -> result
            }
        }
    }

    suspend fun getActorMovies(id:Int): Result<List<ActorMoviesResponse.ActorMovieItem>> {
        return safeCall {
            val response = service.getActorMovies(id)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.cast)
                is Result.Error -> result
            }
        }
    }

    suspend fun postRating(movie:Int, rating:Float, session:String) {
        service.postRating(movie, RateBody(rating), session);
    }
}

