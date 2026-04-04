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
import io.ktor.client.request.post


class RocketComponent {
    companion object {
        const val FLOW_1000_INDEX_URL = "http://$API_SERVER/local1000/picIndexAjax?album=%s"
        const val FLOW_1000_COVER_LIST_URL = "http://$API_SERVER/local1000/albumConfig/list"
        const val FLOW_1000_SECTION_CONTENT_URL = "http://$API_SERVER/local1000/picDetailAjax?id=%d"
        const val FLOW_1000_SECTION_DOWNLOAD_URL = "http://$API_SERVER/local1000/downloadSection?id=%d"
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

    suspend fun fetchAlbumConfigList(): List<AlbumConfig> {
        logger.d { "Fetching AlbumConfigList" }
        val responseBody: String = httpClient.get(FLOW_1000_COVER_LIST_URL).body()
        return json.decodeFromString<List<AlbumConfig>>(responseBody)
    }

    suspend fun fetchPicIndex(albumName: String): List<PicIndexItem> {
        logger.d { "Fetching picIndex" }
        val responseBody: String = httpClient.get(String.format(FLOW_1000_INDEX_URL, albumName)).body()
        return json.decodeFromString<List<PicIndexItem>>(responseBody)
    }

    suspend fun fetchSectionContent(id: Long): SectionDetail {
        logger.d { "Fetching section content" }
        val response: SectionDetail = httpClient.get(String.format(FLOW_1000_SECTION_CONTENT_URL, id)).body()
        return response
    }

    suspend fun downloadSectionById(id: Long) {
        logger.d { "Download section by id $id" }
        val response = httpClient.post (String.format(FLOW_1000_SECTION_DOWNLOAD_URL, id))
        logger.d { "Download section response ${response.status.value}" }
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