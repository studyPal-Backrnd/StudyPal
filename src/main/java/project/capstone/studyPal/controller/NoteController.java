package project.capstone.studyPal.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.GetNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;
import project.capstone.studyPal.service.studyPalService.noteService.NoteService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/studypal/note/")

public class NoteController {
    private final NoteService noteService;
    @PostMapping("create")
    public ResponseEntity<?> createNote(@Valid @RequestBody CreateNoteRequest request){
        String response = noteService.createNote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("get_note")
    public ResponseEntity<?> getNoteById(@Valid @RequestBody GetNoteRequest request){
        Note note = noteService.getNoteById(request);
        return ResponseEntity.status(HttpStatus.OK).body(note);
    }

    @GetMapping("get/{note_id}")
    public ResponseEntity<?> getNote(@Valid @PathVariable Long note_id){
        Note note = noteService.getById(note_id);
        return ResponseEntity.status(HttpStatus.OK).body(note);
    }
    @PutMapping("update")
    public  ResponseEntity<?> updateNote(@Valid @RequestBody UpdateNoteRequest request){
        String response = noteService.updateNote(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("get/all/{user_id}")
    public ResponseEntity<?> getAllNotes(@Valid @PathVariable Long user_id){
        List<Note> notes = noteService.getAllNotes(user_id);
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("delete_note")
    public ResponseEntity<?> deleteNoteById(@Valid @RequestParam Long userId, @RequestParam Long noteId){
        noteService.deleteNote(userId, noteId);
        return ResponseEntity.ok("Note deleted");
    }
    @DeleteMapping("delete/all/{user_id}")
    public ResponseEntity<?> deleteAllNotes(@Valid @PathVariable Long user_id){
        noteService.deleteAllNotes(user_id);
        return ResponseEntity.ok("All notes deleted");
    }
}
