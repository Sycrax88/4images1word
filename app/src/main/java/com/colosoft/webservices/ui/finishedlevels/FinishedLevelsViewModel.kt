package com.colosoft.webservices.ui.finishedlevels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.webservices.local.LocalGame
import com.colosoft.webservices.local.repository.LocalGamesRepository
import kotlinx.coroutines.launch

class FinishedLevelsViewModel : ViewModel() {

    var localGamesRepository = LocalGamesRepository()

    private val _gamesLoaded : MutableLiveData<ArrayList<LocalGame>> = MutableLiveData()
    val gamesLoaded: LiveData<ArrayList<LocalGame>> = _gamesLoaded

    fun loadGames() {
        viewModelScope.launch{
            val listFinishedLevels = localGamesRepository.getLevels()
            _gamesLoaded.postValue(listFinishedLevels as ArrayList<LocalGame> /* = java.util.ArrayList<com.colosoft.webservices.local.LocalGame> */)
        }
    }

    fun deleteLevel(localGame: LocalGame) {
        viewModelScope.launch {
            localGamesRepository.deleteLevel(localGame)
        }
    }


}