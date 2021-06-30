package com.vbuecker.dev_venture_whatsapp.fragments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.data.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder (itemView :View) :RecyclerView.ViewHolder(itemView){
fun bind (user: com.vbuecker.dev_venture_whatsapp.User){
 itemView.titleTv.text = user.name
    Picasso.get().load(user.imageUrl).into(itemView.userImgView)
    itemView.userImgView
}
}