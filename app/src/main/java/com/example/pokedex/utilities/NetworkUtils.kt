package com.example.pokedex.utilities

import android.net.Uri
import android.util.Log
import java.net.MalformedURLException
import java.net.URL

class NetworkUtils {
    val POKEMON_API_BASE_URL: String = "https://pokeapi.co/api/v2"
    val POKEMON_INFO: String = "pokemon"
    var TAG: String? = NetworkUtils::class.simpleName

    fun buildURL(pokeID: String): URL {
        var buildUri: Uri = Uri.parse(POKEMON_API_BASE_URL).buildUpon().appendPath(POKEMON_INFO).appendPath(pokeID).build()
        var url: URL? = null

        try{ url = URL(buildUri.toString()) } catch (e: MalformedURLException) { e.printStackTrace() }

        Log.d(TAG, "Built Uri" + url)
        return url!!
    }
}