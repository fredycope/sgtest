package com.example.gstest.data.utils

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import javax.inject.Inject

class Nav @Inject constructor() {
    fun gotoFragment(view: View, id: Int, args: Bundle?){
        Navigation.findNavController(view).navigate(id,args)
    }
}