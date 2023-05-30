package project.capstone.studyPal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;
import project.capstone.studyPal.service.studyPalService.noteService.NoteService;
@AllArgsConstructor
@RestController("studyPal/api/")
public class NoteController {

    private final NoteService noteService;
    @PostMapping("createNote")
    public ResponseEntity<String> createNote(@RequestBody CreateNoteRequest request){
    return new ResponseEntity<>(noteService.createNote(request), HttpStatus.CREATED);
    }
    @GetMapping("getNoteById")
    public ResponseEntity<Note> getNoteById(@RequestParam long noteId){
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }
    @PutMapping("upDateNote")
    public  ResponseEntity<String> updateNote(@RequestBody UpdateNoteRequest request){
        return new ResponseEntity<>(noteService.updateNote(request), HttpStatus.CONTINUE);
    }
    @DeleteMapping("deleteNoteById")
    public ResponseEntity<String> deleteNote(@RequestBody long id){
         return new ResponseEntity<>(noteService.deleteNote(id), HttpStatus.CONTINUE);
    }

}
