package com.tugraz.quizlet.backend;
import com.google.common.collect.ImmutableList
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import javax.annotation.concurrent.Immutable

class WebServer(private val requestHandler: RequestHandler) {

    fun startServer() {
        embeddedServer(Netty, port = 8000) {
            routing {
                get("/questions") {
                    val gson = Gson()
                    call.respondText(gson.toJson(requestHandler.getAllQuestion()))
                    call.response.status(HttpStatusCode.Found)
                }

                get("/questions/{category}") {
                    val category = call.parameters["category"].toString()
                    val gson = Gson()
                    call.respondText(gson.toJson(requestHandler.getAllQuestionForCategory(category)))
                    call.response.status(HttpStatusCode.Found)
                }

                post("/question") {
                    val question = call.receiveParameters()["question"].toString()
                    val category = call.receiveParameters()["category"].toString()
                    val answer = call.receiveParameters()["answer"].toString()
                    val wrongAnswers = ImmutableList.of(
                        call.receiveParameters()["wrong1"].toString(),
                        call.receiveParameters()["wrong2"].toString(),
                        call.receiveParameters()["wrong3"].toString())
                    requestHandler.addQuestion(question, category, answer, wrongAnswers)
                    call.response.status(HttpStatusCode.Created)
                }
            }
        }.start(wait = true)
    }
}
