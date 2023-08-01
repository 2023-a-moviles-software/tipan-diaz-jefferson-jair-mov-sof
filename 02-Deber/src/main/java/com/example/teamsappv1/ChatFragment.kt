package com.example.teamsappv1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamsappv1.Adapter.ChatOptionAdapter
import com.google.android.material.imageview.ShapeableImageView


class ChatFragment : Fragment() {

    private lateinit var root: View
    private var chatOptionList: MutableList<ChatOption> = ChatProvider.chatList.toMutableList()
    private lateinit var adapter: ChatOptionAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_chat, container, false)
        initRecyclerView()
        initStatusTop(chatOptionList)
        val botonSearch = root.findViewById<EditText>(R.id.etFilter)
        botonSearch.addTextChangedListener { nameFilter->
            val chatFilterList = chatOptionList.filter { chatItem -> chatItem.name.lowercase().contains(nameFilter.toString().lowercase())  }
            adapter.updateChatOptions(chatFilterList)
        }

        return root
    }

    private fun initStatusTop(chatList: List<ChatOption>) {
        val name1 = root.findViewById<TextView>(R.id.tv_status_name_1)
        val name2 = root.findViewById<TextView>(R.id.tv_status_name_2)
        val name3 = root.findViewById<TextView>(R.id.tv_status_name_3)
        val name4 = root.findViewById<TextView>(R.id.tv_status_name_4)
        val photo1 = root.findViewById<ShapeableImageView>(R.id.hl_photo_1)
        val photo2 = root.findViewById<ShapeableImageView>(R.id.hl_photo_2)
        val photo3 = root.findViewById<ShapeableImageView>(R.id.hl_photo_3)
        val photo4 = root.findViewById<ShapeableImageView>(R.id.hl_photo_4)
        for (i in 0..3) {
            val chatOption = chatList.getOrNull(i)
            if (chatOption != null) {
                when (i) {
                    0 -> {
                        name1.text = chatOption.name.split(" ")[0]
                        Glide.with(photo1.context).load(chatOption.photo).into(photo1)
                    }
                    1 -> {
                        name2.text = chatOption.name.split(" ")[0]
                        Glide.with(photo2.context).load(chatOption.photo).into(photo2)
                    }
                    2 -> {
                        name3.text = chatOption.name.split(" ")[0]
                        Glide.with(photo3.context).load(chatOption.photo).into(photo3)
                    }
                    3 -> {
                        name4.text = chatOption.name.split(" ")[0]
                        Glide.with(photo4.context).load(chatOption.photo).into(photo4)
                    }
                }
            }
        }

    }

    private fun initRecyclerView() {
        adapter = ChatOptionAdapter(chatOptionList)
        val manager = LinearLayoutManager(root.context)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewChat)
        val decoration = DividerItemDecoration(recyclerView.context, manager.orientation)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(decoration)
    }


}