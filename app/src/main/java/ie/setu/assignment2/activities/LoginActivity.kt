//https://medium.com/@afifaali931/simple-login-page-in-kotlin-android-for-beginners-6ad8dc25a903

package ie.setu.assignment2.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.assignment2.adapters.VideoGameAdapter
import ie.setu.assignment2.databinding.ActivityLoginBinding
import ie.setu.assignment2.main.MainApp
import timber.log.Timber.i

class LoginActivity : AppCompatActivity(){

    lateinit var app: MainApp
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        binding.btnLogin.setOnClickListener() {
            app.user.delete(videoGame.copy())
            i("delete Button Pressed: $videoGame")
            setResult(RESULT_OK)
            finish()
        }

        binding.btnSignup.setOnClickListener {

        }

    }

}