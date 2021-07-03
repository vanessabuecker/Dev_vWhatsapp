package com.vbuecker.dev_venture_whatsapp.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.vbuecker.dev_venture_whatsapp.R
import com.vbuecker.dev_venture_whatsapp.data.model.Message

class ChatsAdapter(private val userEmail: String) : RecyclerView.Adapter<ChatsAdapter.ViewHolder>() {

    var messageList = arrayListOf<Message>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val edMessage: EditText = itemView.findViewById(R.id.ed_message)

        fun bind(userMsg: Message) = edMessage.setText(userMsg.message)
    }

    fun setDataset(messages: ArrayList<Message>) {
        messageList = messages
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].from == userEmail) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsAdapter.ViewHolder {
        val view = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat_incoming, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat_outgoing, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatsAdapter.ViewHolder, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}