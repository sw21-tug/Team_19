package com.tugraz.quizlet.frontend.apihandler

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet


// TODO:
// Get request
// Post request



class ApiHandler {



    public fun doGetRequest(type: String, Body: String, URL: String) {

        Fuel.get(URL)
            .body(Body)
            .response { request, response, result ->
                println(request)
                println(response)
                val (bytes, error) = result
                if (bytes != null) {
                    println("[response bytes] ${String(bytes)}")
                }
            }

    }

    public fun doGetRequest(type: String, URL: String) {


    }

    public fun doGetRequest(type: String, Body: String, URL: String, QueryParameter: String) {


    }


}