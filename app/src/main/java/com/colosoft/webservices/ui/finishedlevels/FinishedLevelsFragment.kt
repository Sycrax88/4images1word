package com.colosoft.webservices.ui.finishedlevels

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.colosoft.webservices.R
import com.colosoft.webservices.databinding.FragmentFinishedLevelsBinding
import com.colosoft.webservices.local.LocalGame
import com.colosoft.webservices.server.model.Level
import com.colosoft.webservices.ui.list.GamesAdapter

class FinishedLevelsFragment : Fragment() {

    private lateinit var finishedLevelsBinding: FragmentFinishedLevelsBinding
    private lateinit var finishedLevelsViewModel: FinishedLevelsViewModel
    private lateinit var finishedLevelsAdapter: FinishedLevelsAdapter
    private var gamesList: ArrayList<LocalGame> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        finishedLevelsViewModel = ViewModelProvider(this)[FinishedLevelsViewModel::class.java]
        finishedLevelsBinding = FragmentFinishedLevelsBinding.inflate(inflater, container, false)

        finishedLevelsViewModel.loadGames()

        finishedLevelsAdapter = FinishedLevelsAdapter(gamesList,
            onItemClicked = {onItemClicked(it)},
            onLongItemClicked = {onItemLongClicked(it)})

        finishedLevelsBinding.gamesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FinishedLevelsFragment.context)
            adapter = finishedLevelsAdapter
            setHasFixedSize(false)
        }

        finishedLevelsViewModel.gamesLoaded.observe(viewLifecycleOwner,{ finishedLevelsAdapter.appendItems(it)})



        val root: View = finishedLevelsBinding.root
        return root
    }

    private fun onItemLongClicked(localGame: LocalGame) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("Do you want to delete ${localGame.levelName} from your finished levels?")
                setPositiveButton(R.string.accept){ dialog, id ->
                    finishedLevelsViewModel.deleteLevel(localGame)
                    finishedLevelsViewModel.loadGames()

                }
                setNegativeButton(R.string.cancel){ dialog, id ->

                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun onItemClicked(localGame: LocalGame) {
        val sendLevel = Level(
            id = localGame.id,
            levelName = localGame.levelName,
            image1url = localGame.image1url,
            image2url = localGame.image2url,
            image3url = localGame.image3url,
            image4url = localGame.image4url,
            answer = localGame.answer,
            hint = localGame.hint,
            descriptionImage1 = localGame.descriptionImage1,
            descriptionImage2 = localGame.descriptionImage2,
            descriptionImage3 = localGame.descriptionImage3,
            descriptionImage4 = localGame.descriptionImage4
        )
        findNavController().navigate(FinishedLevelsFragmentDirections.actionNavigationFinishedToDetailFragment(sendLevel))

    }
}