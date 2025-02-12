package com.example.a7minworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minworkout.databinding.ItemExcercieStatusBinding

class ExcerciseStatusAdapter(val items: ArrayList<Excercise_Model>):
    RecyclerView.Adapter<ExcerciseStatusAdapter.ViewHolder>()
{
        class ViewHolder(binding:ItemExcercieStatusBinding):
            RecyclerView.ViewHolder(binding.root){
                val tvItem=binding.tvItem
            }

    override fun getItemCount():Int {
        return items.size
    }

    override fun onBindViewHolder(holder:ViewHolder,position:Int) {
        val model:Excercise_Model=items[position]
        holder.tvItem.text=model.getId().toString()

        when{
            model.getIsSelected()->{
              holder.tvItem.background=
                  ContextCompat.getDrawable(holder.itemView.context,
                  R.drawable.item_circular_thin_color_backgroundm)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getIsCompleted()->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_circular_accent_background)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))

            }
            else->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_circular_color_gray_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))

            }
        }
    }

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):ViewHolder {
        return ViewHolder(ItemExcercieStatusBinding.inflate
            (LayoutInflater.from(parent.context), parent,false))
    }

}