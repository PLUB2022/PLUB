package com.example.socialproject.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.socialproject.HomeActivity
import com.example.socialproject.R
import com.example.socialproject.assign.AssignActivity
import com.example.socialproject.common.Retrofit2Client
import com.example.socialproject.mapTest.MapTestActivity
import com.kakao.sdk.common.util.Utility
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton
import retrofit2.Call
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class LoginActivity : AppCompatActivity() {
    private lateinit var mOAuthLoginInstance: OAuthLogin
    private lateinit var mContext: Context
    val api: LoginService = Retrofit2Client.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<OAuthLoginButton>(R.id.loginbtn)
        val testLogoutBtn = findViewById<TextView>(R.id.test_logoutbtn)
        val testMapBtn = findViewById<TextView>(R.id.test_mapbtn)
        val assign = findViewById<TextView>(R.id.assignbtn)

        val naverClientId = "mzfqe9XHL4Ipv4ncF9vH"
        val naverClientSecret = "G2QHIjqGdD"
        val naverClientName = "PLUB"

        mContext = this

        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(
            mContext,
            naverClientId,
            naverClientSecret,
            naverClientName
        )

        assign.setOnClickListener {
            val intent = Intent(this, AssignActivity::class.java)
            startActivity(intent)
            finish()
        }

        testMapBtn.setOnClickListener {
            val intent = Intent(this, MapTestActivity::class.java)
            startActivity(intent)
            finish()
        }


        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        testLogoutBtn.setOnClickListener {
            Thread{
                mOAuthLoginInstance.logoutAndDeleteToken(this)
            }.start()
        }
        loginBtn.setOnClickListener {
            Log.d(
                "TAG",
                "Nhn status need login"
            )
            mOAuthLoginInstance.startOauthLoginActivity(
                this,
                @SuppressLint("HandlerLeak") object : OAuthLoginHandler() {
                    override fun run(success: Boolean) {
                        if (success) {
                            val accessToken = mOAuthLoginInstance.getAccessToken(this@LoginActivity)
                            val refreshToken =
                                mOAuthLoginInstance.getRefreshToken(this@LoginActivity)
                            val expiresAt = mOAuthLoginInstance.getExpiresAt(this@LoginActivity)
                            val tokenType =
                                mOAuthLoginInstance.getTokenType(this@LoginActivity)
                            Log.d("TAG", "nhn Login Access Token : $accessToken")
                            Log.d("TAG", "nhn Login refresh Token : $refreshToken")
                            Log.d("TAG", "nhn Login expiresAt : $expiresAt")
                            Log.d("TAG", "nhn Login Token Type : $tokenType")
                            Log.d(
                                "TAG",
                                "nhn Login Module State : " + mOAuthLoginInstance.getState(this@LoginActivity)
                                    .toString()
                            )
                            val token = "$accessToken" // 네이버 로그인 접근 토큰;

                            // 서버로 통신 (accessToken 값 보내기 GET으로!)
                            api.getNaverLogin(accessToken).enqueue(object : retrofit2.Callback<String> {
                                override fun onResponse(call: Call<String>, response: Response<String>) {
                                    Log.d("log",response.toString())
                                    Log.d("log", response.body().toString())

                                    Toast.makeText(applicationContext, "btn click", Toast.LENGTH_SHORT).show()
                                }
                                override fun onFailure(call: Call<String>, t: Throwable) {
                                    // 실패
                                    Log.d("log",t.message.toString())
                                    Log.d("log","fail")

                                    Toast.makeText(applicationContext, "btn click22222", Toast.LENGTH_SHORT).show()
                                }
                            })

                            val header = "Bearer $token" // Bearer 다음에 공백 추가


                            val apiURL = "https://openapi.naver.com/v1/nid/me"


                            val requestHeaders: MutableMap<String, String> = HashMap()
                            requestHeaders["Authorization"] = header
                            var responseBody = ""

                            val con = connect(apiURL)
                            try {
                                Thread{
                                    con.requestMethod = "GET"
                                    for ((key, value) in requestHeaders) {
                                        con.setRequestProperty(key, value)
                                    }

                                    val responseCode = con.responseCode
                                    if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                                        responseBody = readBody(con.inputStream)
                                    } else { // 에러 발생
                                        responseBody = readBody(con.errorStream)
                                    }
                                    println(responseBody)
                                    val intent = Intent(applicationContext, HomeActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }.start()
                            } catch (e: IOException) {
                                throw RuntimeException("API 요청과 응답 실패", e)
                            } finally {
                                con.disconnect()
                            }


                        } else {
                            val errorCode =
                                mOAuthLoginInstance.getLastErrorCode(this@LoginActivity).getCode()
                            val errorDesc =
                                mOAuthLoginInstance.getLastErrorDesc(this@LoginActivity)
                            Log.d("TAG", "errorCode:$errorCode, errorDesc:$errorDesc")
                            Toast.makeText(
                                this@LoginActivity,
                                "errorCode:$errorCode, errorDesc:$errorDesc",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })

        }


    }

    private fun connect(apiUrl: String): HttpURLConnection {
        return try {
            val url = URL(apiUrl)
            url.openConnection() as HttpURLConnection
        } catch (e: MalformedURLException) {
            throw RuntimeException("API URL이 잘못되었습니다. : $apiUrl", e)
        } catch (e: IOException) {
            throw RuntimeException("연결이 실패했습니다. : $apiUrl", e)
        }
    }


    private fun readBody(body: InputStream): String {
        val streamReader = InputStreamReader(body)
        try {
            BufferedReader(streamReader).use { lineReader ->
                val responseBody = StringBuilder()
                var line: String?
                while (lineReader.readLine().also { line = it } != null) {
                    responseBody.append(line)
                }
                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
        }
    }

}