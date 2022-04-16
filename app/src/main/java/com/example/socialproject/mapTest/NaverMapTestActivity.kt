package com.example.socialproject.mapTest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socialproject.R
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.NaverMapSdk.NaverCloudPlatformClient


class NaverMapTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naver_map_test)

        NaverMapSdk.getInstance(this).client = NaverCloudPlatformClient("as3jcj8e7i")
    }
}