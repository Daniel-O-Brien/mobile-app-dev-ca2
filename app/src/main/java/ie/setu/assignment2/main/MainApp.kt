package ie.setu.assignment2.main

import android.app.Application
import ie.setu.assignment2.models.VideoGameFileStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val videoGame = VideoGameFileStore(this)


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("VideoGame started")
        videoGame.load()
    }
}