/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 10:41 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:41 PM
 *
 */

package com.example.data.net

import com.example.data.models.GetUsersPostRequest
import com.example.data.models.UserRest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersApiService {

    @Headers("Content-Type: application/json")
    @POST("users")
    fun getUsersByLocation(
        @Body getUsersPostRequest: GetUsersPostRequest
    ): Call<List<UserRest>>

}