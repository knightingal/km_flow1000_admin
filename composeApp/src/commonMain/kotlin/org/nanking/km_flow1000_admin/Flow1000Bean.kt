package org.nanking.km_flow1000_admin

import kotlinx.serialization.Serializable


@Serializable
class SectionDetail (
    val dirName: String,
    val pics: List<ImgInSectionDetail>,
    val album: String,
    val title: String,
    val clientStatus: ClientStatus,
)

@Serializable
class ImgInSectionDetail(
    val id: Long,
    override val name: String,
    override val width: Int,
    override val height: Int
): CardCover<String> {

    var albumSourcePath: String? = null
    var sectionDir: String? = null
    override val coverUri: String
        get() = "http://$IMAGE_SERVER/linux1000/${albumSourcePath}/$sectionDir/${
            replaceImgNameWithPng(name)
        }"
}

enum class ClientStatus {
    NONE, PENDING, LOCAL
}

@Serializable
class PicIndexItem(
    val index: Long,
    override val name: String,
    val coverWidth: Int,
    val coverHeight: Int,
    val cover: String,
    val clientStatus: ClientStatus,
): CardCover<String>
{
    var albumSourcePath: String? = null
    override val width: Int = coverWidth
    override val height: Int = coverHeight

    override val coverUri: String
        get() = "http://$IMAGE_SERVER/linux1000/${albumSourcePath}/$name/${
            replaceImgNameWithPng(cover)
        }"
}

interface CardCover<T> {
    val width: Int
    val height: Int
    val coverUri: T
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
) : CardCover<String>{

    override val width: Int = coverSection.coverWidth
    override val height: Int = coverSection.coverHeight
    override val coverUri: String = "http://$IMAGE_SERVER/linux1000/$sourcePath/${coverSection.dirName}/${
        replaceImgNameWithPng(coverSection.cover)
    }"

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
class Flow1000Bean {
}

fun replaceImgNameWithPng(name: String): String {
    return name
        .replace(".bin", "")
        // for there is bug in image component, it can not load avif image,
        // so I have to convert avif to png in server side and change the name here
        .replace(".avif", ".avif.png")
}