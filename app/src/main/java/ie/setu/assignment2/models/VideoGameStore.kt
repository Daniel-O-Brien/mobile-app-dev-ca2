package ie.setu.assignment2.models

interface VideoGameStore {
    fun findAll(): List<VideoGameModel>
    fun create(videoGame: VideoGameModel)
    fun update(videoGame: VideoGameModel)
    fun delete(videoGame: VideoGameModel)
    fun wipe()
}