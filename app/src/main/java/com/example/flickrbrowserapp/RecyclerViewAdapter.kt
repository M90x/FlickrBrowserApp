package com.example.flickrbrowserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photos_row.view.*

class RecyclerViewAdapter (private var photosList: List<Photo>):
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photos_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var photosItems = photosList[position]
        var photoID = photosItems.id
        var photoSecret = photosItems.secret
        var photoServer = photosItems.server

        holder.itemView.apply {

            Glide.with(this)
                .load("https://live.staticflickr.com/${photoServer}/${photoID}_${photoSecret}.jpg")
                .into(imageView)
            titleTV.text = photosItems.title

        }
    }

    override fun getItemCount() = photosList.size

    fun update(photos: List<Photo>){
        this.photosList = photos
        notifyDataSetChanged()
    }
}