package com.example.discording_hostingpage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.LayoutRes
import com.example.discoding.R

class ArrayAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val values: MutableList<Spinner>
    ) : ArrayAdapter<Spinner>(context, resId, values) {


}