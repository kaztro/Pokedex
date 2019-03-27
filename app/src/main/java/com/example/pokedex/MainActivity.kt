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
    }

    fun initRecycler() {

        var pokemon: MutableList<Pokemon> = MutableList(100) {i ->
            Pokemon(i, "Name " + i, "Type " + i)
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemon)

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
