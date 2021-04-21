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
import java.util.logging.Logger
import javax.annotation.concurrent.Immutable

class WebServer(private val requestHandler: RequestHandler) {

    companion object {
        val LOG: Logger = Logger.getLogger(WebServer::class.java.name)
    }

    private var server: NettyApplicationEngine = embeddedServer(Netty, port = 8000) {
        routing {
            get("/questions") {
                LOG.fine("Received call: ${call.request}")
                val category = call.request.queryParameters["category"]
                val questionList = if (category != null) {
                    requestHandler.getAllQuestionForCategory(category)
                } else {
                    requestHandler.getAllQuestion()
                }
                val gson = Gson()
                call.respondText(gson.toJson(questionList))
                call.response.status(HttpStatusCode.Found)
            }

            post("/question") {
                LOG.fine("Received call: ${call.request}")
                val question = call.receiveParameters()["question"]
                val category = call.receiveParameters()["category"]
                val answer = call.receiveParameters()["answer"]
                val wrong1 = call.receiveParameters()["wrong1"]
                val wrong2 = call.receiveParameters()["wrong2"]
                val wrong3 = call.receiveParameters()["wrong3"]
                if(question == null || category == null || answer == null || wrong1 == null || wrong2 == null || wrong3 == null) {
                    call.response.status(HttpStatusCode.BadRequest)
                    LOG.warning("Bad parameters for call: ${call.request}")
                    return@post
                }

                val wrongAnswers = ImmutableList.of(wrong1, wrong2, wrong3)
                requestHandler.addQuestion(question, category, answer, wrongAnswers)
                call.response.status(HttpStatusCode.Created)
            }

            post("/user") {
                LOG.fine("Received call: ${call.request}")
                val email = call.receiveParameters()["email"]
                val password = call.receiveParameters()["password"]

                if(email == null || password == null) {
                    call.response.status(HttpStatusCode.BadRequest)
                    LOG.warning("Bad parameters for call: ${call.request}")
                    return@post
                }

                requestHandler.addUser(email, password)
                call.response.status(HttpStatusCode.Created)
            }

            get("/user") {
                LOG.fine("Received call: ${call.request}")
                val email = call.receiveParameters()["email"]
                val password = call.receiveParameters()["password"]

                if(email == null || password == null) {
                    call.response.status(HttpStatusCode.BadRequest)
                    LOG.warning("Bad parameters for call: ${call.request}")
                    return@get
                }

                val gson = Gson()
                call.respondText(gson.toJson(requestHandler.getUser(email)))
                call.response.status(HttpStatusCode.Found)
            }
        }
    }

    fun startServer() {
        server.start(wait = true)
    }
}
