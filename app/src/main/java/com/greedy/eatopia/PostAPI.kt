package com.greedy.eatopia

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostAPI {

    @GET("{startNum}/{endNum}")
    suspend fun row(@Path("startNum") startNum: Int = 1, @Path("endNum") endNum: Int = 50) : Response<RestaurantResponse>




}