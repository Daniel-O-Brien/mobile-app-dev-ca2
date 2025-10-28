package ie.setu.assignment1.main

import android.app.Application
import ie.setu.assignment1.models.VideoGameFileStore
import ie.setu.assignment1.models.VideoGameMemStore
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