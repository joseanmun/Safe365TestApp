/*
 * Created by Anselmo Jose Munoz Medina on 1/23/20 8:36 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/23/20 8:22 PM
 *
 */

package com.example.mapapp.presentation.map

import com.example.domain.bean.UserBusinessObject
import com.example.domain.interactor.GetUserListUseCase
import com.example.mapapp.injection.scope.PerActivity
import com.example.mapapp.mappers.UserModelMapper
import com.example.mapapp.model.UserModel
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

/**
 * Created by AlbertM on 12/12/18.
 */
@PerActivity
class MapPresenter @Inject constructor(
    val view: MapView,
    val getUserListUseCase: GetUserListUseCase
) {
    lateinit var userListMapResult: List<UserModel>
    lateinit var userListResult: List<UserModel>
    val delay: Long = 10000


    fun getUsersByLatLng(lat: Number, lon: Number) {
        getUserListUseCase.execute(lat, lon, object : GetUserListUseCase.Callback {
            override fun onCurrentUsersLoaded(userList: List<UserBusinessObject>) {
                val mapper = UserModelMapper()
                val userModelList = mapper.toUserModel(userList)
                userListMapResult = userModelList
                userListResult = userModelList
                view.renderCurrentUsers(userModelList)
                view.renderCurrentUsersOnList(userModelList)
                scheduleUpdate(lat, lon)
            }

            override fun onError() {
                view.showError()
            }

        })
    }

    private fun getUsersByLatLngForList(lat: Number, lon: Number) {
        getUserListUseCase.execute(lat, lon, object : GetUserListUseCase.Callback {
            override fun onCurrentUsersLoaded(userList: List<UserBusinessObject>) {
                val mapper = UserModelMapper()
                val userModelList = mapper.toUserModel(userList)
                userListResult = userModelList
                view.renderCurrentUsersOnList(userModelList)
                scheduleUpdate(lat, lon)
            }

            override fun onError() {
                view.showError()
            }

        })
    }

    private fun scheduleUpdate(lat: Number, lon: Number) {
        Timer("SettingUp", false).schedule(delay) {
            getUsersByLatLngForList(lat, lon)
        }
    }

    fun getUserByName(name: String): UserModel? {
        userListMapResult.forEach {
            if (it.name.equals(name)) {
                return it
            }
        }
        return null
    }

}