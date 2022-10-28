package com.greedy.eatopia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedy.eatopia.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //postId 조회
        //val postId = intent.getIntExtra("postId", 0)
      /*  loadData(postId)*/

    }

  /*  //detail에서 post 내용과 코멘트 데이터 조회
    private fun loadData(postId: Int) {

        CoroutineScope(Dispatchers.Main).launch {

            //네트워크 통신
            withContext(Dispatchers.IO) {
                val postResponse = PostsService.getPostService().post(postId)    //postService는 json 필요ㅠ
                val commentsResponse = PostsService.getPostsService().comments(postId)
                if(postResponse.isSuccessful && commentsResponse.inSuccessful) {

                }

            }
        }
    }*/
}