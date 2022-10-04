package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter : androidx.recyclerview.widget.ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
    //sleepnight의 목록의 크기를 반환하도록 재정의 (override 하여 재정의)
    //override fun getItemCount() = data.size

    //하나의 목록에 대해 데이터를 표시하기 위한 함수
    override fun onBindViewHolder(holder: ViewHolder, position:Int){
        val item = getItem(position)
        holder.bind(item)
    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: SleepNight) {
            binding.sleep =item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                //레이아웃을 변수에 저장
                val layoutInflater = LayoutInflater.from(parent.context)
                //레이아웃을 보여주도록 하는 것?
//                val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
                val binding = ListItemSleepNightBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}
class SleepNightDiffCallback:DiffUtil.ItemCallback<SleepNight>(){
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem==newItem
    }

}