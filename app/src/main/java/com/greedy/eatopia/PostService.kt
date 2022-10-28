package com.greedy.eatopia

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsService {
    //PostApi 인터페이스 구현체를 반환한다.
    fun getPostsService() : PostAPI {

        return Retrofit.Builder()
            //json 내용이 들어있는 api url을 등록.
            .baseUrl("http://openapi.seoul.go.kr:8088/697149644872657339384446586255/json/LOCALDATA_072404_JN/")
            .addConverterFactory(GsonConverterFactory.create()) //json데이터가 컴벌트 될 수 있도록 하는 구문(의존성 추가)
            .build()
            .create(PostAPI::class.java)   //사용할 메소드가 담긴 인터페이스 : PostApi

    }
}