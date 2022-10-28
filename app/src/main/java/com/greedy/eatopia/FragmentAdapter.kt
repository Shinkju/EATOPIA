package com.greedy.eatopia

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    /* 뷰 페이저의 화면 아이템은 개수가 변하지 않고 처음 정해진만큼 사용하므로 mutable이 아닌 list로 선언 */
    var fragmentList = listOf<Fragment>()

    /* 어댑터가 화면에 보여줄 전체 프래그먼트의 갯수 반환 */
    override fun getItemCount(): Int {
        return fragmentList.size    //size를 사용하면 갯수 반환 가능
    }

    /* 현재 페이지의 position이 파라미터로 넘어오므로
    * position에 해당하는 위치의 프래그먼트를 반환해야 한다. */
    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)   //fragmentList의 위치를 찾아서 반환한다.
    }
}