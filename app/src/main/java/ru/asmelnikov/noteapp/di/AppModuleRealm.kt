package ru.asmelnikov.noteapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import ru.asmelnikov.noteapp.feature_note.data.data_source.NoteDaoRealm
import ru.asmelnikov.noteapp.feature_note.data.repository.NoteRepoRealmImpl
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepositoryRealm
import ru.asmelnikov.noteapp.feature_note.domain.use_case.*
import javax.inject.Singleton
import io.realm.kotlin.*

@Module
@InstallIn(SingletonComponent::class)
object AppModuleRealm {

    @Provides
    @Singleton
    fun providesRealmDatabase(
        @ApplicationContext context: Context
    ): Realm {
        Realm.init(context)
        val realmConfiguration = RealmConfiguration
            .Builder()
            .name("myrealm.realm")
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        return Realm.getDefaultInstance()
    }

    @Provides
    @Singleton
    fun provideRepository(realm: NoteDaoRealm): NoteRepositoryRealm {
        return NoteRepoRealmImpl(realm)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepositoryRealm): NotesUseCases {
        return NotesUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
    }

}