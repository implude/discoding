package com.example.discoding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView

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
        //URI고치기
        holder.imgUri.setImageURI(profileList.get(position).botUri.toUri())
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.bot_name) //봇 이름
        val description = itemView.findViewById<TextView>(R.id.bot_description)
        val imgUri = itemView.findViewById<ImageView>(R.id.bot_Image)
    }



}