/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:32 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:32 PM
 *
 */

package com.example.domain.interactor

import com.example.domain.bean.UserBusinessObject

interface GetUserListUseCase {
    interface Callback {
        fun onCurrentUsersLoaded(userList: List<UserBusinessObject>)
        fun onError()
    }

    fun execute(lat: Number, lon: Number, callback: Callback)
}