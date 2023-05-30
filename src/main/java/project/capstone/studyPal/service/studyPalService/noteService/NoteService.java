package project.capstone.studyPal.service.studyPalService.noteService;

import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;

public interface NoteService {
    String createNote(CreateNoteRequest createNoteRequest);
    Note getNoteById(Long noteId);
    String upDateNote(UpdateNoteRequest updateNoteRequest);
    String deleteNote(Long noteId);
}
