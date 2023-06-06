package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.data.repository.ResourceMaterialRepository;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.service.studyPalService.shelfService.ShelfService;
import project.capstone.studyPal.service.studyPalService.userService.UserService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResourceMaterialServiceImpl implements ResourceMaterialService{

    private final ResourceMaterialRepository resourceMaterialRepository;
    private final ShelfService shelfService;
    private final UserService userService;


    @Override
    public void addResourceMaterial(ResourceMaterialRequest resourceMaterialRequest) {

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
