package project.capstone.studyPal.service.studyPalService.noteService;

import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;

import java.util.List;

public interface NoteService {
    String createNote(CreateNoteRequest createNoteRequest);
    Note getNoteById(Long noteId);
    List<Note> getAllNotes();
    Long noteCount();
    String updateNote(UpdateNoteRequest updateNoteRequest);
    void deleteNote(Long noteId);
    void deleteAllNotes();
}
