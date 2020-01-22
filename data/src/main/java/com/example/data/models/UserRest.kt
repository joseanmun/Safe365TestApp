/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:44 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:28 PM
 *
 */

package com.example.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

class UserRest(
    @SerializedName("name")
    val name: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timestamp")
    val timestamp: Date,
    @SerializedName("motion")
    val motion: String
)