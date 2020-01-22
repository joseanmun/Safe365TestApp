/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 11:04 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 12/14/18 2:17 AM
 *
 */

package com.example.data.datasources

import com.example.data.mappers.UserBusinessObjectMapper
import com.example.data.models.GetUsersPostRequest
import com.example.data.models.UserRest
import com.example.data.net.UsersApiService
import com.example.domain.bean.UserBusinessObject
import com.example.domain.repository.UsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by AlbertM on 12/12/18.
 */
class UsersApiDataSource @Inject constructor(
    private val retrofit: Retrofit
) : UsersRepository {


    private var userBOList: List<UserBusinessObject>? = null

    override fun getCurrentWeather(
        lat: Number,
        lon: Number,
        callback: UsersRepository.UsersRepositoryCallback
    ) {

        val usersApiService = retrofit.create(UsersApiService::class.java)
        val res: Call<List<UserRest>> =
            usersApiService.getUsersByLocation(GetUsersPostRequest(lat, lon))

        run {
            res.enqueue(object : Callback<List<UserRest>> {
                override fun onFailure(call: Call<List<UserRest>>?, t: Throwable?) {
                    callback.onError()
                }

                override fun onResponse(
                    call: Call<List<UserRest>>?,
                    response: Response<List<UserRest>>?
                ) {
                    val body = response?.body()?.let { it }
                        ?: throw Exception("Error downloading data")
                    userBOList = UserBusinessObjectMapper().toUserBusinessObject(body)
                    callback.onCurrentUsersLoaded(userBOList!!)
                }
            })

        }

    }


}

