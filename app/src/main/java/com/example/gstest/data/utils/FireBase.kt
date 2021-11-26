package com.example.gstest.data.utils

import android.util.Log

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FireBase @Inject constructor(){
    val db = Firebase.firestore

    fun registerLocation(latitude: Double, longitude: Double){
        val loc = hashMapOf(
            "latitude" to latitude,
            "longitude" to longitude,
        )
        db.collection("locations")
            .add(loc)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

    fun getLocation( getLocations: GetLocations) {

        db.collection("locations")
            .get()
            .addOnSuccessListener { result ->
                getLocations.getListLocations(result)
            }
            .addOnFailureListener { exception ->
                Log.w("DATA TAG", "Error getting documents.", exception)
            }
    }
}