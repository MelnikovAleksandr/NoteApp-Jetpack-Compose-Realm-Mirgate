package ru.asmelnikov.noteapp.feature_note.domain.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.asmelnikov.noteapp.ui.theme.*

open class NoteRealm(
    var title: String = "",
    var content: String = "",
    var timeStamp: Long = 0,
    var color: Int = 0,
    @PrimaryKey var id: Int = 0
) : RealmObject() {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }

    class InvalidNoteException(message: String) : Exception(message)
}