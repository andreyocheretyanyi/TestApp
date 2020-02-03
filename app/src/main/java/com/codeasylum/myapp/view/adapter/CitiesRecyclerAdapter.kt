package com.codeasylum.myapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codeasylum.myapp.databinding.ItemCityBinding
import com.codeasylum.myapp.model.localDto.City


class CitiesRecyclerAdapter(private val cities: ArrayList<City>) :
    RecyclerView.Adapter<CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCityBinding = ItemCityBinding.inflate(inflater, parent, false)
        return CitiesViewHolder(binding.root)
    }

    override fun getItemCount(): Int =
        cities.size


    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.binding!!.city = cities[position]
    }
}


class CitiesViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val binding: ItemCityBinding? = DataBindingUtil.bind(v)

}