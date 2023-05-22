package project.capstone.studyPal.service.studyPalService.noteService;

import lombok.AllArgsConstructor;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.data.repository.NoteRepository;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;
import project.capstone.studyPal.exception.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.service.studyPalService.userService.UserService;
import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;

    @Override
    public String createNote(@NotNull  CreateNoteRequest createNoteRequest) {
        Note note = new Note();
        AppUser foundUser = userService.getUserById(createNoteRequest.getUserId());
        note.setTitle(createNoteRequest.getTitle());
        note.setBody(createNoteRequest.getBody());
        Note savedNote = noteRepository.save(note);
        foundUser.setNotes(List.of(savedNote));
        return "Note created";
    }

    @Override
    public Note getNoteById(Long noteId)throws NotFoundException {
        return noteRepository.findById(noteId).orElseThrow(
                ()-> new NotFoundException("Note not found or is deleted"));
    }

    @Override
    public String updateNote(@NotNull UpdateNoteRequest updateNoteRequest) {
        Note foundNote = getNoteById(updateNoteRequest.getNoteId());
        foundNote.setTitle(updateNoteRequest.getUpdateTitle());
        foundNote.setBody(updateNoteRequest.getUpdateBody());
        foundNote.setUpdatedTime(LocalDateTime.now());
        noteRepository.save(foundNote);
        return "Note updated";
    }

    @Override
    public String deleteNoteById(Long noteId) {
        noteRepository.deleteById(noteId);
        return "Note is deleted";
    }
}
