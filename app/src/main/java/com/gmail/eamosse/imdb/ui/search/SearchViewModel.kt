package com.gmail.eamosse.imdb.ui.search

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

class SearchViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error


    private val _searchs: MutableLiveData<List<Discover>> = MutableLiveData()
    val searchs: LiveData<List<Discover>>
        get() = _searchs

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

    fun onSearch()  {
        Log.i("IMDB","CLICK SEARCH");
        getSearch(search.value!!)
    }

    fun getSearch(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSearch(search)) {
                is Result.Succes -> {
                    _searchs.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}