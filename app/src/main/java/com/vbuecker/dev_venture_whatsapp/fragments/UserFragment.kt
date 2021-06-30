package com.vbuecker.dev_venture_whatsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.vbuecker.dev_venture_whatsapp.R
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    lateinit var adapter : FirestorePagingAdapter<com.vbuecker.dev_venture_whatsapp.User, RecyclerView.ViewHolder>

    private val dataBase by lazy {
        FirebaseFirestore.getInstance().collection("users")
            .orderBy("name", Query.Direction.DESCENDING)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupAdapter()
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    private fun setupAdapter() {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(2)
            .setPageSize(10)
            .build()

        val options = FirestorePagingOptions.Builder<com.vbuecker.dev_venture_whatsapp.User>()
            .setLifecycleOwner(this)
            .setQuery(dataBase, config, com.vbuecker.dev_venture_whatsapp.User::class.java)
            .build()

        adapter = object : FirestorePagingAdapter<com.vbuecker.dev_venture_whatsapp.User, RecyclerView.ViewHolder>(options) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                return UserViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
                )
            }

            override fun onBindViewHolder(
                holder: RecyclerView.ViewHolder,
                position: Int,
                model: com.vbuecker.dev_venture_whatsapp.User
            ) {
                (holder as UserViewHolder).bind(model)
            }

            override fun getItemViewType(position: Int): Int {
                return super.getItemViewType(position)
            }

            override fun onLoadingStateChanged(state: LoadingState) {
                super.onLoadingStateChanged(state)
            }

            override fun onError(e: Exception) {
                super.onError(e)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
itemUsers_Rv.apply {
    adapter = adapter
    layoutManager = LinearLayoutManager(requireContext())
}
    }

}

