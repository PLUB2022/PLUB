package com.example.socialproject.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.socialproject.HomeActivity
import com.example.socialproject.R
import com.kakao.sdk.common.util.Utility

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginbtn = findViewById<Button>(R.id.loginbtn)
        val kakaobtn = findViewById<Button>(R.id.kakaologinbtn)
        val googlebtn = findViewById<Button>(R.id.googleloginbtn)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        loginbtn.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        kakaobtn.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        googlebtn.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}