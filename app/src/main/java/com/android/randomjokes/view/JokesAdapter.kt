package com.android.randomjokes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.randomjokes.databinding.HolderJokeBinding
import kotlin.properties.Delegates

class JokesAdapter : RecyclerView.Adapter<JokeViewHolder>() {

    var jokeList: List<String> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = HolderJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(binding)
    }

    override fun getItemCount(): Int = if (jokeList.isEmpty()) 0 else jokeList.size

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        with(holder) {
            with(jokeList[position]) {
                binding.tvJoke.text = this
            }
        }
    }
}