package project.capstone.studyPal.service.googleService;

import project.capstone.studyPal.dto.response.BookItem;

import java.util.List;

public interface GoogleBookService {
    public List<BookItem> searchBooksByTitle(String title);
}
