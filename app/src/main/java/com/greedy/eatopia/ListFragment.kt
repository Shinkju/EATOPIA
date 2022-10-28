package com.greedy.eatopia

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //recyclaterView 생성하고 화면에 뿌려주기 (로드되는 갯수 등)
        recyclerView = binding.postsView
        loadData()
        //initScrollListener()

    }

    // API를 요청하는 코드
    private fun loadData() {

        CoroutineScope(Dispatchers.Main).launch {

            //API 요청 블럭 : PostsService.getPostsService().posts()
            withContext(Dispatchers.IO) {
                val response = PostsService.getPostsService().posts()   //posts()로 결과를 받는다.
                if (response.isSuccessful) {    //요청이 성공이라면?
                    responseData = response.body()!!
                    Log.d("Success", "${responseData}")
                    dataList = responseData.LOCALDATA_072404_JN.row  //list로 선언되어있기 때문에 posts.posts로 표현
                } else {
                    Log.d("Error", "${response.message()}")
                }
            }
            /* recyclerView를 쓸 때 꼭 필요한 adapter */
//            adapter = FragmentAdapterNew(dataList)
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }


    //로드 시 스크롤 관련
//    private fun initScrollListener() {
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
//                /* 로드 중일때 는 추가로 더 로드하지 않도록 방지하는 구문이다!!! */
//                if (!isLoading) {                       //position이 0~19 이면 이라는 조건! -> 20개씩 로드해서 보여줄거니까~
//                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == postList.size - 1) {
//                        //bottom of list!
//                        loadMore()  //loadMore()로 넘겨서 로딩 되는 코드 작업을 진행
//                        isLoading = true   //상태를 false에서 true로 변경해준다.
//                    }
//                }
//            }
//        })
//    }


    //데이터 조회 시 추가 조건 (화면에 보여지는 조건)
//    private fun loadMore() {
//
//        CoroutineScope(Dispatchers.Main).launch {
//            //토탈(150개) 보다 작으면 계속 20개씩 로드 해라
//            val skip = if (posts.skip + 20 < posts.total) posts.skip + 20 else posts.total
//
//            /* 네트워크 통신 하는 코드!!!!!! */
//            withContext(Dispatchers.IO) {
//                val response = PostsService.getPostsService().posts(skip)
//
//                if (response.isSuccessful) {
//                    posts = response.body()!!
//                    Log.d("posts", posts.toString())
//                    //20개가 추가될 시 기존 목록(postList)에 20개의 데이터를 모두 추가(addAll)한다.
//                   // postList.addAll(posts.posts.toMutableList())
//
//                } else {
//                    Log.d("Error", "${response.message()}")
//                }
//            }
//
//            adapter.notifyDataSetChanged()  //adapter쪽으로 화면이 변경된 것을 알려준다.
//            isLoading = false               //상태를 다시 false로 변경하여 로딩 가능하도록 한다.
//        }
//    }
}