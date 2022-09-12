package com.colosoft.webservices.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.webservices.local.LocalGame
import com.colosoft.webservices.local.repository.LocalGamesRepository
import com.colosoft.webservices.server.model.FreeGame
import kotlinx.coroutines.launch
import java.sql.Types.NULL

class DetailViewModel : ViewModel() {

    val localGamesRepository = LocalGamesRepository()

    private val _gameExist : MutableLiveData<Boolean> = MutableLiveData()
    val gameExist: LiveData<Boolean> = _gameExist

    fun addGameToFavorites(game: FreeGame) {
        val localGame = LocalGame(
            id = game.id,
            name= game.title,
            releaseDate = game.releaseDate,
            publisher = game.publisher,
            developer = game.developer,
            platform = game.platform,
            genre= game.genre,
            summary = game.shortDescription)


        viewModelScope.launch {
            localGamesRepository.saveGame(localGame)
        }


    }

    fun searchGame(id: Int?) {
        var gameExistAux = false
        viewModelScope.launch {
            val localGame = localGamesRepository.searchGame(id)
            if (localGame != null)
                gameExistAux = true
            _gameExist.postValue(gameExistAux)
        }
    }
}