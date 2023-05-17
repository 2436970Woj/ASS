
package ass.service;

import ass.noteobject.Note;
import java.util.List;

public interface NoteService{
    List<Note> getAllNotes();
   
    void saveNote(Note note);
    
    Note getNoteById(long id);
    
    void deleteNoteById(long id);
}
