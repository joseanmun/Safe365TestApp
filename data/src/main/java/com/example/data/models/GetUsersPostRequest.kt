/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:45 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:45 PM
 *
 */

package com.example.data.models

import com.google.gson.annotations.SerializedName

class GetUsersPostRequest(
    @SerializedName("latitude")
    val latitude: Number,
    @SerializedName("longitude")
    val longitude: Number
)