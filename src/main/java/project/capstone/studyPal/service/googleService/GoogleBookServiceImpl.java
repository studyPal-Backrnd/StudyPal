package project.capstone.studyPal.service.googleService;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project.capstone.studyPal.config.appConfig.GoogleBookConfig;
import project.capstone.studyPal.dto.response.BookItem;
import project.capstone.studyPal.dto.response.BookSearchResponse;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GoogleBookServiceImpl implements GoogleBookService {

    private final WebClient webClient;
    private final GoogleBookConfig googleBookConfig;


    @Override
    public List<BookItem> searchBooksByTitle(String title) {
        return Objects.requireNonNull(webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/volumes")
                                .queryParam("q", "intitle:" + title)
                                .queryParam("key", googleBookConfig.getBookApiKey())
                                .build())
                        .retrieve()
                        .bodyToMono(BookSearchResponse.class)
                        .block())
                .getItems();
                }
    }

