package com.gmail.eamosse.imdb.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovie
import com.gmail.eamosse.idbdata.local.entities.toDiscover
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error


    private val _favorites: MutableLiveData<List<Discover>> = MutableLiveData()
    val favorites: LiveData<List<Discover>>
        get() = _favorites


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

    fun getFavorites(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            _favorites.postValue( repository.getFavoritesMovies().map { it.toDiscover() })
        }
    }

}