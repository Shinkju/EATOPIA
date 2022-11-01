package com.greedy.eatopia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greedy.eatopia.databinding.ListItemWeatherBinding


class WeatherAdapter (var items : Array<ModelWeather>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        val binding = ListItemWeatherBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    inner class ViewHolder(val binding: ListItemWeatherBinding) : RecyclerView.ViewHolder(binding.root){
        fun setItem(item : ModelWeather){
            binding.tvTime.text = item.fcstTime + "시 날씨"
            binding.tvRainType.text = getRainType(item.rainType)
            binding.tvHumidity.text = item.humidity + "%"
            binding.tvSky.text = getSky(item.sky)
            binding.tvTemp.text = item.temp + "°"
        }
    }

    // 강수 형태
    fun getRainType(rainType : String) : String {
        return when(rainType) {
            "0" -> "없음"
            "1" -> "비"
            "2" -> "비/눈"
            "3" -> "눈"
            else -> "오류 rainType : " + rainType
        }
    }

    // 하늘 상태
    fun getSky(sky : String) : String {
        return when(sky) {
            "1" -> "🌞"
            "3" -> "⛅"
            "4" -> "☁"
            else -> "오류 rainType : " + sky
        }
    }

}