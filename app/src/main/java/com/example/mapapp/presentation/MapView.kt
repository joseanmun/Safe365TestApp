/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 11:26 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 11:26 PM
 *
 */

package com.example.mapapp.presentation

import com.example.mapapp.model.UserModel

interface MapView {
    fun renderCurrentUsers(userList: List<UserModel>)
    fun renderCurrentUsersOnList(userList: List<UserModel>)
    fun showError()
}