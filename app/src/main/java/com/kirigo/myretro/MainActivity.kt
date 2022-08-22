package com.kirigo.myretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirigo.myretro.databinding.ActivityMainBinding
//import io.trell.mypost.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
//        bringPost()
    }
    fun fetchPosts(){
        val apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPosts()
        request.enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    var posts=response.body()
                    Toast.makeText(baseContext,"fetched ${posts!!.size}posts",Toast.LENGTH_LONG).show()
                    var adapter=RvAdapter(baseContext,posts)
                    Log.d("Tag",posts.toString())
                    binding.rvApi.adapter=adapter
                    binding.rvApi.layoutManager=LinearLayoutManager(baseContext)
                }

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }
        })

    }



}