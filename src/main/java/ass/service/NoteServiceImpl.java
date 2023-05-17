
package ass.service;

import ass.noteobject.Note;
import ass.repository.NoteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService{
    
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void saveNote(Note note) {
        this.noteRepository.save(note);
    }

    @Override
    public Note getNoteById(long id) {
        Optional<Note> optional=noteRepository.findById(id);
        Note note = null;
        if(optional.isPresent()){
            note = optional.get();
        }else{
                throw new RuntimeException(" Note not found for id:: " + id);
        }
        return note;
    }

    @Override
    public void deleteNoteById(long id) {
        this.noteRepository.deleteById(id);
    }
    
}
