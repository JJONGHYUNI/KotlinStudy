package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString

class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
    var data = listOf<SleepNight>()
        //데이터가 변경되면 알 수 있도록 설정
        set(value){
            field = value
            notifyDataSetChanged()
        }

    //sleepnight의 목록의 크기를 반환하도록 재정의 (override 하여 재정의)
    override fun getItemCount() = data.size

    //하나의 목록에 대해 데이터를 표시하기 위한 함수
    override fun onBindViewHolder(holder: ViewHolder, position:Int){
        val item = data[position]
        holder.bind(item)
    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView:View):RecyclerView.ViewHolder(itemView){
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)
            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                //레이아웃을 변수에 저장
                val layoutInflater = LayoutInflater.from(parent.context)
                //레이아웃을 보여주도록 하는 것?
                val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
                return ViewHolder(view)
            }
        }
    }



}