package ie.setu.assignment1.models

import com.google.gson.Gson
import android.content.Context
import timber.log.Timber.i
import java.io.FileOutputStream
import java.io.ObjectOutputStream

// https://medium.com/@zorbeytorunoglu/serialization-and-deserialization-on-kotlin-android-81596ac6da8e


data class Data(val a : ArrayList<VideoGameModel>)

class VideoGameFileStore : VideoGameStore {

    val videoGames = ArrayList<VideoGameModel>()

    val gson = Gson()

    override fun findAll(): List<VideoGameModel> {
        return videoGames
    }

    override fun create(videoGame: VideoGameModel) {
        videoGame.id = getId()
        videoGames.add(videoGame)
        val jsonString = gson.toJson(videoGame)
        i("%s%s", "JSON STRING", jsonString)
        logAll()
    }

    override fun update(videoGame: VideoGameModel) {
        var foundVideoGame: VideoGameModel? = videoGames.find { p -> p.id == videoGame.id }
        if (foundVideoGame != null) {
            foundVideoGame.title = videoGame.title
            foundVideoGame.description = videoGame.description
            logAll()
        }
    }

    override fun delete(videoGame: VideoGameModel) {
        var foundVideoGame: VideoGameModel? = videoGames.find { p -> p.id == videoGame.id }
        if (foundVideoGame != null) {
            videoGames.remove(foundVideoGame)
            logAll()
        }
    }

    override fun wipe() {
        videoGames.clear()
    }

    private fun logAll() {
        videoGames.forEach { i("Video Games: $it") }
    }
}