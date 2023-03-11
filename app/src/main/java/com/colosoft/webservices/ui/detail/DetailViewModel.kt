package com.colosoft.webservices.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.webservices.local.LocalGame
import com.colosoft.webservices.local.repository.LocalGamesRepository
import com.colosoft.webservices.server.model.Level
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    val localGamesRepository = LocalGamesRepository()

    private val _finishedLevel : MutableLiveData<Boolean> = MutableLiveData()
    val finishedLevel: LiveData<Boolean> = _finishedLevel

    fun addLevelToFinished(game: Level) {
        val localGame = LocalGame(
            id = game.id,
            levelName = game.levelName,
            image1url = game.image1url,
            image2url = game.image2url,
            image3url = game.image3url,
            image4url = game.image4url,
            answer = game.answer,
            hint = game.hint,
            descriptionImage1 = game.descriptionImage1,
            descriptionImage2 = game.descriptionImage2,
            descriptionImage3 = game.descriptionImage3,
            descriptionImage4 = game.descriptionImage4
            )

        viewModelScope.launch {
            localGamesRepository.saveGame(localGame)
        }


    }

    fun searchLevel(id: Int?) {
        var finishedLevelAux = false
        viewModelScope.launch {
            val localGame = localGamesRepository.searchLevel(id)
            if (localGame != null)
                finishedLevelAux = true
            _finishedLevel.postValue(finishedLevelAux)
        }
    }

    fun incrementAttempts(attempts: Int): Int {
        return attempts + 1
    }
}