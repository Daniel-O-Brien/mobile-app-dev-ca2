package ie.setu.assignment2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.assignment2.databinding.CardVideogameBinding
import ie.setu.assignment2.models.VideoGameModel

interface VideoGameListener {
    fun onVideoGameClick(VideoGame: VideoGameModel)
}

class VideoGameAdapter(private var VideoGames: List<VideoGameModel>,
                       private val listener: VideoGameListener) :
    RecyclerView.Adapter<VideoGameAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardVideogameBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val VideoGame = VideoGames[holder.adapterPosition]
        holder.bind(VideoGame, listener)
    }

    override fun getItemCount(): Int = VideoGames.size

    class MainHolder(private val binding : CardVideogameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(VideoGame: VideoGameModel, listener: VideoGameListener) {
            binding.videoGameTitle.text = VideoGame.title
            binding.description.text = VideoGame.description
            binding.root.setOnClickListener { listener.onVideoGameClick(VideoGame) }
        }
    }
}
