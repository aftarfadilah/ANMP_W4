package com.aftarfadilah.week4.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.aftarfadilah.week4.databinding.GamesListItemBinding
import com.aftarfadilah.week4.model.Games

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class GameListViewAdapter(
    val gameList:ArrayList<Games>
) : RecyclerView.Adapter<GameListViewAdapter.GameViewHolder>() {

    class GameViewHolder(var binding: GamesListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameListViewAdapter.GameViewHolder {
        val binding = GamesListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GameListViewAdapter.GameViewHolder(binding)
    }

    fun updateStudentList(newGameList: ArrayList<Games>) {
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.txtGenre.text = gameList[position].genre
        holder.binding.txtName.text = gameList[position].name
        holder.binding.txtPlatform.text = gameList[position].platforms?.joinToString(", ")
        holder.binding.txtSettingDifficulty.text = "Difficulty: ${gameList[position].settings?.difficulty}"
        holder.binding.txtSettingPlaytime.text = "Playtime: ${gameList[position].settings?.playtime}"
        holder.binding.txtSettingGameSize.text = "Game Time: ${gameList[position].settings?.gameSize}"

        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateGameList(newGameList: ArrayList<Games>) {
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return gameList.size
    }

}