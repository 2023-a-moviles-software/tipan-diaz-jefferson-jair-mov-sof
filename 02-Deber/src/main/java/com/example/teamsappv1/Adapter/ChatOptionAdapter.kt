package com.example.teamsappv1.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamsappv1.ChatOption
import com.example.teamsappv1.R

class ChatOptionAdapter(var chatOpcionList: List<ChatOption>) :
    RecyclerView.Adapter<ChatOptionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatOptionViewHolder {
        /*Paint item in recycler view*/

        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatOptionViewHolder(
            layoutInflater.inflate(
                R.layout.item_chat_option,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatOptionViewHolder, position: Int) {
        val item = chatOpcionList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return chatOpcionList.size
    }

    fun updateChatOptions(chatOpcionList: List<ChatOption>) {
        this.chatOpcionList = chatOpcionList
        notifyDataSetChanged()
    }
}