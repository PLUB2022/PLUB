package com.example.socialproject.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.socialproject.HomeActivity
import com.example.socialproject.R
import com.kakao.sdk.common.util.Utility
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton


class LoginActivity : AppCompatActivity() {
    lateinit var mOAuthLoginInstance: OAuthLogin
    lateinit var mContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginbtn = findViewById<OAuthLoginButton>(R.id.loginbtn)

        val naver_client_id = ""
        val naver_client_secret = ""
        val naver_client_name = ""

        mContext = this


        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        loginbtn.setOnClickListener {
            mOAuthLoginInstance = OAuthLogin.getInstance()
            mOAuthLoginInstance.init(
                mContext,
                naver_client_id,
                naver_client_secret,
                naver_client_name
            )
            Log.d(
                "TAG",
                "Nhn status need login"
            )
            mOAuthLoginInstance.startOauthLoginActivity(this, @SuppressLint("HandlerLeak") object: OAuthLoginHandler(){
                override fun run(success: Boolean){
                    if (success) {
                        val accessToken = mOAuthLoginInstance.getAccessToken(this@LoginActivity)
                        val refreshToken = mOAuthLoginInstance.getRefreshToken(this@LoginActivity)
                        val expiresAt = mOAuthLoginInstance.getExpiresAt(this@LoginActivity)
                        val tokenType =
                            mOAuthLoginInstance.getTokenType(this@LoginActivity)
                        Log.d("TAG", "nhn Login Access Token : $accessToken")
                        Log.d("TAG", "nhn Login refresh Token : $refreshToken")
                        Log.d("TAG", "nhn Login expiresAt : $expiresAt")
                        Log.d("TAG", "nhn Login Token Type : $tokenType")
                        Log.d("TAG", "nhn Login Module State : "+mOAuthLoginInstance.getState(this@LoginActivity).toString())
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val errorCode =
                            mOAuthLoginInstance.getLastErrorCode(this@LoginActivity).getCode()
                        val errorDesc =
                            mOAuthLoginInstance.getLastErrorDesc(this@LoginActivity)
                        Log.d("TAG", "errorCode:$errorCode, errorDesc:$errorDesc")
                        Toast.makeText (this@LoginActivity, "errorCode:$errorCode, errorDesc:$errorDesc", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }


    }
}