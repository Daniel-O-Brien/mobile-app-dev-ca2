package ie.setu.assignment1.models

interface VideoGameStore {
    fun findAll(): List<VideoGameModel>
    fun create(videoGame: VideoGameModel)
    fun update(videoGame: VideoGameModel)
}