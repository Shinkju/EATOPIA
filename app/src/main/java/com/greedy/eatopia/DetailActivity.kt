package com.greedy.eatopia

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra("title"))
        {
            binding.title.text = "π€" + intent.getStringExtra("title") + "π€"
            binding.foodList.text = intent.getStringExtra("foodList")
            binding.tell.text = intent.getStringExtra("tell")
            binding.address.text = intent.getStringExtra("address")

            //binding.layout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
        else
        {
            Log.d("message", "κ°μ Έμ¨ λ°μ΄ν° μμ΄")
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }



}