import java.sql.Date

data class Note (
    val id : Int ?,
    val title : String,
    val text : String,
    var noteDate : Int = 0,
    val comments : Int = 0,
    val readComments : Int = 0,
    val viewUrl : String ?,
    val privacyView : Int = 0,
    val privacyComment : Int = 0,
    // Уровень доступа к заметке. Возможные значения: 0 — все пользователи 1 — только друзья,
    //2 — друзья и друзья друзей, 3 — только пользователь.
)

object NoteService {
    var notes = emptyArray<Note>()
    var id : Int = 0

    fun add(note: Note) : Int {
        note.noteDate = 18092024
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