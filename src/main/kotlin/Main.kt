package ru.netolohy

import java.sql.RowId

class PostNotFoundException(message: String) : RuntimeException(message)

abstract class Attachment(
    val type: String
)

data class PhotoAttachment(val photo: Photo) : Attachment("photo")
data class VideoAttachment(val video: Video) : Attachment("video")
data class AudioAttachment(val audio: Audio) : Attachment("audio")
data class GeoAttachment(val geo: Geo) : Attachment("geo")
data class GraffitiAttachment(val graffiti: Graffiti) : Attachment("graffiti")

data class Comment(
    val commentId: Int = 0,
    val commentUserId: Int = 0,
    val commentDate: Int = 0,
    val commentText: String = ""
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Reposts(
    val count: Int = 0,
    val userReposts: Boolean = false
)

data class Views(
    val count: Int = 0
)

data class Photo(
    val id: Int = 1,
    val ownerId: Int = 1,
    val photo130: String = "https://vk.com/some_photo_link",
    val photo604: String = "https://vk.com/another_photo_link"
) : Attachment("photo")
data class Video(
    val id: Int = 1,
    val ownerId: Int = 1,
    val title: String = "A Funny Video",
    val durationVideo: Int = 30
) : Attachment("video")
data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int?,
    val albumId: Int?,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean = true,
    val isHd: Boolean,
) : Attachment("audio")
data class Geo(
    val geoType: Int,
    val latitude: Int,
    val longitude: Int,
) : Attachment("geo")

data class Graffiti(
    val id: Int,
    val ownerId: Int,
    val url: String,
    val width: Int,
    val height: Int
) : Attachment("graffiti")

data class Post(
    var id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val publishDate: Int,
    val postText: String,
    val comment: Comment?,
    val likes: Likes?,
    val reposts: Reposts?,
    val views: Views?,
    val attachments: Array<Attachment> = emptyArray()
)
object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var id: Int = 0

    fun add(post: Post): Post {
        id += 1
        posts += post
        posts.last().id = id
        return posts.last()
    }

    fun update(post: Post): Boolean {
        var flag: Boolean = false
        for ((index, postCycle) in posts.withIndex()) {
            if (post.id == postCycle.id) {
                flag = true
                posts[index] = post
            }
        }
        return flag
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        if (id < postId) {
            throw PostNotFoundException("Create Comment Error - Post not found")
        }
        comments += comment
        return comment
    }

        fun clear() {
        posts = emptyArray()
        id = 0
    }
}

fun main() {
    val ownerId = 32768
    val fromId = 10
    val createdBy = 123
    val publishDate = 101023
    val postText = "First post with comment"
    val comments = Comment()
    val likes = Likes(0,false,true, canPublish = true)
    val reposts = Reposts()
    val views = Views()
    val attachments = arrayOf(Photo(), Video())
    val post = Post(0,ownerId,fromId,createdBy,publishDate,postText,comments,
        likes,reposts,views,attachments)
    val result = WallService.add(post)
    WallService.createComment(0,Comment(5,23, 23233,"my first comment"))

}