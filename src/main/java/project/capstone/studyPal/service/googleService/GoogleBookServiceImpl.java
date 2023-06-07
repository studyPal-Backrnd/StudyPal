package project.capstone.studyPal.service.googleService;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project.capstone.studyPal.config.appConfig.GoogleBookConfig;
import project.capstone.studyPal.dto.response.BookSearchResponse;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GoogleBookServiceImpl implements GoogleBookService {

    private final WebClient webClient;
    private final GoogleBookConfig googleBookConfig;


    @Override
    public Mono<BookSearchResponse> searchBooksByTitle(String title) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                            .path("/volumes")
                            .queryParam("q", "intitle:" + title)
                            .queryParam("key", googleBookConfig.getBookApiKey())
                            .build())
                .retrieve()
                .bodyToMono(BookSearchResponse.class);
                }
    }

