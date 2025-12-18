package ie.setu.assignment2.main

import android.app.Application
import ie.setu.assignment2.models.VideoGameFileStore
import ie.setu.assignment2.models.UserFileStore
import ie.setu.assignment2.models.UserModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val videoGame = VideoGameFileStore(this)
    val user = UserFileStore(this)

    var currentUserId = 0L

    fun updateCurrentUser(id: Long) {
        currentUserId = id
        i("kfglsd" + id)
        videoGame.load(id)
        i("dsfzkj" + videoGame)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("VideoGame started")
        user.load()
    }
}