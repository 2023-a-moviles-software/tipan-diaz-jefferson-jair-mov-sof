package com.example.teamsappv1.Adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.teamsappv1.ChatOption
import com.example.teamsappv1.R
import com.google.android.material.imageview.ShapeableImageView

class ChatOptionViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textViewName = view.findViewById<TextView>(R.id.tv_name)
    val textViewFecha = view.findViewById<TextView>(R.id.tv_fecha)
    val textViewLastMessahe = view.findViewById<TextView>(R.id.tv_last_message)
    val img_photo = view.findViewById<ShapeableImageView>(R.id.iv_photo)


    fun render (chatOption: ChatOption){
        textViewName.text = chatOption.name
        textViewFecha.text = chatOption.fecha
        textViewLastMessahe.text = chatOption.lastMessage
        Glide.with(img_photo.context).load(chatOption.photo).into(img_photo)
    }
}