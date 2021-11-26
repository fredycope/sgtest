package com.example.gstest.ui.locations

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.gstest.R
import com.example.gstest.data.utils.FireBase
import com.example.gstest.data.utils.GetLocations
import com.example.gstest.databinding.FragmentDescriptionBinding
import com.example.gstest.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapsFragment : Fragment(), GetLocations {
    lateinit var binding: FragmentMapsBinding
    @Inject
    lateinit var fireBase: FireBase
    lateinit var mapFragment :SupportMapFragment



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_maps, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fireBase.getLocation(this)

    }

    override fun getListLocations(result: QuerySnapshot) {
        mapFragment?.getMapAsync(){googleMap ->
            for (document in result) {
                val loc = LatLng(document.data.get("latitude").toString().toDouble(), document.data.get("longitude").toString().toDouble())
                googleMap.addMarker(MarkerOptions().position(loc).title("No title"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
            }

        }
    }

}