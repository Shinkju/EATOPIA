package com.greedy.eatopia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greedy.eatopia.databinding.FragmentList2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragment : Fragment() {

    private val binding by lazy { FragmentList2Binding.inflate(layoutInflater) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FragmentAdapterNew
    private lateinit var responseData: RestaurantResponse
    private lateinit var dataList: List<Row>
    private var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.recycler_list, container, false)

        // Inflate the layout for this fragment
        recyclerView = binding.postsView
        loadData()
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        initScrollListener()


        val ratingBar: RatingBar = v.findViewById(R.id.ratingBar)

        ratingBar.onRatingBarChangeListener  =
            RatingBar.OnRatingBarChangeListener {
                    ratingBar, rating, fromUser ->
            }

        binding.goLogin.setOnClickListener{
            val intent = Intent(it.context, JoinActivity::class.java)
            startActivity(intent)
        }


        return binding.root


    }

    // API를 요청하는 코드
    private fun loadData() {

        CoroutineScope(Dispatchers.Main).launch {

            //API 요청 블럭 : PostsService.getPostsService().posts()
            withContext(Dispatchers.IO) {
                val response = PostsService.getPostsService().row()   //posts()로 결과를 받는다.
                if (response.isSuccessful) {    //요청이 성공이라면?
                    responseData = response.body()!!
                    Log.d("Success", "${responseData}")
                    dataList = responseData.LOCALDATA_072404_JN.row
                    Log.d("Success", "${dataList}")
                } else {
                    Log.d("Error", "${response.message()}")
                }
            }
            /* recyclerView를 쓸 때 꼭 필요한 adapter */
            adapter = FragmentAdapterNew(dataList)
            recyclerView.adapter = adapter


        }
    }


    //로드 시 스크롤 관련
    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                /* 로드 중일때 는 추가로 더 로드하지 않도록 방지하는 구문이다!!! */
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == dataList.size - 1) {
                        //bottom of list!
                        //loadMore()  //loadMore()로 넘겨서 로딩 되는 코드 작업을 진행
                        isLoading = true
                    }
                }
            }
        })
    }

}