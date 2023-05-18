package project.capstone.studyPal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.capstone.studyPal.data.models.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
