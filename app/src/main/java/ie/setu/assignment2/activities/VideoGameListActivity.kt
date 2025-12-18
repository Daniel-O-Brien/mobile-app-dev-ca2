package ie.setu.assignment2.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.assignment2.R
import ie.setu.assignment2.adapters.VideoGameAdapter
import ie.setu.assignment2.adapters.VideoGameListener
import ie.setu.assignment2.databinding.ActivityVideogameListBinding
import ie.setu.assignment2.main.MainApp
import ie.setu.assignment2.models.VideoGameModel

class VideoGameListActivity : AppCompatActivity(), VideoGameListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityVideogameListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideogameListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = VideoGameAdapter(app.videoGame.findAll(),this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.map -> {
                val launcherIntent  = Intent(this, MapActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.item_add -> {
                val launcherIntent = Intent(this, VideoGameActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.settings -> {
                val launcherIntent = Intent(this, SettingsActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
//                (binding.recyclerView.adapter)?.
//                notifyItemRangeChanged(0,app.videoGame.findAll().size)
                binding.recyclerView.adapter = VideoGameAdapter(app.videoGame.findAll(),this)
            }
        }

    override fun onVideoGameClick(VideoGame: VideoGameModel) {
        val launcherIntent = Intent(this, VideoGameActivity::class.java)
        launcherIntent.putExtra("VideoGame_edit", VideoGame)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
//                (binding.recyclerView.adapter)?.
//                notifyItemRangeChanged(0,app.videoGame.findAll().size)
                binding.recyclerView.adapter = VideoGameAdapter(app.videoGame.findAll(),this)
            }
        }
}