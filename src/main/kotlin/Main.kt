package ru.netolohy

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = true,
    val canOpen: Boolean = false
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

data class Post(
    var id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val publishDate: Int,
    val postText: String,
    val comments: Comments,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views
)

object WallService {
    private var posts = emptyArray<Post>()
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

    fun clear() {
        posts = emptyArray()
        id = 0
    }
}

fun main() {

}