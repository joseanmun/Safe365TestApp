/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:53 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:53 PM
 *
 */

package com.example.data.mappers

import com.example.data.models.UserRest
import com.example.domain.bean.UserBusinessObject

class UserBusinessObjectMapper {

    fun toUserBusinessObject(userRest: UserRest): UserBusinessObject = UserBusinessObject(
        userRest.name,
        userRest.avatar,
        userRest.latitude,
        userRest.longitude,
        userRest.timestamp,
        userRest.motion
    )

    fun toUserBusinessObject(userRestList: List<UserRest>) = userRestList.map {
        toUserBusinessObject(it)
    }

}