package com.colosoft.webservices.ui.finishedlevels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colosoft.webservices.R
import com.colosoft.webservices.databinding.CardViewItemGameFinishedBinding
import com.colosoft.webservices.local.LocalGame

class FinishedLevelsAdapter (
    private val finishedLevelsList: ArrayList<LocalGame>,
    private val onItemClicked: (LocalGame) -> Unit,
    private val onLongItemClicked: (LocalGame) ->Unit,
) : RecyclerView.Adapter<FinishedLevelsAdapter.LevelsFinishedViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsFinishedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_game_finished,parent,false)
        return LevelsFinishedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LevelsFinishedViewHolder, position: Int) {
        val game = finishedLevelsList[position]
        holder.bindGame(game)
        holder.itemView.setOnClickListener{ onItemClicked(finishedLevelsList[position])}
        holder.itemView.setOnLongClickListener{ onLongItemClicked(finishedLevelsList[position])
            true}
    }

    override fun getItemCount(): Int = finishedLevelsList.size

    fun appendItems(newList: ArrayList<LocalGame>){
        finishedLevelsList.clear()
        finishedLevelsList.addAll(newList)
        notifyDataSetChanged()
    }

    class  LevelsFinishedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = CardViewItemGameFinishedBinding.bind(itemView)

        fun bindGame(game: LocalGame){
            with(binding){
                levelTextView.text = game.levelName
                answerTextView.text = game.answer.toString()

                //Picasso.get().load("https://www.freetogame.com/g/"+game.id.toString()+"/thumbnail.jpg").into(posterImageView)
            }
        }
    }
}