package ru.asmelnikov.noteapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.asmelnikov.noteapp.feature_note.data.repository.NoteRepoRealmImpl
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepositoryRealm
import ru.asmelnikov.noteapp.feature_note.domain.use_case.*
import javax.inject.Singleton
import io.realm.kotlin.*
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm

@Module
@InstallIn(SingletonComponent::class)
object AppModuleRealm {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(NoteRealm::class)
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideRepository(realm: Realm): NoteRepositoryRealm {
        return NoteRepoRealmImpl(realm = realm)
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