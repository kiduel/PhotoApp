package com.example.android.photoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.android.photoapp.models.Photo

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val photo = intent?.getSerializableExtra(PHOTO) as Photo?

        photo?.webformatURL.let {
            Glide.with(this).load(photo?.webformatURL).into(imageView)
        }

        imageView.setOnClickListener{
            finish();
        }

    }

    companion object {
        val PHOTO = "Photo"
    }

}
