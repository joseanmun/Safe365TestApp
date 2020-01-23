/*
 * Created by Anselmo Jose Munoz Medina on 1/13/20 11:57 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/13/20 11:57 PM
 *
 */

package com.example.mapapp

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import com.example.mapapp.base.BaseActivity
import com.example.mapapp.model.UserModel
import com.example.mapapp.presentation.MapPresenter
import com.example.mapapp.presentation.MapView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject


class MapsActivity : BaseActivity(), OnMapReadyCallback, MapView {

    @Inject
    lateinit var presenter: MapPresenter

    private var longitude: Double = 41.387154
    private var latitude: Double = 2.167180

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override var layout = R.layout.activity_maps;


    override fun onViewLoaded() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        showLoader()
        getCurrentLocation()
    }

    override fun renderCurrentUsers(userList: List<UserModel>) {
        hideLoader()
        userList.forEach {
            // Add a marker in Sydney and move the camera
            val poi = LatLng(it.latitude, it.longitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(poi)
                    .title(it.name)
            )
        }
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                longitude = location?.longitude!!
                latitude = location?.latitude
                presenter.execute(latitude, longitude)
                val currentPosition = LatLng(latitude, longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f))
            }
    }
}
