package com.victorsdd.androidmaster.my_app.superHero.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.superHero.models.SuperHero
import com.victorsdd.androidmaster.my_app.superHero.viewHolders.SuperHeroViewHolder

class SuperHeroAdapter (
    private var superHeroList: List<SuperHero> = emptyList(),
    private val onItemSelected: (SuperHero) -> Unit,
) : RecyclerView.Adapter<SuperHeroViewHolder>() {


    fun updateList(superHeroList: List<SuperHero>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder = SuperHeroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hero_item_layout, parent, false))

    override fun getItemCount(): Int = superHeroList.size

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        viewHolder.bind(superHeroList[position], onItemSelected)
    }

}