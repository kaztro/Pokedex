package com.example.pokedex.utilities

import android.net.Uri
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {
    val POKEMON_API_BASE_URL: String = "https://pokeapi.co/api/v2"
    val POKEMON_INFO: String = "pokemon"
    var TAG: String? = NetworkUtils::class.java.simpleName

    fun buildURL(pokeID: String): URL {
        var buildUri: Uri = Uri.parse(POKEMON_API_BASE_URL).buildUpon().appendPath(POKEMON_INFO).appendPath(pokeID).build()
        var url: URL? = null

        try{ url = URL(buildUri.toString()) } catch (e: MalformedURLException) { e.printStackTrace() }

        Log.d(TAG, "Built Uri" + url)
        return url!!
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String? {
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        try{
            urlConnection.inputStream.bufferedReader().use { reader ->
                val scanner = Scanner(reader)

                scanner.useDelimiter("\\A")

                var hasInput: Boolean = scanner.hasNext()

                return if(hasInput) {
                    scanner.next()
                } else {
                    null
                }
            }
        } finally {
            urlConnection.disconnect()
        }


        /*
        try{
            var n: InputStream = urlConnection.inputStream.bufferedReader().readText()

            val scanner = Scanner(n)

            scanner.useDelimiter("\\A")

            var hasInput: Boolean = scanner.hasNext()

            return if(hasInput) {
                scanner.next()
            } else {
                null
            }
        } finally {
            urlConnection.disconnect()
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")



        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        var urlConnection: HttpURLConnection = URL(HttpURLConnection).openConnection() as HttpURLConnection
        try {
            var _in: InputStream = urlConnection.inputStream.bufferedReader().readText()
        }*/
    }
}