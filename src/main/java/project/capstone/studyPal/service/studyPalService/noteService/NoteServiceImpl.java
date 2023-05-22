package project.capstone.studyPal.service.studyPalService.noteService;

import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.data.repository.NoteRepository;
import project.capstone.studyPal.data.repository.UserRepository;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.service.cloudService.CloudService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final UserRepository userRepository;
    private CloudService cloudService;
    private final NoteRepository noteRepository;

    @Override
    public AppUser getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException("User not found..."));
    }

    @Override
    public String createNote(@NotNull CreateNoteRequest createNoteRequest) {
        AppUser foundUser = getUserById(createNoteRequest.getUserId());
        Note note = new Note();
        note.setTitle(createNoteRequest.getTitle());
        note.setBody(createNoteRequest.getBody());
        foundUser.setNotes(List.of(note));
        noteRepository.save(note);
        return "Note created";
    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(
                ()-> new NotFoundException("Note not found or note is deleted"));
    }

    @Override
    public String updateNote(@NotNull UpdateNoteRequest updateNoteRequest) {
        Note note = getNoteById(updateNoteRequest.getNoteId());
        note.setTitle(updateNoteRequest.getUpdateTitle());
        note.setBody(updateNoteRequest.getUpdateBody());
        note.setUpdatedTime(LocalDateTime.now());
        noteRepository.save(note);
        return "Note update successfully";
    }

    @Override
    public String deleteNoteById(Long noteId) {
        noteRepository.deleteById(noteId);
        return "Note deleted";
    }

    private String validateEmail(String email) {
        SecureRandom randomNumbers = new SecureRandom();
        String token =  randomNumbers.ints(5, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(""));
        return token;
    }
}
