import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netolohy.*

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val ownerId = 32768
        val fromId = 10
        val createdBy = 123
        val publishDate = 101023
        val postText = "First post"
        val comments = Comments()
        val likes = Likes(0,false,true, canPublish = true)
        val reposts = Reposts()
        val views = Views()
        val post = Post(0,ownerId,fromId,createdBy,publishDate,postText,comments,
            likes,reposts,views)
        val result = WallService.add(post)

        assertEquals(1, result.id)
    }

    @Test
    fun updatePositive() {
        val ownerId = 32768
        val fromId = 10
        val createdBy = 123
        val publishDate = 101023
        val postText = "First post edited"
        val comments = Comments()
        val likes = Likes(0,false,true, canPublish = true)
        val reposts = Reposts()
        val views = Views()
        val post = Post(1,ownerId,fromId,createdBy,publishDate,postText,comments,
            likes,reposts,views)
        val testPost = WallService.add(post)
        val result = WallService.update(post)

        assertEquals(true, result)
    }
    @Test
    fun updateNegative() {
        val id = 35
        val ownerId = 32768
        val fromId = 10
        val createdBy = 123
        val publishDate = 101023
        val postText = "First post edited"
        val comments = Comments()
        val likes = Likes(0,false,true, canPublish = true)
        val reposts = Reposts()
        val views = Views()
        val post = Post(id,ownerId,fromId,createdBy,publishDate,postText,comments,
            likes,reposts,views)
        val result = WallService.update(post)

        assertEquals(false, result)
    }
}