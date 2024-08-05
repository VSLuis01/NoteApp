package br.com.luise.noteapp.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.luise.noteapp.R
import br.com.luise.noteapp.components.NoteButton
import br.com.luise.noteapp.components.NoteInputText
import br.com.luise.noteapp.data.NotesDataSource
import br.com.luise.noteapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Notificação")
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDAFFE3))
        )

        // Content

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                })

            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) description = it
                })

            NoteButton(text = "Salvar", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    // save/add
                    onAddNote(Note(title = title, description = description))
                    title = ""
                    description = ""

                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                }
            })
        }

        HorizontalDivider(modifier = Modifier.padding(10.dp))

        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note) {
                    onRemoveNote(note)
                }
            }
        }


    }
}

@Composable
fun NoteRow(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)),
        color = Color(0xFFDFE6EB),
        tonalElevation = 6.dp,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 13.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleSmall)
            Text(text = note.description, style = MaterialTheme.typography.titleSmall)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.bodySmall
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}