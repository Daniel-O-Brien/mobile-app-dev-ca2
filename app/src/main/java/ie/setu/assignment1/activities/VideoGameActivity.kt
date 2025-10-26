package ie.setu.assignment1.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.setu.assignment1.R
import ie.setu.assignment1.databinding.ActivityVideogameBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.VideoGameModel
import ie.setu.assignment1.helpers.showImagePicker
import timber.log.Timber.i

class VideoGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideogameBinding
    var videoGame = VideoGameModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

        binding = ActivityVideogameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("VideoGame Activity started...")

        if (intent.hasExtra("VideoGame_edit")) {
            edit = true
            // Requires API 33+
            // VideoGame = intent.getParcelableExtra("VideoGame_edit",VideoGameModel::class.java)!!
            videoGame = intent.extras?.getParcelable("VideoGame_edit")!!
            binding.videoGameTitle.setText(videoGame.title)
            binding.description.setText(videoGame.description)
            binding.btnAdd.setText(R.string.save_VideoGame)
            Picasso.get()
                .load(videoGame.image)
                .into(binding.VideoGameImage)
        }

        binding.btnAdd.setOnClickListener() {
            videoGame.title = binding.videoGameTitle.text.toString()
            videoGame.description = binding.description.text.toString()
            if (videoGame.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_VideoGame_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.videoGame.update(videoGame.copy())
                } else {
                    app.videoGame.create(videoGame.copy())
                }
            }
            i("add Button Pressed: $videoGame")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        registerImagePickerCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_videogame, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            videoGame.image = result.data!!.data!!
                            Picasso.get()
                                .load(videoGame.image)
                                .into(binding.VideoGameImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}