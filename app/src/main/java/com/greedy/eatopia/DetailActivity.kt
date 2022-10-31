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

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var rows: Row
    private lateinit var listR: List<Row>
    //private lateinit var responseData: RestaurantResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //BPLCNM를 조회 해서 해당 데이터 조회
        val rowBPLCNM = intent.getStringExtra("BPLCNM")
        loadData(rowBPLCNM)

    }

    //디테일 화면에서 내용, 코멘트 조회
    private fun loadData(rowBPLCNM: String?) {

        CoroutineScope(Dispatchers.Main).launch {
            //네트워크 통신
            withContext(Dispatchers.IO) {
                val rowResponse = PostsService.getPostsService().rows("BPLCNM")
                //val commentsResponse = PostsService.getPostsService().comments(postId)
                if (rowResponse.isSuccessful) {
                    listR = rowResponse.body()!!.LOCALDATA_072404_JN.row
                    Log.d("listR"," ${listR}")
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

            binding.layout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

        }


    }

}