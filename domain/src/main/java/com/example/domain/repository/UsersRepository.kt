/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:30 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:29 PM
 *
 */

package com.example.domain.repository

import com.example.domain.bean.UserBusinessObject

interface UsersRepository {

    interface UsersRepositoryCallback {
        fun onCurrentUsersLoaded(userList: List<UserBusinessObject>)
        fun onError()

    }

    fun getCurrentWeather(lat: Number, lon: Number, callback: UsersRepositoryCallback)
}