package com.colosoft.webservices.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.webservices.server.freegamesrepository.FreeGamesRepository
import com.colosoft.webservices.server.model.FreeGame
import com.colosoft.webservices.server.model.FreeGamesList
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val gamesRepository = FreeGamesRepository()

    private val _gamesLoaded : MutableLiveData<ArrayList<FreeGame>> = MutableLiveData()
    val gamesLoaded: LiveData<ArrayList<FreeGame>> = _gamesLoaded

    fun getGames() {
        viewModelScope.launch {
            val gamesList : FreeGamesList = gamesRepository.getGames()
            _gamesLoaded.postValue(gamesList.games as ArrayList<FreeGame>)
        }
    }
}