package br.com.luise.noteapp.repository

import br.com.luise.noteapp.data.NoteDataBaseDao
import br.com.luise.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDataBaseDao: NoteDataBaseDao) {
    suspend fun addNote(note: Note) = noteDataBaseDao.insert(note = note)
    suspend fun updateNote(note: Note) = noteDataBaseDao.update(note)
    suspend fun deleteNote(note: Note) = noteDataBaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDataBaseDao.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDataBaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}