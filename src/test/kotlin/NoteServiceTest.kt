import org.junit.Test

import org.junit.Assert.*
import ru.netolohy.PostNotFoundException

class NoteServiceTest {
    @Test
    fun add() {
        val new = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val service = NoteService()
        val result = service.add(new)
        assertEquals(result, 1)
    }

    @Test
    fun delete() {
        val new = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val service = NoteService()
        val tmp = service.add(new)
        val result = service.delete(1)
        assertEquals(result, 1)
    }

    @Test
    fun baddelete() {
        val new = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val service = NoteService()
        val tmp = service.add(new)
        val result = service.delete(100)
        assertEquals(result, 180)
    }
    @Test
    fun edit() {
        val new = Note(1,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val service = NoteService()
        val tmp = service.add(new)
        val result = service.edit(new)
        assertEquals(result, 1)
    }
    @Test
    fun badedit() {
        val new = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val wr = Note(100,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val service = NoteService()
        val tmp = service.add(new)
        val result = service.edit(wr)
        assertEquals(result, 180)
    }
    @Test
    fun getbyld() {
        val new = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val service = NoteService()
        val tmp = service.add(new)
        val result = service.getbyld(1, 150)
        assertEquals(result, new)
    }

    @Test (expected = PostNotFoundException::class)
    fun badgetbyld() {
        val new = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val service = NoteService()
        val tmp = service.add(new)
        val result = service.getbyld(3, 2)
        assertEquals(result, new)
    }

    @Test
    fun createComment() {
        val newnote = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val new = NoteComment(1,1,150, "very difficult", 0)
        val service = NoteService()
        val test = service.add(newnote)
        println(test)
        val result = service.createComment(new)
        assertEquals(result, 1)
    }

    @Test
    fun badcreateComment() {
        val new = NoteComment(0,1,15, "very difficult", 0)
        val service = NoteService()
        val result = service.createComment(new)
        assertEquals(result, 181)
    }

    @Test
    fun deleteComment() {
        val newnote = Note(0,15,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val new = NoteComment(0,1,15, "very difficult", 0)
        val service = NoteService()
        val test = service.add(newnote)
        val tmp = service.createComment(new)
        val result = service.deleteComment(1,15)
        assertEquals(result, 1)
    }
    @Test
    fun baddeleteComment() {
        val new = NoteComment(1,150,15, "very difficult", 0)
        val service = NoteService()
        val tmp = service.createComment(new)
        val result = service.deleteComment(1,159)
        assertEquals(result, 181)
    }
    @Test
    fun editComment() {
        val newnote = Note(0,150,"New note", "very difficult", 23092024,
            0,0,"", 0)
        val new = NoteComment(0,1,150, "very difficult", 0)
        val service = NoteService()
        val test = service.add(newnote)
        val tmp = service.createComment(new)
        println(tmp)
        val result = service.editComment(1, 150,"new text")
        assertEquals(result, 1)
    }

    @Test
    fun badeditComment() {
        val new = NoteComment(0,150,15, "very difficult", 0)
        val service = NoteService()
        val tmp = service.createComment(new)
        val result = service.editComment(1, 159,"new text")
        assertEquals(result, 181)
    }
}