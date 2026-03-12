package org.nanking.km_flow1000_admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RocketLaunch (
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("success")
    val launchSuccess: Boolean?,
)

@Serializable
class PicIndexItem(
    override val name: String,
    val coverWidth: Int,
    val coverHeight: Int,
): AlbumConfigCover<String>
{
    override val width: Int = coverWidth
    override val height: Int = coverHeight

    override var cover: String = ""
        get() = "http://192.168.2.12:3002/linux1000/source/$name/${field.replace(".bin", "")}"
}

interface AlbumConfigCover<T> {
    val width: Int
    val height: Int
    val cover: T
    val name: String
}

@Serializable
class AlbumConfig(
    val id: Long,
    override val name: String,
    val encrypted: Boolean,
    val encryptedPath: String?,
    val sourcePath: String,
    val baseUrl: String?,
    val coverSection: Flow1000Section,
) : AlbumConfigCover<String>{

    override val width: Int = coverSection.coverWidth
    override val height: Int = coverSection.coverHeight
    override val cover: String = "http://192.168.2.12:3002/linux1000/$sourcePath/${coverSection.dirName}/${coverSection.cover.replace(".bin", "")}"

}

@Serializable
class Flow1000Section(
    val id: Long,
    val name: String,
    val dirName: String,
    val createTime: String,
    val cover: String,
    val album: String,
    val coverWidth: Int,
    val coverHeight: Int,
    val images: List<Flow1000Img>
) {
}

@Serializable
class Flow1000Img(
    val id: Long,
    val name: String,
    val inCover: Int,
    val width: Int,
    val height: Int
)