package org.nanking.km_flow1000_admin

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import io.ktor.client.engine.okhttp.*


class RocketComponent {
    companion object {
        const val FLOW_1000_INDEX_URL = "http://127.0.0.1:8000/local1000/picIndexAjax?album=ship"
        val json = Json { ignoreUnknownKeys = true }
        val logger = getLogger("RocketComponent")
    }

    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchPicIndex(): List<PicIndexItem> {
        logger.d { "Fetching picIndex" }
        val responseBody: String = httpClient.get(FLOW_1000_INDEX_URL).body()
        logger.d { responseBody }
        return json.decodeFromString<List<PicIndexItem>>(responseBody)
    }

    @OptIn(ExperimentalTime::class)
    private suspend fun getDateOfLastSuccessfulLaunch(): String {
        val rockets: List<RocketLaunch> = httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessfulLaunch = rockets.last {
            it.launchSuccess == true
        }
        val date = Instant.parse(lastSuccessfulLaunch.launchDateUTC)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return "${date.month} ${date.day}, ${date.year}"
    }

    suspend fun launchPhrase(): String =
        try {
            "The last successful phrase is ${getDateOfLastSuccessfulLaunch()}"
        } catch (e: Exception) {
            println("Exception during getting the date of the last successful launch $e")
            "Error occurred"
        }
}