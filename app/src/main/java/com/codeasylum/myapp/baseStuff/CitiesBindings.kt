package com.codeasylum.myapp.baseStuff

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codeasylum.myapp.model.localDto.City
import com.codeasylum.myapp.view.adapter.CitiesRecyclerAdapter

object CitiesBindings {
    @JvmStatic
    @BindingAdapter("app:citiesData")
    fun bindCities(recyclerView: RecyclerView, cities: ArrayList<City>) {
        with(recyclerView) {
            if (this.adapter == null) {
                adapter = CitiesRecyclerAdapter(cities)
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            }
            adapter?.notifyDataSetChanged()
        }

    }
}