package org.nanking.km_flow1000_admin

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class Flow1000RequestWrap {
    companion object {
        const val FLOW_1000_INDEX_URL = "http://$API_SERVER/local1000/picIndexAjax?album=%s"
        const val FLOW_1000_COVER_LIST_URL = "http://$API_SERVER/local1000/albumConfig/list"
        const val FLOW_1000_SECTION_CONTENT_URL = "http://$API_SERVER/local1000/picDetailAjax?id=%d"
        const val FLOW_1000_SECTION_DOWNLOAD_URL = "http://$API_SERVER/local1000/downloadSection?id=%d"
        const val FLOW_1000_SECTION_UNSUBSCRIBE_URL = "http://$API_SERVER/local1000/unsubscribeSection/%d"
        const val FLOW_1000_SECTION_URL = "http://$API_SERVER/local1000/section/%d"
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

    /**
     * Fetches the list of album configurations from the Flow1000 server.
     *
     * @return A list of [AlbumConfig] objects representing the available albums.
     */
    suspend fun fetchAlbumConfigList(): List<AlbumConfig> {
        logger.d { "Fetching AlbumConfigList" }
        val responseBody: String = httpClient.get(FLOW_1000_COVER_LIST_URL).body()
        return json.decodeFromString<List<AlbumConfig>>(responseBody)
    }

    /**
     * Fetches the picture index for a specific album.
     *
     * @param albumName The name of the album to fetch the index for.
     * @return A list of [PicIndexItem] objects representing the pictures in the album.
     */
    suspend fun fetchPicIndex(albumName: String): List<PicIndexItem> {
        logger.d { "Fetching picIndex" }
        val responseBody: String = httpClient.get(String.format(FLOW_1000_INDEX_URL, albumName)).body()
        return json.decodeFromString<List<PicIndexItem>>(responseBody)
    }

    /**
     * Fetches the detailed content of a specific section.
     *
     * @param id The ID of the section to fetch.
     * @return A [SectionDetail] object containing the section's details.
     */
    @Suppress("DefaultLocale")
    suspend fun fetchSectionContent(id: Long): SectionDetail {
        logger.d { "Fetching section content" }
        val response: SectionDetail = httpClient.get(String.format(FLOW_1000_SECTION_CONTENT_URL, id)).body()
        return response
    }

    /**
     * Downloads a section by its ID.
     *
     * @param id The ID of the section to download.
     */
    @Suppress("DefaultLocale")
    suspend fun downloadSectionById(id: Long) {
        logger.d { "Download section by id $id" }
        val response = httpClient.post (String.format(FLOW_1000_SECTION_DOWNLOAD_URL, id))
        logger.d { "Download section response ${response.status.value}" }
    }

    /**
     * Unsubscribes from a section by its ID.
     *
     * @param id The ID of the section to unsubscribe from.
     */
    @Suppress("DefaultLocale")
    suspend fun unsubscribeSectionById(id: Long) {
        logger.d { "unsubscribe section by id $id" }
        val response = httpClient.post (String.format(FLOW_1000_SECTION_UNSUBSCRIBE_URL, id))
        logger.d { "unsubscribe section response ${response.status.value}" }
    }

    /**
     * Deletes a section by its ID.
     *
     * @param id The ID of the section to delete.
     */
    @Suppress("DefaultLocale")
    suspend fun deleteSectionById(id: Long) {
        logger.d { "unsubscribe section by id $id" }
        val response = httpClient.delete(String.format(FLOW_1000_SECTION_URL, id))
        logger.d { "unsubscribe section response ${response.status.value}" }
    }
}