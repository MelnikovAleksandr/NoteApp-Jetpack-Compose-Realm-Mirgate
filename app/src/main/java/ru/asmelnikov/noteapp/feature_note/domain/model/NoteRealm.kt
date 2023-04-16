package ru.asmelnikov.noteapp.feature_note.domain.model


import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import ru.asmelnikov.noteapp.ui.theme.*

open class NoteRealm : RealmObject {

    var title: String = ""
    var content: String = ""
    var timeStamp: Long = 0
    var color: Int = 0

    @PrimaryKey
    var id: ObjectId = ObjectId.invoke()

    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }

    class InvalidNoteException(message: String) : Exception(message)
}