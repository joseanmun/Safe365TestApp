/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:25 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:25 PM
 *
 */

package com.example.domain.bean

import java.util.*

class UserBusinessObject(
    val name: String,
    val avatar: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Date,
    val motion: String
)