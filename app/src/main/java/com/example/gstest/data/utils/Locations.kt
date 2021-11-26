package com.example.gstest.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import javax.inject.Inject

class Locations @Inject constructor() {
    @Inject
    lateinit var fireBase: FireBase

    private lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
     fun getLocation(context: Context) {
        val locationRequest = LocationRequest.create().apply {
            interval = 60000 * 5
            fastestInterval = 60000 * 5
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    fireBase.registerLocation(location.latitude, location.longitude)
                    Toast.makeText(context,"LOCATION ${location.latitude} ${location.longitude}", Toast.LENGTH_LONG).show()
                }
            }
        }
        val fusedLocationClient = FusedLocationProviderClient(context)
        fusedLocationClient.requestLocationUpdates(locationRequest,
            locationCallback,
            Looper.getMainLooper())

    }
}