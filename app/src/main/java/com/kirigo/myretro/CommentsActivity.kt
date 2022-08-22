package com.kirigo.myretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirigo.myretro.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId =0
    var commentId =0
    lateinit var binding: ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        obtainPostId()
        fetchPostById()
        setupToolbar()
        fetchCommentbyId()
        obtainCommentId()

    }
    fun obtainPostId(){
        postId = intent.extras?.getInt("POST-ID")?:0
    }
    fun  obtainCommentId(){
        commentId =intent.extras?.getInt("POST_ID")?:0
    }
    fun fetchPostById(){
        val apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPostById(postId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(response.isSuccessful){
                    var post = response.body()
                    binding.tvPost.text = post?.title
                    binding.tvBody2.text = post?.title
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }

        })
    }
    fun setupToolbar(){
        setSupportActionBar( binding.toolbarComments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun fetchCommentbyId() {
        val apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getComment(postId)

        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comment = response.body()?: emptyList()
                    displayComment(comment)
                }
            }
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun displayComment(commentList: List<Comment>) {
        val adapterDisplay= DisplayCurrentAdapter(commentList)
        binding.rvDisplay.layoutManager = LinearLayoutManager(this)
        binding.rvDisplay.adapter = adapterDisplay
    }
}