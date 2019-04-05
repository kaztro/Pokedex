package com.example.tryingpokedex.utils

import android.net.Uri
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {
    //Se declaran los componentes de la URL
    val POKEMON_API_BASE_URL: String = "https://pokeapi.co/api/v2/"
    val POKEMON_INFO = "pokemon"

    private val TAG = NetworkUtils::class.qualifiedName

    //Funcion para unir la URL, retorna URL
    fun buildURL(pokeID: String): URL? {
        val builtUri = Uri.parse(POKEMON_API_BASE_URL)
            .buildUpon()
            .appendPath(POKEMON_INFO)
            .appendPath(pokeID)
            .build()

        var url: URL? = null

        try{ url = URL(builtUri.toString()) }
        catch (e: MalformedURLException) { e.printStackTrace() }

        return url
    }

    fun getResponseFromHttpUrl(url: URL): String? {
        var urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            val inputS = urlConnection.inputStream

            val scanner = Scanner(inputS)
            scanner.useDelimiter("\\A")

            var hasInput: Boolean = scanner.hasNext();

            if(hasInput) return scanner.next()
            else return null
        } finally {
            urlConnection.disconnect()
        }
    }

}