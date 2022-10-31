package com.greedy.eatopia

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {

    // getUltraSrtFcst : 초단기 예보 조회 + 인증키
    @GET("getUltraSrtFcst?serviceKey=h%2BsXoWQXwcaSvPsrZnt%2FI6h3S7lOrTwkNpsQfjRr2r9m%2Fzi2ocgCqmR9gkKZxcpcFYbi%2BhEMEvU7v43bhn6nKQ%3D%3D")

    fun GetWeather(@Query("numOfRows") num_of_rows : Int,   // 한 페이지 경과 수
                   @Query("pageNo") page_no : Int,          // 페이지 번호
                   @Query("dataType") data_type : String,   // 응답 자료 형식
                   @Query("base_date") base_date : String,  // 발표 일자
                   @Query("base_time") base_time : String,  // 발표 시각
                   @Query("nx") nx : String,                // 예보지점 X 좌표
                   @Query("ny") ny : String)                // 예보지점 Y 좌표
            : Call<WeatherResponse>

}




// retrofit을 사용하기 위한 빌더 생성
private val retrofit = Retrofit.Builder()
    .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiObject {
    val retrofitService: WeatherInterface by lazy {
        retrofit.create(WeatherInterface::class.java)
    }
}