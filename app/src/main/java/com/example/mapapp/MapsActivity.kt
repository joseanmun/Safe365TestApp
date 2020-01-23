/*
 * Created by Anselmo Jose Munoz Medina on 1/13/20 11:57 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/13/20 11:57 PM
 *
 */

package com.example.mapapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.example.mapapp.base.BaseActivity
import com.example.mapapp.model.UserModel
import com.example.mapapp.presentation.MapPresenter
import com.example.mapapp.presentation.MapView
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*
import javax.inject.Inject


class MapsActivity : BaseActivity(), OnMapReadyCallback, MapView,
    AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var presenter: MapPresenter

    private var longitude: Double = 41.387154
    private var latitude: Double = 2.167180
    private var checkSelected: Int = 0

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var mMap: GoogleMap
    private lateinit var messages: TimeAgoMessages

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        messages = TimeAgoMessages.Builder().withLocale(Locale.ENGLISH).build()
        spinner!!.setOnItemSelectedListener(this)
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
        mMap.setInfoWindowAdapter(CustomInfoWindowAdapter())

    }

    override var layout = R.layout.activity_maps;


    override fun onViewLoaded() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        showLoader()
        getCurrentLocation()
    }

    var list: ArrayList<Target> = arrayListOf()

    override fun renderCurrentUsers(userList: List<UserModel>) {
        mMap.clear()
        hideLoader()
        list = arrayListOf()
        userList.forEach {
            // Add a marker in Sydney and move the camera
            val poi = LatLng(it.latitude, it.longitude)
            var market = MarkerOptions()
                .position(poi)
                .title(it.name)
            var target = object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    mMap.addMarker(
                        market.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    )
                }
            }
            Picasso.get()
                .load(it.avatar)
                .priority(Picasso.Priority.HIGH)
                .placeholder(R.drawable.ic_cloud_download_light_blue_a200_36dp)
                .error(R.drawable.ic_error_outline_red_200_36dp)
                .resize(200, 200)
                .centerCrop()
                .transform(CircleTransformation())
                .into(target)
            list.add(target)
        }
    }

    override fun renderCurrentUsersOnList(userList: List<UserModel>) {
        checkSelected = 0
        var spinnerAdapter = CustomeSpinnerAdapter(this, userList)
        spinner?.adapter = spinnerAdapter
        spinner!!.setAdapter(spinnerAdapter)
    }

    override fun showError() {
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                longitude = location?.longitude!!
                latitude = location?.latitude
                presenter.getUsersByLatLng(latitude, longitude)
                val currentPosition = LatLng(latitude, longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f))
            }
    }

    internal inner class CustomInfoWindowAdapter : GoogleMap.InfoWindowAdapter {

        // These are both view groups containing an ImageView with id "badge" and two
        // TextViews with id "title" and "snippet".
        private val contents: View = layoutInflater.inflate(R.layout.custom_info_contents, null)

        override fun getInfoWindow(marker: Marker): View? {
            return null
        }

        override fun getInfoContents(marker: Marker): View? {
            render(marker, contents)
            return contents
        }

        private fun render(marker: Marker, view: View) {
            val titleTextView = view.findViewById<TextView>(R.id.name_tv)
            val latTextView = view.findViewById<TextView>(R.id.lat_tv)
            val lngTextView = view.findViewById<TextView>(R.id.lng_tv)
            val activeTextView = view.findViewById<TextView>(R.id.timestamp_tv)
            val motionTextView = view.findViewById<TextView>(R.id.motion_tv)

            val userModel: UserModel? = presenter.getUserByName(marker.title)

            titleTextView.text = userModel?.name
            latTextView.text = userModel?.latitude.toString()
            lngTextView.text = userModel?.longitude.toString()
            activeTextView.text = TimeAgo.using(userModel!!.timestamp!!, messages)
            motionTextView.text = userModel?.motion


        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        if (++checkSelected > 1) {
            val intent =
                UserDetailActivity.newIntent(this, p0!!.adapter.getItem(position) as UserModel)
            startActivity(intent)
        }
    }
}
