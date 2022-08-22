package com.kirigo.myretro

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kirigo.myretro.databinding.ApiListBinding
//import io.trell.mypost.databinding.ApiListBinding
class RvAdapter (var context: Context,var postList: List<Post>):
    RecyclerView.Adapter<PostsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var binding=ApiListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        var postsViewHolder=PostsViewHolder(binding)
        return postsViewHolder
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var recentpostion=postList.get(position)
        holder.binding.tvUserid.text=recentpostion.userId.toString()
        holder.binding.tvUserid.text=recentpostion.id.toString()
        holder.binding.tvTitle2.text=recentpostion.title.toString()
        holder.binding.tvBody2.text=recentpostion.body.toString()
        val context = holder.itemView.context
        holder.binding.cvPost2.setOnClickListener {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra("POST-ID",recentpostion.id )
            context.startActivity(intent)
        }
//        holder.binding.cvPost.setOnClickListener(){
//            val intent = Intent(context, CommentsActivity::class.java)
//            intent
//        }


    }

    override fun getItemCount(): Int {
        return postList.size

    }
}

class PostsViewHolder(var binding: ApiListBinding):
    RecyclerView.ViewHolder(binding.root)
