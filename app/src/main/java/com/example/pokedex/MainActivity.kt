package com.example.pokedex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.pokedex.adapters.PokemonAdapter
import com.example.pokedex.models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        //FetchPokemonTask().execute()
    }

    private fun initRecycler() {
        //FetchPokemonTask().execute()

        val pokemon: MutableList<Pokemon> = MutableList(100) { i ->
            Pokemon(i, "Name $i", "Type $i")
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemon)

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
/*
    class FetchPokemonTask : AsyncTask<String, Void, String>() {

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
                sendIt(pokemonInfo)
            } else {
                println("I've failed you mom")
            }
        }

        fun sendIt(pokeInfo: String?): String? {
            return pokeInfo
        }
    }*/

}
