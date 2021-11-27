package com.example.gstest.data.network

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import java.io.File
import javax.inject.Inject

class FireStorage @Inject constructor(){
   val storage = Firebase.storage

    fun uploadImage(img: String, fileUri: Uri, context: Context){
        val storageRef = storage.reference

      val file = Uri.fromFile(File(img))
      var metadata = storageMetadata {
            contentType = "image/jpeg"
        }

        var uploadTask = storageRef.child("Images").child(img).putFile(fileUri)

        uploadTask.addOnProgressListener {
            val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
            Log.d("TAG", "Upload is $progress% done")
        }.addOnPausedListener {
            Log.d("TAG", "Upload is paused")

        }.addOnFailureListener {

        }.addOnSuccessListener {
            Toast.makeText(context,"Imagen enviada con exito",Toast.LENGTH_SHORT).show()
        }
    }
}