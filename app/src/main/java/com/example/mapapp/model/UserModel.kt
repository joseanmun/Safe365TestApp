/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 11:21 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:28 PM
 *
 */

package com.example.mapapp.model

import java.util.*

class UserModel(
    val name: String,
    val avatar: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Date,
    val motion: String
)