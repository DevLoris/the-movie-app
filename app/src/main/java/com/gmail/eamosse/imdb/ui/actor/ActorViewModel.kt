package com.gmail.eamosse.imdb.ui.actor

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

class ActorViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error


    private val _actors: MutableLiveData<List<Actor>> = MutableLiveData()
    val actors: LiveData<List<Actor>>
        get() = _actors

    private val _actor: MutableLiveData<Actor> = MutableLiveData()
    val actor: LiveData<Actor>
        get() = _actor

    private val _movies: MutableLiveData<List<Discover>> = MutableLiveData()
    val movies: LiveData<List<Discover>>
        get() = _movies

    private val _actorMovies: MutableLiveData<List<ActorMovies>> = MutableLiveData()
    val actorMovies: LiveData<List<ActorMovies>>
        get() = _actorMovies

    var search: MutableLiveData<String> = MutableLiveData("")

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

    fun onSearch(search: String)  {
        getMovie(search)
    }

    private fun getMovie(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSearch(search)) {
                is Result.Succes -> {
                    var data = mutableListOf<Discover>()
                    result.data.forEach {
                        if(it.poster_path != "") {
                            data.add(it)
                        }
                    }
                    _movies.postValue(data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getActors(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieActors(id)) {
                is Result.Succes -> _actors.postValue(result.data)
                is Result.Error -> _error.postValue(result.message)
            }
        }
    }

    fun getActor(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getActor(id)) {
                is Result.Succes -> _actor.postValue(result.data)
                is Result.Error -> _error.postValue(result.message)
            }
        }
    }

    fun getActorMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getActorMovies(id)) {
                is Result.Succes -> _actorMovies.postValue(result.data)
                is Result.Error -> _error.postValue(result.message)
            }
        }
    }
}