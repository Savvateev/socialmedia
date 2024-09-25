
import ru.netolohy.PostNotFoundException

val BAD_ID = "Записи с таким ID не существует"
val ACCES_DENIDED ="У вас нет прав доступа"

interface Item {
    val id: Int
    val ownerId: Int
    val text: String
    val privacy: Int
}

data class Note (
    override var id: Int,
    override var ownerId: Int,
    var title: String,
    override var text: String,
    var noteDate: Int = 0,
    var comments: Int = 0,
    var readComments: Int = 0,
    var viewUrl: String = "",
    override var privacy: Int = 0,
    // Уровень доступа к заметке. Возможные значения: 0 — все пользователи 3 — только пользователь.
) : Item
data class NoteComment(
    override var id: Int,
    var noteId: Int,
    override var ownerId: Int,
    override var text: String,
    override var privacy: Int = 0,
    // Уровень доступа к заметке. Возможные значения: 0 — все пользователи 3 — только пользователь.
) : Item

class NoteService() {
    var id: Int = 0
    var commentId : Int = 0
    var notes = mutableMapOf<Int, Note>()
    var noteComments = mutableMapOf<Int, NoteComment>()
    var deletedComments = mutableMapOf<Int, NoteComment>()

    fun add(note: Note): Int {
        id += 1
        notes.put(id, note)
        return id
    }

    fun delete(id: Int): Int {
        if (notes.containsKey(id)) {
            notes.remove(id)
            return 1
            }
        return 180
    }

    fun edit(note: Note): Int {
        if (notes.containsKey(note.id)) {
            notes.set(note.id!!, note)
            return 1
            }
        return 180
    }

    fun getbyld(id: Int, ownerId: Int): Note {
        if (notes.containsKey(id)) {
            val tmp = notes.getValue(id)
            if ((ownerId == tmp.ownerId) || (tmp.privacy == 0)) {
                return tmp
            }
            else throw PostNotFoundException(ACCES_DENIDED)
        } else throw PostNotFoundException(BAD_ID)
        throw PostNotFoundException(BAD_ID)
    }

    fun getnotes(ownerId: Int): Map<Int, Note> = notes.filterValues { it.ownerId == ownerId }

    fun createComment(comment: NoteComment) : Int {
        if (notes.containsKey(comment.noteId)) {
            val tmp = notes.getValue(comment.noteId)
            if ((tmp.ownerId == comment.ownerId) || (tmp.privacy == 0)){
                commentId += 1
                noteComments.put(commentId, comment)
                return commentId
            }
            else return 182
        }
        else return 181
    }

    fun deleteComment(commentId: Int, ownerId: Int): Int {
        if (noteComments.containsKey(commentId)) {
            val tmp = noteComments.getValue(commentId)
            if (tmp.ownerId == ownerId){
                deletedComments.put(commentId, tmp)
                noteComments.remove(commentId)
                return 1
            }
            else return 182
        }
        else return 181
    }

    fun editComment(commentId: Int, ownerId: Int, msg : String):Int {
        if (noteComments.containsKey(commentId)) {
            var tmp = noteComments.getValue(commentId)
            if (tmp.ownerId == ownerId){
                tmp.text = msg
                noteComments.set(commentId, tmp)
                return 1
            }
            else return 182
        }
        else return 181
    }

    fun restoreComment(commentId: Int, ownerId: Int):Int {
        if (deletedComments.containsKey(commentId)) {
            var tmp = deletedComments.getValue(commentId)
            if (tmp.ownerId == ownerId){
                deletedComments.remove(commentId)
                noteComments.put(commentId, tmp)
                return 1
            }
            else return 182
        }
        else return 181
    }

    fun getnoteComments(ownerId: Int): Map<Int, NoteComment> = noteComments.filterValues { it.ownerId == ownerId }

}

fun main() {
    val first = Note(0,150,"First note", "jkasgfljkahsdfjhsdf,j",23092024,
        0,0,"", 0)
    val second = Note(0,150,"Second note", "bu bu bu bu bu bu", 23092024,
        0,0,"", 0)
    val new = Note(2,150,"New note", "very difficult", 23092024,
        0,0,"", 0)
    val service = NoteService()
    service.add(first)
    println(service.getbyld(service.add(first), 150))
    println(service.getnotes(150))
    service.add(second)
    service.edit(new)
    service.delete(1)
}
