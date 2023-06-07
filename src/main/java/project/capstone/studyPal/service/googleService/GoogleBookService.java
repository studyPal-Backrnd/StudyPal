package project.capstone.studyPal.service.googleService;

import project.capstone.studyPal.dto.response.BookSearchResponse;
import reactor.core.publisher.Mono;

public interface GoogleBookService {
    public Mono<BookSearchResponse> searchBooksByTitle(String title);
}
