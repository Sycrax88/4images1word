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
            levelName = game.levelName,
            image1url = game.image1url,
            image2url = game.image2url,
            image3url = game.image3url,
            image4url = game.image4url,
            answer = game.answer)

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