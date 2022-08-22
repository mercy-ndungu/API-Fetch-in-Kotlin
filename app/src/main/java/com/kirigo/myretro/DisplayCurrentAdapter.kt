package com.kirigo.myretro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kirigo.myretro.databinding.DisplaycommentBinding

class DisplayCurrentAdapter(var listComment:List<Comment>): RecyclerView.Adapter<CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var binding=DisplaycommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentComment = listComment.get(position)

        with(holder.binding) {
            tvTitle2.text = currentComment.name.toString()
            tvId2.text = currentComment.id.toString()
            tvUserid2.text = currentComment.email
            tvBody2.text = currentComment.body

        }
    }
    override fun getItemCount():Int{
        return  listComment.size

    }}
class CommentViewHolder(var binding: DisplaycommentBinding):RecyclerView.ViewHolder(binding.root)