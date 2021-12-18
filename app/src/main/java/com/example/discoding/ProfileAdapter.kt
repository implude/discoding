package com.example.discoding

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class ProfileAdapter(val profileList: ArrayList<Profiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bot_image, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {
        holder.name.text = profileList.get(position).name
        holder.description.text = profileList.get(position).description

        Glide.with(holder.imagetag).load("http://selfstudy.kro.kr:5000/public/images/Group 130.png").into(holder.imagetag)
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, EditBot::class.java)
            intent.putExtra("name", profileList.get(position).name)
            intent.putExtra("description", profileList.get(position).description)
            v.context.startActivity(intent)
        }
    }
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.bot_name) //봇 이름
        val description = itemView.findViewById<TextView>(R.id.bot_description)
        val imagetag = itemView.findViewById<ImageView>(R.id.bot_Image)
    }
}