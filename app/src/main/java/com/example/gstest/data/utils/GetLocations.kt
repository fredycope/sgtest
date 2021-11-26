package com.example.gstest.data.utils

import com.google.firebase.firestore.QuerySnapshot

interface GetLocations {
    fun getListLocations(result: QuerySnapshot)
}