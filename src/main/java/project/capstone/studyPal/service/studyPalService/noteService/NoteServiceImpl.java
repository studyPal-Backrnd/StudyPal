package project.capstone.studyPal.service.studyPalService.noteService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.data.repository.NoteRepository;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.service.studyPalService.userService.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{
    private final UserService userService;
    private final NoteRepository noteRepository;
    @Override
    public String createNote(CreateNoteRequest createNoteRequest) {
        Note note = new Note();
        AppUser foundUser = userService.getUserById(createNoteRequest.getUserId());
        if(!foundUser.isEnabled())
            throw new LogicException("User is not enabled");
        else{
            note.setTitle(createNoteRequest.getTitle());
            note.setBody(createNoteRequest.getBody());
//            Note savedNote = noteRepository.save(note);
//            foundUser.getNotes().add(savedNote);
            foundUser.getNotes().add(note);
            userService.saveUser(foundUser);
            return "New note created";
        }
    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(
                ()-> new NotFoundException("Note not found or note is deleted"));
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Long noteCount() {
        return noteRepository.count();
    }

    @Override
    public String updateNote(UpdateNoteRequest updateNoteRequest) {
        Note foundNote = getNoteById(updateNoteRequest.getNoteId());
        foundNote.setTitle(updateNoteRequest.getUpdateTitle());
        foundNote.setBody(updateNoteRequest.getUpdateBody());
        foundNote.setUpdatedAt(LocalDateTime.now());
        noteRepository.save(foundNote);
        return "Note is updated";
    }

    @Override
    public String deleteNote(Long noteId) {
        noteRepository.deleteById(noteId);
        return "Note deleted";
    }
}
