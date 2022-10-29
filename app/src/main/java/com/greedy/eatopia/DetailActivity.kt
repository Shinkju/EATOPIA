package com.greedy.eatopia

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.greedy.eatopia.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.http.GET

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var rows: Row
    private lateinit var responseData: RestaurantResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //rowBPLCNM를 조회 해서 해당 데이터 조회
        val rowBPLCNM = intent.getStringExtra("BPLCNM")
        loadData(rowBPLCNM.toString())

    }

    //디테일 화면에서 내용, 코멘트 조회
    private fun loadData(rowBPLCNM: String) {

        CoroutineScope(Dispatchers.Main).launch {
            //네트워크 통신
            withContext(Dispatchers.IO) {
                val rowResponse = PostsService.getPostsService().rows("rowBPLCNM")
                //val commentsResponse = PostsService.getPostsService().comments(postId)
                if (rowResponse.isSuccessful) {
                    rows = rowResponse.body()!!
                    //comments = commentsResponse.body()!!
                } else {
                    Log.d("Error", "${rowResponse.message()}")
                    //Log.d("Error", "${commentsResponse.message()}")
                }
            }

            //화면에 뿌려주는 코드
            binding.title.text = rows.BPLCNM
            binding.address.text = rows.SITEWHLADDR
            binding.foodList.text = rows.UPTAENM
            binding.tell.text = rows.SITETEL
            //binding.commentCount.text = "comments(${comments.total})"             //코멘트

            /*
            * adapter = CommentsAdapter(comments.comments)
            binding.commentRecyclerView.adapter = adapter
            binding.commentRecyclerView.layoutManager = LinearLayoutManager(baseContext)
            * */

            /*로드 중에는 layout이 보이지 않도록 하다가,
            로드가 완료되면 프로그래스바가 보이지 않고 layout이 보이도록 상태를 바꿔주는 코드!*/
            binding.layout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

        }


    }

}