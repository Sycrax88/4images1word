package com.colosoft.webservices.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.webservices.local.LocalGame
import com.colosoft.webservices.local.repository.LocalGamesRepository
import com.colosoft.webservices.server.model.FreeGame
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    var localGamesRepository = LocalGamesRepository()

    private val _gamesLoaded : MutableLiveData<ArrayList<LocalGame>> = MutableLiveData()
    val gamesLoaded: LiveData<ArrayList<LocalGame>> = _gamesLoaded

    fun loadGames() {
        viewModelScope.launch{
            val listFavoritesGames = localGamesRepository.getGames()
            _gamesLoaded.postValue(listFavoritesGames as ArrayList<LocalGame> /* = java.util.ArrayList<com.colosoft.webservices.local.LocalGame> */)
        }
    }

    fun deleteGame(localGame: LocalGame) {
        viewModelScope.launch {
            localGamesRepository.deleteGame(localGame)
        }
    }


}