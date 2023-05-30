package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.data.repository.ResourceMaterialRepository;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;
import project.capstone.studyPal.service.studyPalService.shelfService.ShelfService;

import java.util.List;

@Service
@AllArgsConstructor
public class ResourceMaterialServiceImpl implements ResourceMaterialService{

    private final ResourceMaterialRepository resourceMaterialRepository;
    private final ShelfService shelfService;


    @Override
    public void addResourceMaterial(ResourceMaterialRequest resourceMaterialRequest) {

    }

    @Override
    public void removeResourceMaterial(Long resourceMaterialId) {

    }

    @Override
    public void removeResourceMaterial(ResourceMaterial resourceMaterial) {

    }

    @Override
    public List<ResourceMaterial> viewAllResourceMaterials() {
        return null;
    }

    @Override
    public ResourceMaterial getResourceMaterialById(Long resourceMaterialId) {
        return null;
    }

    @Override
    public ResourceMaterial getResourceMaterialByTopic(String title) {
        return null;
    }
}
