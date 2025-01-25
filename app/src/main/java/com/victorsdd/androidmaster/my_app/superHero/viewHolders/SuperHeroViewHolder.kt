package com.victorsdd.androidmaster.my_app.superHero.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.victorsdd.androidmaster.databinding.HeroItemLayoutBinding
import com.victorsdd.androidmaster.my_app.superHero.models.SuperHero

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = HeroItemLayoutBinding.bind(view)
    fun bind(superHero : SuperHero, onItemSelected: (SuperHero) -> Unit) {
        binding.tvSuperheroName.text = superHero.name
        Picasso.get().load(superHero.image.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(superHero) }
    }
}