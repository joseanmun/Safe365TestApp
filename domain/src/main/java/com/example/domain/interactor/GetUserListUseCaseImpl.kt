/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:34 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:34 PM
 *
 */

package com.example.domain.interactor

import com.example.domain.bean.UserBusinessObject
import com.example.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserListUseCaseImpl @Inject constructor(private val usersRepository: UsersRepository) :
    GetUserListUseCase, UsersRepository.UsersRepositoryCallback {

    lateinit var callbackInt: GetUserListUseCase.Callback

    override fun onError() {
        callbackInt.onError()
    }


    override fun onCurrentUsersLoaded(userList: List<UserBusinessObject>) {
        callbackInt.onCurrentUsersLoaded(userList)
    }

    override fun execute(lat: Number, lon: Number, callback: GetUserListUseCase.Callback) {
        callbackInt = callback
        usersRepository.getCurrentWeather(lat, lon, this)
    }


}