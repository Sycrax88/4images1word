package com.colosoft.webservices.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.colosoft.webservices.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val game = args.game
        with(detailBinding){
            gameTitleTextView.text = game.title
            releaseDateTextView.text = game.releaseDate
            publisherTextView.text = game.publisher
            developerTextView.text = game.developer
            platformTextView.text = game.platform
            genreTextView.text = game.genre
            shortDescriptionTextView.text = game.shortDescription
            Picasso.get().load("https://www.freetogame.com/g/"+game.id.toString()+"/thumbnail.jpg").into(posterImageView)
        }
    }
}