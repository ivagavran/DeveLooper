package hr.ivagavran.develooper;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import hr.ivagavran.develooper.databinding.ActivityHelperBinding
import hr.ivagavran.develooper.quiz.QuizMain
import hr.ivagavran.develooper.tips.TipsActivity

class Helper : AppCompatActivity() {
    private lateinit var binding: ActivityHelperBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.tipsTricksBtn.setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }

        binding.quizzesBtn.setOnClickListener {
            startActivity(Intent(this, QuizMain::class.java))
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            val email = firebaseUser.email
            binding.subTitleTv.text = email
        }
    }
}