package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.config.appConfig.GoogleBookConfig;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.data.models.Shelf;
import project.capstone.studyPal.data.repository.ResourceMaterialRepository;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;
import project.capstone.studyPal.dto.response.BookItem;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.service.googleService.GoogleBookService;
import project.capstone.studyPal.service.studyPalService.shelfService.ShelfService;
import project.capstone.studyPal.service.studyPalService.userService.UserService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResourceMaterialServiceImpl implements ResourceMaterialService{

    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final GoogleBookService googleBookService;
    private final ResourceMaterialRepository resourceMaterialRepository;
    private final ShelfService shelfService;
    private final UserService userService;
    private final GoogleBookConfig googleBookConfig;
    private ModelMapper modelMapper;

    @Override
    public List<BookItem> searchResourceMaterials(String topicOfInterest) throws IOException, GeneralSecurityException {
        return googleBookService.searchBooksByTitle(topicOfInterest);
    }

    @Override
    public void addResourceMaterial(@NotNull ResourceMaterialRequest resourceMaterialRequest) {
        AppUser user  = userService.getUserById(resourceMaterialRequest.getUserId());
        ResourceMaterial resourceMaterial = modelMapper.map(resourceMaterialRequest.getBookItem().getVolumeInfo(), ResourceMaterial.class);
//        resourceMaterial.setTitle(resourceMaterialRequest.getBookItem().getVolumeInfo().getTitle());
//        resourceMaterial.setDescription(resourceMaterialRequest.getBookItem().getVolumeInfo().getDescription());
//        resourceMaterial.setAuthors(resourceMaterialRequest.getBookItem().getVolumeInfo().getAuthors());
//        resourceMaterial.setLink(resourceMaterialRequest.getBookItem().getVolumeInfo().getInfoLink());

        if (user.getShelf().getResourceMaterials().contains(resourceMaterial)) throw new LogicException("Resource material cannot be duplicated");
        ResourceMaterial savedResourceMaterial = resourceMaterialRepository.save(resourceMaterial);
        user.getShelf().getResourceMaterials().add(savedResourceMaterial);
        userService.updateUser(user);

    }

    @Override
    public void removeResourceMaterial(Long userId, Long resourceMaterialId) {
        Optional<ResourceMaterial> foundMaterial = resourceMaterialRepository.findById(resourceMaterialId);
        if (foundMaterial.isEmpty()) throw new LogicException("Resource Material not found");
        AppUser user = userService.getUserById(userId);
        Shelf shelf = user.getShelf();
        List<ResourceMaterial> resourceMaterials = shelf.getResourceMaterials();
        resourceMaterials.removeIf(resourceMaterial -> resourceMaterial.getId().equals(resourceMaterialId));
        userService.updateUser(user);
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
    public ResourceMaterial getResourceMaterialById(Long resourceMaterialId) {
        Optional<ResourceMaterial> resourceMaterial = resourceMaterialRepository.findById(resourceMaterialId);
        if (resourceMaterial.isEmpty()) throw new LogicException("not found");
        return resourceMaterial.get();
    }

    @Override
    public Optional<ResourceMaterial> getResourceMaterialByTitle(String title) {
        return resourceMaterialRepository.getResourceMaterialByTitle(title);
    }


}
