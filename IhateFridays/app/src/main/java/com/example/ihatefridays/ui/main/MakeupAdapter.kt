package com.example.ihatefridays.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.ihatefridays.R
import com.example.ihatefridays.model.MakeUp
import kotlinx.android.synthetic.main.bullhockey.view.*

class MakeupAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MakeUp>() {

        override fun areItemsTheSame(oldItem: MakeUp, newItem: MakeUp): Boolean {
           return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: MakeUp, newItem: MakeUp): Boolean {
            return (oldItem.id== newItem.id && oldItem.name==newItem.name)
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewholder =MakeupAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.bullhockey,
                parent,
                false
            ),
            interaction
        )

        return  viewholder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MakeupAdapterViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<MakeUp>) {
        differ.submitList(list)
    }

    class MakeupAdapterViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MakeUp) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
        itemView.tv_name.text = item.name
         itemView.tv_price.text = item.price
            itemView.tv_rating.text = item.rating.toString()
            Glide.with(itemView.context)
                .load(item.image_link)
                .centerCrop()
                .into(itemView.iv_image)

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: MakeUp)
    }
}

