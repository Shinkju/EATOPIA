package com.greedy.eatopia

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.greedy.eatopia.databinding.ActivityDetailBinding
import com.greedy.eatopia.databinding.RecyclerListBinding

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



class FragmentAdapterNew(var dataList: List<Row?>) : RecyclerView.Adapter<PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return if (dataList == null) 0 else dataList!!.size
    }

    override fun onBindViewHolder(viewHolder: PostHolder, position: Int) {
        val item = dataList!![position]
        viewHolder.setItem(item)
    }

}



// 리스트를 볼수 있도록 뿌려주는 구문
class PostHolder(val binding: RecyclerListBinding) : RecyclerView.ViewHolder(binding.root) {

    lateinit var row : Row

    init {
        binding.root.setOnClickListener{
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.apply {
                this.putExtra("title", row.BPLCNM)
                this.putExtra("foodList", row.UPTAENM)
                this.putExtra("tell", row.SITETEL)
                this.putExtra("address", row.SITEWHLADDR)
            }
            it.context.startActivity(intent)
        }
    }



    //화면 구성을 위해 디바이스 위에 데이터를 뿌려주는 코드
    fun setItem(row: Row?) {

        row?.let {
            binding.title.text = row.BPLCNM
            binding.foodList.text = row.UPTAENM
            binding.address.text = row.SITEWHLADDR
            binding.tell.text = row.SITETEL
        }
        this.row = row!!
    }

}