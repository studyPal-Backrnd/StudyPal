package project.capstone.studyPal.service.studyPalService.noteService;

import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;
import project.capstone.studyPal.exception.NotFoundException;

public interface NoteService {
   String createNote(CreateNoteRequest createNoteRequest);
   Note getNoteById(Long noteId)throws NotFoundException;
   String updateNote(UpdateNoteRequest updateNoteRequest);
   String deleteNoteById(Long noteId);
}
