package com.colosoft.webservices.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.system.Os.accept
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.colosoft.webservices.R
import com.colosoft.webservices.databinding.FragmentFavoritesBinding
import com.colosoft.webservices.local.LocalGame
import com.colosoft.webservices.server.model.FreeGame

class FavoritesFragment : Fragment() {

    private lateinit var favoritesBinding: FragmentFavoritesBinding
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var gamesFavoritesAdapter: GamesFavoritesAdapter
    private var gamesList: ArrayList<LocalGame> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)

        favoritesViewModel.loadGames()

        gamesFavoritesAdapter = GamesFavoritesAdapter(gamesList,
            onItemClicked = {onItemClicked(it)},
            onLongItemClicked = {onItemLongClicked(it)})

        favoritesBinding.gamesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesFragment.context)
            adapter = gamesFavoritesAdapter
            setHasFixedSize(false)
        }

        favoritesViewModel.gamesLoaded.observe(viewLifecycleOwner,{ gamesFavoritesAdapter.appendItems(it)})



        val root: View = favoritesBinding.root
        return root
    }

    private fun onItemLongClicked(localGame: LocalGame) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("Do you want to delete ${localGame.levelName} from your favorites?")
                setPositiveButton(R.string.accept){ dialog, id ->
                    favoritesViewModel.deleteGame(localGame)
                    favoritesViewModel.loadGames()

                }
                setNegativeButton(R.string.cancel){ dialog, id ->

                }
            }
            builder.create()
        }
        alertDialog?.show()

        favoritesViewModel.deleteGame(localGame)
    }

    private fun onItemClicked(localGame: LocalGame) {

    }
}