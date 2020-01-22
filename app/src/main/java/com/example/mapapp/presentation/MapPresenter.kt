/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 11:28 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 12/13/18 5:28 PM
 *
 */

package com.example.mapapp.presentation

import com.example.domain.bean.UserBusinessObject
import com.example.domain.interactor.GetUserListUseCase
import com.example.mapapp.injection.scope.PerActivity
import com.example.mapapp.mappers.UserModelMapper
import javax.inject.Inject

/**
 * Created by AlbertM on 12/12/18.
 */
@PerActivity
class MapPresenter @Inject constructor(
    val view: MapView,
    val getUserListUseCase: GetUserListUseCase
) {


    fun execute(lat: Number, lon: Number) {
        getUserListUseCase.execute(lat, lon, object : GetUserListUseCase.Callback {
            override fun onCurrentUsersLoaded(userList: List<UserBusinessObject>) {
                val mapper = UserModelMapper()
                val userModelList = mapper.toUserModel(userList)
                view.renderCurrentUsers(userModelList)
            }

            override fun onError() {
                view.showError()
            }

        })

    }

}