package project.capstone.studyPal.service.studyPalService.noteService;

import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.GetNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;

import java.util.List;

public interface NoteService {
    String createNote(CreateNoteRequest createNoteRequest);
    Note getNoteById(GetNoteRequest getNoteRequest);
    List<Note> getAllNotes(Long userId);
    Long noteCount();
    String updateNote(UpdateNoteRequest updateNoteRequest);
    void deleteNote(Long userId, Long noteId);
    void deleteAllNotes(Long userId);
}
