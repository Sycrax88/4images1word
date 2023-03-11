package com.colosoft.webservices.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.colosoft.webservices.R
import com.colosoft.webservices.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private var gameExistAux = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val game = args.game

        detailViewModel.searchGame(game.id)

        detailViewModel.gameExist.observe(viewLifecycleOwner) { gameExist ->
            if (gameExist){
                detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))

                detailBinding.validateButton.isEnabled = false;
                gameExistAux = true
            }
            else{
                detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites_border))
                detailBinding.validateButton.isEnabled = true;
                gameExistAux = false
            }
        }


        with(detailBinding){
            if (gameExistAux){
                textInputLayout.hint = game.answer;
                textInputLayout.isEnabled = false;
            }

            levelTextView.text = game.levelName
            initialHint.text = "Number of letters: " + game.answer?.length.toString();
            descriptionImage1.text = game.descriptionImage1
            descriptionImage2.text = game.descriptionImage2
            descriptionImage3.text = game.descriptionImage3
            descriptionImage4.text = game.descriptionImage4



            Picasso.get().load(game.image1url).into(image1)
            Picasso.get().load(game.image2url).into(image2)
            Picasso.get().load(game.image3url).into(image3)
            Picasso.get().load(game.image4url).into(image4)

            validateButton.setOnClickListener {
                if (answerInputEditText.text.toString() == game.answer){
                    detailViewModel.addGameToFavorites(game)

                    Toast.makeText(getActivity(),R.string.correct_answer,Toast.LENGTH_SHORT).show();

                }

            favoritesImageView.setOnClickListener {
                if (gameExistAux)
                    Toast.makeText(context, "${game.levelName} is already in your favorites list.", Toast.LENGTH_LONG).show()
                else{
                    detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                    gameExistAux = true
                    detailViewModel.addGameToFavorites(game)

                }

            }
        }
    }
}