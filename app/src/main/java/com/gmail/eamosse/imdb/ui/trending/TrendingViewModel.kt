package com.gmail.eamosse.imdb.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _trendingMovies: MutableLiveData<List<Trending>> = MutableLiveData()
    val trendingMovies: LiveData<List<Trending>>
        get() = _trendingMovies

    private val _trendingPeoples: MutableLiveData<List<Person>> = MutableLiveData()
    val trendingPeoples: LiveData<List<Person>>
        get() = _trendingPeoples

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories


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

    fun getTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrendingMovies()) {
                is Result.Succes -> {
                    _trendingMovies.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getTrendingPeoples() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrendingPeoples()) {
                is Result.Succes -> {
                    _trendingPeoples.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}