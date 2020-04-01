package com.gmail.eamosse.imdb.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _discoveries: MutableLiveData<List<Discover>> = MutableLiveData()
    val discoveries: LiveData<List<Discover>>
        get() = _discoveries

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
        get() = _movie

    private val _similarMovie: MutableLiveData<List<SimilarMovie>> = MutableLiveData()
    val similarMovie: LiveData<List<SimilarMovie>>
        get() = _similarMovie

    private val _video: MutableLiveData<VideoMovie> = MutableLiveData()
    val video: LiveData<VideoMovie>
        get() = _video

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Succes -> {
                    _token.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getDiscover(genreId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getDiscover(genreId)) {
                is Result.Succes -> {
                    _discoveries.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovie(movieId)) {
                is Result.Succes -> {
                    _movie.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSimilarMovies(movieId)) {
                is Result.Succes -> {
                    _similarMovie.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getTrailer(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getVideosOfMovie(movieId)) {
                is Result.Succes -> {
                    var filtered = result.data.find { it.type == ("Trailer");  }
                    _video.postValue(filtered);
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun rateCurrentMovie(rate:Float = 10f) {
        if (movie.value is Movie) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.postRating(movie.value!!.id, rate, token.value!!.requestToken);
            }
        }
    }
}