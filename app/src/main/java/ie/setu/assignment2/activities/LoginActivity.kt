//https://medium.com/@afifaali931/simple-login-page-in-kotlin-android-for-beginners-6ad8dc25a903

package ie.setu.assignment2.activities

import android.os.Bundle
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.assignment2.adapters.VideoGameAdapter
import ie.setu.assignment2.databinding.ActivityLoginBinding
import ie.setu.assignment2.databinding.ActivityVideogameBinding
import ie.setu.assignment2.main.MainApp
import ie.setu.assignment2.models.UserModel
import ie.setu.assignment2.R
import timber.log.Timber.i
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(){

    var user = UserModel()
    lateinit var app: MainApp
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        binding.btnLogin.setOnClickListener() {
            user.username = binding.username.text.toString()
            user.password = binding.username.text.toString()
            val loginUser = app.user.login(user)
            if (loginUser != null) {
                val launcherIntent = Intent(this, VideoGameListActivity::class.java)
                startActivity(launcherIntent)
                finish()
            } else {
                Snackbar.make(it,"Invalid username or password", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.btnSignup.setOnClickListener {
            user.username = binding.username.text.toString()
            user.password = binding.username.text.toString()
            val newUser = app.user.signup(user)
            if (newUser != null)
                Snackbar.make(it, "Registered Successfully", Snackbar.LENGTH_LONG).show()
            else Snackbar.make(it, "User already exists", Snackbar.LENGTH_LONG).show()
        }

    }


}