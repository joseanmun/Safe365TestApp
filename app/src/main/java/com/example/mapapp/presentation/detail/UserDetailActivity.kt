/*
 * Created by Anselmo Jose Munoz Medina on 1/23/20 8:37 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/23/20 8:35 PM
 *
 */

package com.example.mapapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mapapp.R
import com.example.mapapp.model.UserModel
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail.*
import java.util.*


class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = getString(R.string.detail_title)
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra<UserModel>(INTENT_USER_ID)
        val messages = TimeAgoMessages.Builder().withLocale(Locale.ENGLISH).build()

        name_tv!!.text = user.name
        lat_tv!!.text = user.latitude.toString()
        lng_tv!!.text = user.longitude.toString()
        timestamp_tv!!.text = TimeAgo.using(user!!.timestamp!!, messages)
        motion_tv!!.text = user.motion
        Picasso.get()
            .load(user.avatar)
            .priority(Picasso.Priority.HIGH)
            .placeholder(R.drawable.ic_cloud_download_light_blue_a200_36dp)
            .error(R.drawable.ic_error_outline_red_200_36dp)
            .into(badge!!)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context, user: UserModel): Intent {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(INTENT_USER_ID, user)
            return intent
        }
    }


}
