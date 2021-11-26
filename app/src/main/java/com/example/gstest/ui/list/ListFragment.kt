package com.example.gstest.ui.list

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.data.models.RequestGeneral
import com.backbase.assignment.data.models.Results
import com.example.gstest.R
import com.example.gstest.data.utils.Locations
import com.example.gstest.data.utils.Nav
import com.example.gstest.data.utils.OnClickList
import com.example.gstest.databinding.FragmentListBinding
import com.example.masterdetail.dbroom.dbmodel.GSTest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(), OnClickList {
    lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by viewModels()
    lateinit var listAdapter: ListAdapter
    @Inject
    lateinit var navigation: Nav
    @Inject
    lateinit var locations: Locations
    private val MY_LOCATION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ListAdapter(this)

        if (checkPermission()) {
            locations.getLocation(requireContext())
        } else {
            requestPermission()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        binding.viewModel = viewModel

        binding.rvResult.adapter = listAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvResult.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        if(validateNetwork()){
            viewModel.deleteData()
            viewModel.onCreateCharacter()
            getCharacter()
        }else{
            viewModel.getData()
            getData()
        }
    }
    private fun getCharacter(){
        viewModel.getList.observe(viewLifecycleOwner, Observer {
                val gson = Gson()
                val data = gson.fromJson(it, RequestGeneral::class.java)
                saveData(data.results)

                binding.pdLoad.hide()
        })


    }

    private fun saveData(list: List<Results>){
        var nl = ArrayList<GSTest>()
        list.map {
            val gsTest = GSTest(it.original_title,it.overview, it.poster_path,it.release_date)
            nl.add(gsTest)
            viewModel.saveData(gsTest)
        }
        listAdapter.addData(nl)
    }

    private fun getData(){
        viewModel.getDataList.observe(viewLifecycleOwner, Observer {
            listAdapter.addData(it)
        })
        binding.pdLoad.hide()
    }

    override fun goToFragment(result: Any, view: View) {
        val bundle = bundleOf("obj" to result.toString())
        navigation.gotoFragment(view,R.id.action_listFragment_to_descriptionFragment,bundle)

    }

    fun validateNetwork(): Boolean{
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            MY_LOCATION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_LOCATION_REQUEST_CODE ->
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                    locations.getLocation(requireContext())
                } else {
                    requestPermission()
                }
        }
    }

}