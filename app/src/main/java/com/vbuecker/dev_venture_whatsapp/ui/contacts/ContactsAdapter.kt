package com.vbuecker.dev_venture_whatsapp.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vbuecker.dev_venture_whatsapp.R
import com.vbuecker.dev_venture_whatsapp.data.model.Contact
import de.hdodenhof.circleimageview.CircleImageView

class ContactsAdapter(val onContactSelected: (contact: Contact) -> Unit) : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    var contactsList = arrayListOf<Contact>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactName: TextView = itemView.findViewById(R.id.contact_name)
        private val contactDetail: TextView = itemView.findViewById(R.id.contact_detail)
        private val send : ImageView = itemView.findViewById(R.id.contact_send)

        fun bind(contact: Contact) {
            contactName.text = contact.name
            contactDetail.text = contact.email
            send.setOnClickListener {
                onContactSelected(contact)
            }
        }
    }

    fun setDataset(contacts: ArrayList<Contact>) {
        contactsList = contacts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }
}