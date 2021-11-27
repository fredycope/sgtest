package com.example.gstest.ui

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gstest.R
import com.example.gstest.data.network.FireStorage
import com.example.gstest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.provider.OpenableColumns




@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var fireStorage: FireStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        initListener()
    }

    private fun initListener(){
        binding.fabInfo.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            startActivityForResult(gallery, 100)
            //val compartir = Intent()
            //compartir.type = "image/*"
            /*compartir.action=Intent.ACTION_GET_CONTENT
            compartir.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            startActivityForResult(Intent.createChooser(compartir,"Picture"), 100)
            */
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("DATA ${data?.clipData} ${data?.dataString}")
        for (i in 0 until data?.clipData!!.itemCount){
            val uri = data?.clipData!!.getItemAt(i).uri
            val fileName = getFileName(uri)

            fireStorage.uploadImage(fileName!!,uri, this)
        }


    }

    fun getFileName(uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor?.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result!!.lastIndexOf('/')
            if (cut != -1) {
                result = result.substring(cut + 1)
            }
        }
        return result
    }
}