package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.DiscoverResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.SimilarMoviesResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getDiscover(@Query("with_genres") withGenres:Int): Response<DiscoverResponse>

    @GET("movie/{movie}")
    suspend fun getMovie(@Path("movie") movie:Int): Response<MovieResponse>

    @GET("movie/{movie}/similar")
    suspend fun getSimilarMovies(@Path("movie") movie:Int): Response<SimilarMoviesResponse>
}