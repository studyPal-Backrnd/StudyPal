package project.capstone.studyPal.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;
import project.capstone.studyPal.service.studyPalService.noteService.NoteService;
@AllArgsConstructor
@RestController("api/v1/studypal/note")
public class NoteController {

    private final NoteService noteService;
    @PostMapping("create")
    public ResponseEntity<String> createNote(@Valid @RequestBody CreateNoteRequest request){
    return new ResponseEntity<>(noteService.createNote(request), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@Valid @PathVariable(value = "id") long noteId){
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }
    @PutMapping("update")
    public  ResponseEntity<String> updateNote(@Valid @RequestBody UpdateNoteRequest request){
        return new ResponseEntity<>(noteService.updateNote(request), HttpStatus.CONTINUE);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNote(@Valid @PathVariable long id){
         return new ResponseEntity<>(noteService.deleteNote(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllNotes(){
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

}
