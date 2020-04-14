package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.body.RateBody
import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.DiscoverResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.SimilarMoviesResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.data.ActorMovies
import retrofit2.Response
import retrofit2.http.*

internal interface MovieService {
    /*
        AUTHENTIFICATION
     */
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    //sessions
    @GET("authentication/session/new")
    suspend fun getSession(@Query("request_token") request_token:String): Response<SessionResponse>

    /*
        CATEGORIES
     */
    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getDiscover(@Query("with_genres") withGenres:Int): Response<DiscoverResponse>

    /*
        MOVIES
     */
    @GET("movie/{movie}")
    suspend fun getMovie(@Path("movie") movie:Int): Response<MovieResponse>

    @POST("movie/{movie}/rating")
    @Headers("Accept: application/json")
    suspend fun postRating(@Path("movie") movie:Int, @Body value: RateBody, @Query("session_id") session_id:String)

    @GET("movie/{movie}/similar")
    suspend fun getSimilarMovies(@Path("movie") movie:Int): Response<SimilarMoviesResponse>

    /*
        TRENDING
     */
    @GET("trending/movie/week")
    suspend fun getTrendingsMovies(): Response<TrendingMovieResponse>

    @GET("trending/person/week")
    suspend fun getTrendinsPersons(): Response<TrendingPersonResponse>

    /*
        RECHERCHE
     */
    @GET("search/movie")
    suspend fun getSearch(@Query("query") query:String): Response<SearchResponse>

    /*
        ACTORS
    */
    //actors of a movie
    @GET("person/{person_id}")
    suspend fun getActor(@Path("person_id") id:Int): Response<ActorResponse.ActorItem>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(@Path("movie_id") movie:Int): Response<ActorResponse>

    //movies of an actor
    @GET("person/{person_id}/movie_credits")
    suspend fun getActorMovies(@Path("person_id") id:Int): Response<ActorMoviesResponse>


}