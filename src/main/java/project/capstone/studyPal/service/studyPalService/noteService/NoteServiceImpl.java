package project.capstone.studyPal.service.studyPalService.noteService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.data.repository.NoteRepository;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.GetNoteRequest;
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
            note.setTitle(createNoteRequest.getTitle());
            note.setBody(createNoteRequest.getBody());
            note.setDateAdded(LocalDateTime.now());
//            Note savedNote = noteRepository.save(note);
//            foundUser.getNotes().add(savedNote);
            foundUser.getNotes().add(note);
            userService.updateUser(foundUser);
            return "New note created";
    }

    @Override
    public Note getNoteById(GetNoteRequest request) {
        AppUser foundUser = userService.getUserById(request.getUserId());
        List<Note> notes = foundUser.getNotes();
        for(Note note: notes){
            if (note.getId().equals(request.getNoteId()))return note;
        }
        throw new RuntimeException("Note not found.");
//        return noteRepository.findById(noteId).orElseThrow(
//                ()-> new NotFoundException("Note not found or note is deleted"));
    }

    @Override
    public List<Note> getAllNotes(Long userId) {
        AppUser foundUser = userService.getUserById(userId);
        return foundUser.getNotes();
    }

    @Override
    public Long noteCount() {
        return noteRepository.count();
    }

    @Override
    public String updateNote(UpdateNoteRequest updateNoteRequest) {
//        Note foundNote = getNoteById(updateNoteRequest.getUserId(), updateNoteRequest.getNoteId());
        AppUser foundUser = userService.getUserById(updateNoteRequest.getUserId());
        List<Note> notes = foundUser.getNotes();
        for (Note note : notes){
            if(note.getId().equals(updateNoteRequest.getNoteId())){
                note.setTitle(updateNoteRequest.getUpdateTitle());
                note.setBody(updateNoteRequest.getUpdateBody());
                note.setUpdatedAt(LocalDateTime.now());
                userService.updateUser(foundUser);
                return "Note is updated";
            }
        }
        throw new RuntimeException("Error updating note");
    }

    @Override
    public void deleteNote(Long userId, Long noteId) {
        AppUser foundUser = userService.getUserById(userId);
        List<Note> notes = foundUser.getNotes();
        notes.removeIf(note -> note.getId().equals(noteId));
        userService.updateUser(foundUser);
    }

    @Override
    public void deleteAllNotes(Long userId) {
        AppUser foundUser = userService.getUserById(userId);
        List<Note> notes = foundUser.getNotes();
        notes.clear();
        userService.updateUser(foundUser);
    }
}
