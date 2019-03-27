package com.example.pokedex.adapters

import android.os.AsyncTask
import android.provider.Settings.Global.getString
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pokedex.R
import com.example.pokedex.models.Pokemon
import com.example.pokedex.utilities.NetworkUtils
import kotlinx.android.synthetic.main.list_element_pokemon.view.*
import java.io.IOException
import java.net.URL

class PokemonAdapter(val items: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var countViews: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element_pokemon, parent, false)

        view.findViewById<TextView>(R.id.count_element).text = countViews.toString()
        countViews++
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pokemon) = with(itemView) {
            tv_pokemon_name.text = item.name
            tv_pokemon_type.text = item.type
        }
    }

    class FetchPokemonTask() : AsyncTask<String, Void, String>() {

        lateinit var result_tv: TextView

        override fun doInBackground(vararg pokemonNumbers: String?): String? {
            if(pokemonNumbers.isEmpty()) return null

            var ID: String? = pokemonNumbers[0]

            val pokeAPI: URL = NetworkUtils().buildURL(ID!!)

            try {
                var result: String? = NetworkUtils().getResponseFromHttpUrl(pokeAPI);
                return result;
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
        }

        override fun onPostExecute(pokemonInfo: String?) {
            if(pokemonInfo != null || pokemonInfo != "") {
                result_tv.setText(pokemonInfo)
            } else {
                result_tv.setText("Fallexd")
            }
        }
    }

}