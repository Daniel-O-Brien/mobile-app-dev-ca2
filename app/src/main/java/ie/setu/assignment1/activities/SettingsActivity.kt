package ie.setu.assignment1.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ie.setu.assignment1.R
import ie.setu.assignment1.databinding.ActivitySettingsBinding
import ie.setu.assignment1.main.MainApp
import timber.log.Timber.i

class SettingsActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarSettings.title = title
        setSupportActionBar(binding.toolbarSettings)

        app = application as MainApp

        binding.btnWipe.setOnClickListener() {
            app.videoGame.wipe()
            setResult(RESULT_OK)
            finish()
        }
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