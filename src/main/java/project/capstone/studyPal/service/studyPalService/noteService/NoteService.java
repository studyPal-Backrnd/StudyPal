package project.capstone.studyPal.service.studyPalService.noteService;

import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;

public interface NoteService {
    AppUser getUserById(Long userId);
    String createNote(CreateNoteRequest createNoteRequest);
    Note getNoteById(Long noteId);
    String updateNote(UpdateNoteRequest updateNoteRequest);
    String deleteNoteById(Long noteId);
}
