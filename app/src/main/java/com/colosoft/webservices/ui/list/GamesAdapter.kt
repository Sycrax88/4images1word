package com.colosoft.webservices.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colosoft.webservices.R
import com.colosoft.webservices.databinding.CardViewItemGameBinding
import com.colosoft.webservices.server.model.Level

class GamesAdapter(
    private val gamesList: ArrayList<Level>,
    private val onItemClicked: (Level) -> Unit
    ) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_game,parent,false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gamesList[position]
        holder.bindGame(game)
        holder.itemView.setOnClickListener{ onItemClicked(gamesList[position])}
    }

    override fun getItemCount(): Int = gamesList.size

    fun appendItems(newList: ArrayList<Level>){
        gamesList.clear()
        gamesList.addAll(newList)
        notifyDataSetChanged()
    }

        class  GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val binding = CardViewItemGameBinding.bind(itemView)

            fun bindGame(game: Level){
                with(binding){
                    levelTextView.text = game.levelName
                }
            }
        }
    }