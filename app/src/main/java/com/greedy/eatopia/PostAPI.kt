package com.greedy.eatopia

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostAPI {

    //Post 목록 전체 조회  -  skip과 limit는 몇개 씩 끊어서 로드할건지 정하는거!
    @GET("{startNum}/{endNum}")
    suspend fun posts(@Path("startNum") startNum: Int = 1, @Path("endNum") endNum: Int = 5) : Response<RestaurantResponse>

    //Post 1개 상세 조회
    @GET("posts/{id}")
    suspend fun post(@Path("id") id: Int) : Response<RestaurantResponse>

    //Post에 해당하는 comment 조회
  /*  @GET("data/{id}/comments")
    suspend fun comments*/




}