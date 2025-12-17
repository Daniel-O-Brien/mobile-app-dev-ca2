package ie.setu.assignment2.models

import com.google.gson.Gson
import android.content.Context
import com.google.gson.reflect.TypeToken
import timber.log.Timber.i
import java.io.File
import java.time.LocalDate

// https://medium.com/@zorbeytorunoglu/serialization-and-deserialization-on-kotlin-android-81596ac6da8e
// https://developer.android.com/training/data-storage/app-specific
// https://developer.android.com/reference/android/content/Context
// https://www.bezkoder.com/kotlin-parse-json-gson/#GsonfromJson_method
// https://developer.android.com/reference/java/io/File#exists()


data class Data(val a : ArrayList<VideoGameModel>)

class VideoGameFileStore(val context: Context): VideoGameStore {

    lateinit var videoGames: ArrayList<VideoGameModel>

    val gson = Gson()

    val typeToken = object : TypeToken<ArrayList<VideoGameModel>>() {}.type

    fun save(data: ArrayList<VideoGameModel>) {
        val file = gson.toJson(data)
        i("1111" + data)
        context.openFileOutput("data", Context.MODE_PRIVATE).use {
            it.write(file.toByteArray())
            i("2222" + file)
        }
    }

    fun load() {
        if (File(context.filesDir, "data").exists()) {

            val data = context.openFileInput("data").bufferedReader().use { it.readText() }
            i(data)
            if (!data.isEmpty()) {
                videoGames = gson.fromJson<ArrayList<VideoGameModel>>(data, typeToken)

                for (videoGame in videoGames)
                    if (videoGame.releaseDate.toString() == "0000-00-00")
                        videoGame.releaseDate = LocalDate.parse("1970-01-01")
            } else
                videoGames = ArrayList<VideoGameModel>()
        }
    }

    override fun findAll(): List<VideoGameModel> {
        return videoGames
    }

    override fun create(videoGame: VideoGameModel) {
        videoGame.id = getId()
        i("0000" + videoGame.toString())
        videoGames.add(videoGame)
        save(videoGames)
        logAll()
    }

    override fun update(videoGame: VideoGameModel) {
        var foundVideoGame: VideoGameModel? = videoGames.find { p -> p.id == videoGame.id }
        if (foundVideoGame != null) {
            foundVideoGame.title = videoGame.title
            foundVideoGame.description = videoGame.description
            foundVideoGame.developer = videoGame.developer
            foundVideoGame.releaseDate = videoGame.releaseDate
            logAll()
        }
    }

    override fun delete(videoGame: VideoGameModel) {
        var foundVideoGame: VideoGameModel? = videoGames.find { p -> p.id == videoGame.id }
        if (foundVideoGame != null) {
            videoGames.remove(foundVideoGame)
            save(videoGames)
            logAll()
        }
    }

    override fun wipe() {
        videoGames.clear()
        save(videoGames)
    }

    private fun logAll() {
        videoGames.forEach { i("Video Games: $it") }
    }
}