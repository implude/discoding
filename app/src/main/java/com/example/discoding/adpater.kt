package com.example.discoding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


//리사이클러뷰
class ProfileAdapter(private val context: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var datas = mutableListOf<ProfileData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bot_image,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mainBotName: TextView = itemView.findViewById(R.id.bot_name)
        //private val txtAge: TextView = itemView.findViewById(R.id.tv_rv_age)
        private val mainBotImage: ImageView = itemView.findViewById(R.id.bot_Image)

        fun bind(item: ProfileData) {
            mainBotName.text = item.bot_name
            //txtAge.text = item.age.toString()
            Glide.with(itemView).load(item.img).into(mainBotImage)

        }
    }


}