package com.example.gstest.ui.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.gstest.R
import com.example.gstest.databinding.FragmentDescriptionBinding
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import com.backbase.assignment.data.models.Results
import com.example.masterdetail.dbroom.dbmodel.GSTest
import com.google.gson.Gson


@AndroidEntryPoint
class DescriptionFragment : Fragment() {
    lateinit var binding: FragmentDescriptionBinding

    lateinit var obj: GSTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val gson = Gson()
            obj = gson.fromJson(it?.get("obj").toString(), GSTest::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_description, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.ivPosterdetail).load("https://image.tmdb.org/t/p/w500${obj.posterPath}").into(binding.ivPosterdetail)
        binding.tvTitle.text =obj.originalTitle
        binding.tvOverview.text = obj.overview
        binding.tvTxt.text = obj.releaseDate

        initListener()
    }

    private fun initListener(){
        binding.fabInfo.setOnClickListener {
            val compartir = Intent(Intent.ACTION_SEND)
            compartir.type = "text/plain"
            val mensaje = "${binding.tvTitle.text} \n ${binding.tvOverview.text} \n ${binding.tvTxt.text}"
            compartir.putExtra(Intent.EXTRA_SUBJECT, "GSTest")
            compartir.putExtra(Intent.EXTRA_TEXT, mensaje)
            startActivity(Intent.createChooser(compartir, "Compartir vía"))
        }
    }

}