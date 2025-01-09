package gaur.himanshu.ktorclient.client

import gaur.himanshu.ktorclient.model.Comment
import gaur.himanshu.ktorclient.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    // https://jsonplaceholder.typicode.com/posts
    companion object {
        fun getClient(): HttpClient = HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 3000
                requestTimeoutMillis = 3000
                connectTimeoutMillis = 3000
            }

            install(DefaultRequest) {
                url {
                    host = "jsonplaceholder.typicode.com"
                    protocol = URLProtocol.HTTPS
                    headers {
                        append(HttpHeaders.Authorization, "faklfjalkfjlasjfalksf")
                    }
                }
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

        }
    }

    suspend fun getPosts(): List<Post> {
        return getClient().get("/posts")
            .body<List<Post>>()
    }

    suspend fun postPost(post: Post): Post {
        return getClient().post {
            url {
                path("/posts")
            }
            contentType(ContentType.Application.Json)
            setBody(post)
        }.body<Post>()
    }

    suspend fun patch(map: Map<String, String>, id: Int): Post {
        return getClient().patch {
            url {
                path("/posts/${id}")
                contentType(ContentType.Application.Json)
                setBody(map)
            }
        }.body<Post>()
    }

    suspend fun put(post: Post, id: Int): Post {
        return getClient().put {
            url {
                path("/posts/${id}")
                contentType(ContentType.Application.Json)
                setBody(post)
            }
        }.body<Post>()
    }

    suspend fun delete(id: Int): HttpResponse {
        return getClient().delete {
            url {
                path("/posts/${id}")
            }
        }
    }

    // https://jsonplaceholder.typicode.com/comments?postId=1
    suspend fun getComments(id: Int): List<Comment> {
        return getClient().get {
            url {
                path("/comments")
                parameter("postId", id)
            }
        }.body<List<Comment>>()
    }

}