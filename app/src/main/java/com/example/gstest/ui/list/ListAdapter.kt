package com.example.gstest.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.data.models.Results
import com.bumptech.glide.Glide
import com.example.gstest.R
import com.example.gstest.data.utils.OnClickList
import com.example.gstest.databinding.ListItemBinding
import com.example.masterdetail.dbroom.dbmodel.GSTest
import com.google.gson.Gson

class ListAdapter(val onClickList: OnClickList) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    private val items: MutableList<GSTest> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item, parent, false
        )
        return ViewHolder(view)
    }

    fun addData(list: List<GSTest>){
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int):GSTest{
        return items[position] as GSTest
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val dataBinding: ViewDataBinding): RecyclerView.ViewHolder(dataBinding.root) {
        fun onBind(item: GSTest){
            val listItem = dataBinding as ListItemBinding
            listItem.tvTitle.text = item.originalTitle
            Glide.with(listItem.ivLogo).load("https://image.tmdb.org/t/p/w500${item.posterPath}").into(listItem.ivLogo)

            itemView.setOnClickListener {
                val gson = Gson()
                val str = gson.toJson(item)
                println("JAJAJAJAJ ${item.posterPath}")
                onClickList.goToFragment(str ,it)
            }
        }
    }
}