package com.example.pokedex

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.pokedex.adapters.PokemonAdapter
import com.example.pokedex.models.Pokemon
import com.example.pokedex.utilities.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FetchPokemonTask().execute()
    }

    fun initRecycler(pokemonInfo: String?) {

        var pokemon: MutableList<Pokemon> = MutableList(964) {i ->
            Pokemon(i, "Name " + pokemonInfo, "Type " + "Lolito FDZ")
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemon)

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    class FetchPokemonTask : AsyncTask<String, Void, String>() {

        lateinit var result_tv: TextView

        override fun doInBackground(vararg pokemonNumbers: String?): String? {
            if(pokemonNumbers.isEmpty()) {
                val pokeAPI: URL = NetworkUtils().buildURL("", false)

                return try {
                    var result: String? = NetworkUtils().getResponseFromHttpUrl(pokeAPI)
                    result
                } catch (e: IOException) {
                    e.printStackTrace()
                    ""
                }
            } else {
                var ID: String? = pokemonNumbers[0]
                val pokeAPI: URL = NetworkUtils().buildURL(ID!!, false)

                return try {
                    var result: String? = NetworkUtils().getResponseFromHttpUrl(pokeAPI)
                    result
                } catch (e: IOException) {
                    e.printStackTrace()
                    ""
                }
            }
        }

        override fun onPostExecute(pokemonInfo: String?) {
            if(pokemonInfo != null || pokemonInfo != "") {
                MainActivity().initRecycler(pokemonInfo)
            } else {
                result_tv.setText("I've Failed you momxd")
            }
        }
    }
}
