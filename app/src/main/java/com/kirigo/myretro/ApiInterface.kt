package com.kirigo.myretro

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
    @GET("/posts/{id}")
    fun getPostById(@Path("postId")postId :Int): Call<Post>
//    @GET("/comments")
//    fun getComment():Call<List<Comment>>
    @GET("/posts/{postId}/comments")
   fun getComment(@Path("id")commentId:Int):Call<List<Comment>>
}