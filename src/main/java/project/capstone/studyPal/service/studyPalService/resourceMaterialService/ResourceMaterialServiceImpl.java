package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.BooksRequestInitializer;
import com.google.api.services.books.v1.model.Volume;
import com.google.api.services.books.v1.model.Volumes;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.config.appConfig.GoogleBookConfig;
import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.data.repository.ResourceMaterialRepository;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;
import project.capstone.studyPal.dto.response.BookItem;
import project.capstone.studyPal.dto.response.BookSearchResponse;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.service.studyPalService.shelfService.ShelfService;
import project.capstone.studyPal.service.studyPalService.userService.UserService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResourceMaterialServiceImpl implements ResourceMaterialService{

    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final ResourceMaterialRepository resourceMaterialRepository;
    private final ShelfService shelfService;
    private final UserService userService;
    private final GoogleBookConfig googleBookConfig;

    @Override
    public List<ResourceMaterial> searchResourceMaterials(String topicOfInterest) throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();

        GenericUrl url = new GenericUrl(googleBookConfig.getBookUrl());
        url.put("q", "intitle:" + topicOfInterest);
        url.put("key", googleBookConfig.getBookApiKey());

        HttpRequest request = requestFactory.buildGetRequest(url);
        request.setParser(new JsonObjectParser(new JacksonFactory()));

        HttpResponse response = request.execute();
        BookSearchResponse searchResponse = response.parseAs(BookSearchResponse.class);
        List<ResourceMaterial> relatedBooks = new ArrayList<>();

        if (searchResponse != null && searchResponse.getItems() != null) {
            for (BookItem item : searchResponse.getItems()) {
                ResourceMaterial book = new ResourceMaterial();
                book.setTitle(item.getVolumeInfo().getTitle());
                book.setDescription(item.getVolumeInfo().getDescription());
                book.setAuthor(item.getVolumeInfo().getAuthors().get(0));
                book.setLink(item.getVolumeInfo().getInfoLink());
                relatedBooks.add(book);
            }
        }

        return relatedBooks;
    }
    @Override
    public void addResourceMaterial(@NotNull ResourceMaterialRequest resourceMaterialRequest) {

    }

    @Override
    public void removeResourceMaterial(Long resourceMaterialId) {
        Optional<ResourceMaterial> foundMaterial = resourceMaterialRepository.findById(resourceMaterialId);
        if (foundMaterial.isEmpty()) throw new LogicException("not found");
        resourceMaterialRepository.deleteById(resourceMaterialId);
    }

    @Override
    public void removeResourceMaterial(ResourceMaterial resourceMaterial) {
        resourceMaterialRepository.delete(resourceMaterial);
    }

    @Override
    public List<ResourceMaterial> viewAllResourceMaterials() {
        return resourceMaterialRepository.findAll();
//        return resourceMaterialRepository.findAll(Page );
    }

    @Override
    public Optional<ResourceMaterial> getResourceMaterialById(Long resourceMaterialId) {
        return resourceMaterialRepository.findById(resourceMaterialId);
    }

    @Override
    public Optional<ResourceMaterial> getResourceMaterialByTopic(String title) {
        return resourceMaterialRepository.getResourceMaterialByTitle(title);
    }


}
