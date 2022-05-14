package com.example.socialproject.mapTest

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import com.example.socialproject.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.NaverMapSdk.NaverCloudPlatformClient
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import java.util.jar.Manifest
import kotlin.properties.Delegates


class NaverMapTestActivity : AppCompatActivity(), OnMapReadyCallback {
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체
    private lateinit var naverMap: NaverMap
    private lateinit var mapView: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naver_map_test)
        mapView = findViewById(R.id.map)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        NaverMapSdk.getInstance(this).client = NaverCloudPlatformClient("as3jcj8e7i")

        mapView.getMapAsync(this)
        // 위치를 반환하는 구현체인 FusedLocationSource 생성
        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        //naverMap.locationTrackingMode = LocationTrackingMode.Follow

        var currentLocation: Location?

        //권한 설정
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION , android.Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSTION_REQUEST_CODE)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                currentLocation = location

                // 카메라 현재위치로 이동
                val cameraPosition = CameraPosition(
                    LatLng(currentLocation!!.latitude, currentLocation!!.longitude), // 대상 지점
                    17.0, // 줌 레벨
                )

                //현재위치 마커 표시
                val marker = Marker()
                marker.position = LatLng( currentLocation!!.latitude, currentLocation!!.longitude)
                marker.map = naverMap
                naverMap.cameraPosition = cameraPosition
            }
    }
}