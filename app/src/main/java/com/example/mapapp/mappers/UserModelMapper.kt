/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 11:22 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 11:03 PM
 *
 */

package com.example.mapapp.mappers

import com.example.domain.bean.UserBusinessObject
import com.example.mapapp.model.UserModel

class UserModelMapper {

    fun toUserModel(userBusinessObject: UserBusinessObject): UserModel = UserModel(
        userBusinessObject.name,
        userBusinessObject.avatar,
        userBusinessObject.latitude,
        userBusinessObject.longitude,
        userBusinessObject.timestamp,
        userBusinessObject.motion
    )

    fun toUserModel(userBusinessObjectList: List<UserBusinessObject>) = userBusinessObjectList.map {
        toUserModel(it)
    }

}