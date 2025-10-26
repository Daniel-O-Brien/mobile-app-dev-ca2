package ie.setu.assignment1.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class VideoGameMemStore : VideoGameStore {

    val videoGames = ArrayList<VideoGameModel>()

    override fun findAll(): List<VideoGameModel> {
        return videoGames
    }

    override fun create(videoGame: VideoGameModel) {
        videoGame.id = getId()
        videoGames.add(videoGame)
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
        i("found video game")
        if (foundVideoGame != null) {
            videoGames.remove(foundVideoGame)
            i("deletes game")
            logAll()
        }
    }

    private fun logAll() {
        videoGames.forEach { i("Video Games: $it") }
    }
}