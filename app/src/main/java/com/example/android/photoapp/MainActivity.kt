package com.example.android.photoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.android.photoapp.api.PhotoRetriever
import com.example.android.photoapp.models.Photo
import com.example.android.photoapp.models.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var photos : List<Photo>? =  null;
    var mainAdapter : MainAdapter? = null;
    lateinit var rec_view : RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rec_view = findViewById(R.id.rec_view) as RecyclerView;
        rec_view.layoutManager = LinearLayoutManager (this)

        var retriever = PhotoRetriever()

        val callback = object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>, t: Throwable) {
                Log.i("MainAc", "Problems in retrofit")
            }

            override fun onResponse(call: Call<PhotoList>, response: Response<PhotoList>) {
               response?.isSuccessful.let {
                   this@MainActivity.photos = response.body()?.hits
                   mainAdapter = MainAdapter(
                       this@MainActivity.photos!!, this@MainActivity)
                   rec_view.adapter = mainAdapter

               }
            }

        }

        retriever.getPhotos(callback)
      }

    override fun onClick(view: View?) {
        val intent = Intent(this,DetailActivity::class.java)
        val holder = view?.tag as MainAdapter.PhotoViewHolder
        intent.putExtra(DetailActivity.PHOTO, mainAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)
    }
}
