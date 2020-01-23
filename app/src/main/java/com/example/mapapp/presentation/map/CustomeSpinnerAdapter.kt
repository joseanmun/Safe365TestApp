/*
 * Created by Anselmo Jose Munoz Medina on 1/23/20 8:36 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/23/20 8:36 PM
 *
 */

package com.example.mapapp.presentation.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mapapp.R
import com.example.mapapp.model.UserModel
import com.example.mapapp.presentation.utils.CircleTransformation
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import com.squareup.picasso.Picasso
import java.util.*


class CustomeSpinnerAdapter(val context: Context, var userList: List<UserModel>) : BaseAdapter() {


    val mInflater: LayoutInflater = LayoutInflater.from(context)
    val messages = TimeAgoMessages.Builder().withLocale(Locale.ENGLISH).build();

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.custom_spinner_row, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }

        // setting adapter item height programatically.

        //val params = view.layoutParams
        //params.height = 60
        //view.layoutParams = params
        var userModel = userList.get(position)
        vh.titleTextView.text = userModel.name
        vh.latTextView.text = userModel.latitude.toString()
        vh.lngTextView.text = userModel.longitude.toString()
        vh.activeTextView.text = TimeAgo.using(userModel.timestamp, messages)
        vh.motionTextView.text = userModel.motion
        Picasso.get()
            .load(userModel.avatar)
            .priority(Picasso.Priority.HIGH)
            .placeholder(R.drawable.ic_cloud_download_light_blue_a200_36dp)
            .error(R.drawable.ic_error_outline_red_200_36dp)
            .resize(200, 200)
            .centerCrop()
            .transform(CircleTransformation())
            .into(vh.iconImageView)
        return view
    }

    override fun getItem(position: Int): Any? {

        return userList.get(index = position)

    }

    override fun getItemId(position: Int): Long {

        return 0

    }

    override fun getCount(): Int {
        return userList.size
    }

    private class ItemRowHolder(view: View?) {
        val iconImageView: ImageView
        val titleTextView: TextView
        val latTextView: TextView
        val lngTextView: TextView
        val activeTextView: TextView
        val motionTextView: TextView

        init {
            this.iconImageView = view!!.findViewById<ImageView>(R.id.badge)
            this.titleTextView = view!!.findViewById<TextView>(R.id.name_tv)
            this.latTextView = view!!.findViewById<TextView>(R.id.lat_tv)
            this.lngTextView = view!!.findViewById<TextView>(R.id.lng_tv)
            this.activeTextView = view!!.findViewById<TextView>(R.id.timestamp_tv)
            this.motionTextView = view!!.findViewById<TextView>(R.id.motion_tv)
        }
    }
}