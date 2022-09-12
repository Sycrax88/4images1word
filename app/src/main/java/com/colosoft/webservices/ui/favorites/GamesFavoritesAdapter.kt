package com.colosoft.webservices.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colosoft.webservices.R
import com.colosoft.webservices.databinding.CardViewItemGameBinding
import com.colosoft.webservices.local.LocalGame
import com.squareup.picasso.Picasso

class GamesFavoritesAdapter (
    private val gamesList: ArrayList<LocalGame>,
    private val onItemClicked: (LocalGame) -> Unit,
    private val onLongItemClicked: (LocalGame) ->Unit,
) : RecyclerView.Adapter<GamesFavoritesAdapter.GamesFavoritesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesFavoritesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_game,parent,false)
        return GamesFavoritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GamesFavoritesViewHolder, position: Int) {
        val game = gamesList[position]
        holder.bindGame(game)
        holder.itemView.setOnClickListener{ onItemClicked(gamesList[position])}
        holder.itemView.setOnLongClickListener{ onLongItemClicked(gamesList[position])
            true}
    }

    override fun getItemCount(): Int = gamesList.size

    fun appendItems(newList: ArrayList<LocalGame>){
        gamesList.clear()
        gamesList.addAll(newList)
        notifyDataSetChanged()
    }

    class  GamesFavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = CardViewItemGameBinding.bind(itemView)

        fun bindGame(game: LocalGame){
            with(binding){
                gameTitleTextView.text = game.name
                genreTextView.text = game.genre
                platformTextView.text = game.platform
                Picasso.get().load("https://www.freetogame.com/g/"+game.id.toString()+"/thumbnail.jpg").into(posterImageView)
            }
        }
    }
}