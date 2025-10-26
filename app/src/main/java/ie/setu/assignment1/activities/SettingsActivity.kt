package ie.setu.assignment1.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.assignment1.R
import ie.setu.assignment1.adapters.VideoGameAdapter
import ie.setu.assignment1.databinding.ActivitySettingsBinding
import ie.setu.assignment1.databinding.ActivityVideogameBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.VideoGameModel

class SettingsActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarCancel.title = title
        setSupportActionBar(binding.toolbarCancel)

        app = application as MainApp
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_back -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}