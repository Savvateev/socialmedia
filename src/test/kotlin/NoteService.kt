
data class Note (
    val id : Int ?,
    val title : String,
    val text : String,
    val noteDate : Int,
    val comments : Int = 0,
    val readComments : Int = 0,
    val viewUrl : String ?,
)

object NoteService {
    var notes = emptyArray<Note>()
    var id : Int = 0

    fun add(note: Note) : Int {
        notes += note
        id += 1
        return id
    }

    fun delete(id : Int) : Int {
        return 1

    }

    fun createComment() : Int {
        return 1

    }

}