package com.colosoft.webservices.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.colosoft.webservices.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private lateinit var favoritesBinding: FragmentFavoritesBinding
    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = favoritesBinding.root
        return root
    }
}