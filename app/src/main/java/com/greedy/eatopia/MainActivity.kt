package com.greedy.eatopia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.greedy.eatopia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //프래그먼트 리스트 생성
        val fragmentList = listOf(ListFragment(), LikeFragment(), UserFragment())

        //프래그먼트 어댑터 생성 및 저장
        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList

        //adapter 연결
        binding.viewPager.adapter = adapter

        /* TabLayout 추가 시 작성 - 변경불가(var) */
        var tabTitles = listOf<String>("맛집 리스트", "즐겨찾기", "내 정보")

        //TabLayout과 viewPager 연결
        TabLayoutMediator(binding.tabLayout2, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }





}