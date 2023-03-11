package com.colosoft.webservices.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.webservices.local.LocalGame
import com.colosoft.webservices.local.repository.LocalGamesRepository
import com.colosoft.webservices.server.levelsrepository.LevelsRepository
import com.colosoft.webservices.server.model.Level
import com.colosoft.webservices.server.model.LevelsList
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    var localGamesRepository = LocalGamesRepository()
    private val gamesRepository = LevelsRepository()

    private val _gamesLoaded : MutableLiveData<ArrayList<Level>> = MutableLiveData()
    val gamesLoaded: LiveData<ArrayList<Level>> = _gamesLoaded

    fun getLevels() {
        viewModelScope.launch {
            val gamesList : LevelsList = gamesRepository.getLevels()
            val listFinishedLevels = localGamesRepository.getLevels()
            //val gamesNotFinished = gamesList.subtract(listFinishedLevels.toSet()).toList()

            val gamesNotFinished = gamesList.filter { a ->
                listFinishedLevels.none { b ->
                    areEqual(a, b)
                }
            }
            println("SOG: $gamesNotFinished")
            _gamesLoaded.postValue(gamesNotFinished as ArrayList<Level>)
        }
    }

    fun areEqual(a: Level, b: LocalGame): Boolean {
        return a.id == b.id
    }
}