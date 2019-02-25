package com.example.android.photoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.android.photoapp.models.Photo

class MainAdapter(var photos: List<Photo>,
                  var clickListener: View.OnClickListener) :
    RecyclerView.Adapter<MainAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.photo_item_s, p0, false))
    }

    override fun getItemCount(): Int {
            return photos.size
    }

    override fun onBindViewHolder(p0: PhotoViewHolder, p1: Int) {
        val photo = photos.get(p1)
        p0.names.text = photo.tags
        p0.likes.text = photo.likes.toString()
        p0.favs.text = photo.favorites.toString()
        if (photo.previewURL.isNotEmpty()) {
            Glide.with(p0.names.context)
                .load(photo.previewURL)
                .into(p0.photo_item)
        }

    }

    fun getPhoto (adapterPosition: Int) : Photo
    {
        return photos.get(adapterPosition)
    }

    inner class PhotoViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView){
        var names : TextView
        var likes : TextView
        var favs : TextView
        var photo_item : ImageView

        init {
            if (clickListener != null) itemView.setOnClickListener(clickListener)
            itemView.tag = this;
            names = itemView.findViewById(R.id.tags) as TextView
            likes = itemView.findViewById(R.id.likes_label) as TextView
            favs = itemView.findViewById(R.id.favorites) as TextView
            photo_item = itemView.findViewById(R.id.photo_item) as ImageView

        }

    }
}