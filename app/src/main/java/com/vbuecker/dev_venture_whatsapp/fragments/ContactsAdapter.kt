package com.vbuecker.dev_venture_whatsapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vbuecker.dev_venture_whatsapp.R
import com.vbuecker.dev_venture_whatsapp.data.model.Contact

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    var contactList: ArrayList<Contact> = arrayListOf()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactName: TextView = itemView.findViewById(R.id.titleTv)
        private val contactDetail: TextView = itemView.findViewById(R.id.subtitleTv)

        fun bind(contact: Contact) {
            contactName.text = contact.name
            contactDetail.text = contact.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setDataset(contacts: ArrayList<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }
}