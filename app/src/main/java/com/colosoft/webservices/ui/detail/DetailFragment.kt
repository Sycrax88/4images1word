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
    private var finishedLevelAux = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val game = args.game

        detailViewModel.searchLevel(game.id)

        detailViewModel.finishedLevel.observe(viewLifecycleOwner) { finishedLevel ->
            if (finishedLevel){
                //detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                detailBinding.validateButton.isEnabled = false;
                detailBinding.textInputLayout.hint = game.answer.toString().uppercase();
                detailBinding.textInputLayout.isEnabled = false;
                finishedLevelAux = true
            }
            else{
                //detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites_border))
                detailBinding.validateButton.isEnabled = true;
                finishedLevelAux = false
            }
        }


        with(detailBinding){


            descriptionImage1.visibility = View.GONE;
            descriptionImage2.visibility = View.GONE;
            descriptionImage3.visibility = View.GONE;
            descriptionImage4.visibility = View.GONE;

            levelTextView.text = game.levelName
            initialHint.text = "Number of letters: " + game.answer?.length.toString();
            descriptionImage1.text = game.descriptionImage1
            descriptionImage2.text = game.descriptionImage2
            descriptionImage3.text = game.descriptionImage3
            descriptionImage4.text = game.descriptionImage4
            shownHintLabel.text = game.hint

            Picasso.get().load(game.image1url).into(image1)
            Picasso.get().load(game.image2url).into(image2)
            Picasso.get().load(game.image3url).into(image3)
            Picasso.get().load(game.image4url).into(image4)

            hintLabel.visibility = View.GONE;
            shownHintLabel.visibility = View.GONE;

            image1.setOnClickListener {
                if (descriptionImage1.visibility == View.GONE)
                    descriptionImage1.visibility = View.VISIBLE;
                else
                    descriptionImage1.visibility = View.GONE;
            }
            image2.setOnClickListener {
                if (descriptionImage2.visibility == View.GONE)
                    descriptionImage2.visibility = View.VISIBLE;
                else
                    descriptionImage2.visibility = View.GONE;
            }
            image3.setOnClickListener {
                if (descriptionImage3.visibility == View.GONE)
                    descriptionImage3.visibility = View.VISIBLE;
                else
                    descriptionImage3.visibility = View.GONE;
            }
            image4.setOnClickListener {
                if (descriptionImage4.visibility == View.GONE)
                    descriptionImage4.visibility = View.VISIBLE;
                else
                    descriptionImage4.visibility = View.GONE;
            }

            var attemptsNumber = 0
            validateButton.setOnClickListener {
                attemptsNumber = detailViewModel.incrementAttempts(attemptsNumber)
                if (attemptsNumber == 5){
                    hintLabel.visibility = View.VISIBLE;
                    shownHintLabel.visibility = View.VISIBLE;
                }

                if (answerInputEditText.text.toString() == game.answer!!.uppercase()) {
                    detailViewModel.addLevelToFinished(game)
                    finishedLevelAux = true
                    Toast.makeText(getActivity(), R.string.correct_answer, Toast.LENGTH_SHORT)
                        .show();
                    validateButton.isEnabled = false;
                    textInputLayout.isEnabled = false;
                }
                else{
                    Toast.makeText(getActivity(), R.string.wrong_answer, Toast.LENGTH_SHORT)
                        .show();
                }
            }

            /*favoritesImageView.setOnClickListener {
                if (finishedLevelAux)
                    Toast.makeText(
                        context,
                        "${game.levelName} is already in your favorites list.",
                        Toast.LENGTH_LONG
                    ).show()
                else {
                    //detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                    finishedLevelAux = true
                    detailViewModel.addLevelToFinished(game)

                }
            } */

        }
    }
}