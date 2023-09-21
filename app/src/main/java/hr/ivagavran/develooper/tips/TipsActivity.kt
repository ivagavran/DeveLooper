package hr.ivagavran.develooper.tips;

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import hr.ivagavran.develooper.MainActivity
import hr.ivagavran.develooper.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTipsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var tipsArrayList: ArrayList<ModelTips>
    private lateinit var adapterTips: AdapterTips

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        loadTips()

        binding.searchEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    adapterTips.filter.filter(s)
                }
                catch (e: Exception){
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        } )

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        binding.addTipsBtn.setOnClickListener {
            startActivity(Intent(this, TipsAddActivity::class.java))
        }
    }

    private fun loadTips() {
        tipsArrayList = ArrayList()
        
        val ref = FirebaseDatabase.getInstance().getReference("Tips")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tipsArrayList.clear()
                for (ds in snapshot.children){
                    val model = ds.getValue(ModelTips::class.java)
                    
                    tipsArrayList.add(model!!)
                }

                adapterTips = AdapterTips(this@TipsActivity, tipsArrayList)
                binding.tipsRv.adapter = adapterTips
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}