package com.greedy.eatopia

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostAPI {

    //Post 목록 전체 조회  -  skip과 limit는 몇개 씩 끊어서 로드할건지 정하는거!
    @GET("{startNum}/{endNum}")
    suspend fun row(@Path("startNum") startNum: Int = 1, @Path("endNum") endNum: Int = 50) : Response<RestaurantResponse>

    //Post 1개 상세 조회
    @GET("{CODE}")
    suspend fun result(@Path("CODE") CODE: Int) : Response<RestaurantResponse>

    //Post에 해당하는 comment 조회
  /*  @GET("data/{id}/comments")
    suspend fun comments*/




}