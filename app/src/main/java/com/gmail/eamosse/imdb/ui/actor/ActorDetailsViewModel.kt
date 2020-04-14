package com.gmail.eamosse.imdb.ui.actor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorDetailsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error


    private val _actor: MutableLiveData<Actor> = MutableLiveData()
    val actor: LiveData<Actor>
        get() = _actor
}
