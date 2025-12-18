package ie.setu.assignment2.main

import android.app.Application
import ie.setu.assignment2.models.VideoGameFileStore
import ie.setu.assignment2.models.UserFileStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val videoGame = VideoGameFileStore(this)
    val user = UserFileStore(this)


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("VideoGame started")
        videoGame.load()
        user.load()
    }
}