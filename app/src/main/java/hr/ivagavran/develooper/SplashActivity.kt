package hr.ivagavran.develooper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        firebaseAuth = FirebaseAuth.getInstance()
        Handler().postDelayed(Runnable {
            startActivity(Intent( this, MainActivity::class.java))
            finish()
        },1000)
    }

}