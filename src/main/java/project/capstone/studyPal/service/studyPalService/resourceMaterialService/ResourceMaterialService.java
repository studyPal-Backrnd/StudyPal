package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;

import java.util.List;

public interface ResourceMaterialService {
    void addResourceMaterial(ResourceMaterialRequest resourceMaterialRequest);
    void removeResourceMaterial(Long resourceMaterialId);
    void removeResourceMaterial(ResourceMaterial resourceMaterial);
    List<ResourceMaterial> viewAllResourceMaterials();
    ResourceMaterial getResourceMaterialById(Long resourceMaterialId);
    ResourceMaterial getResourceMaterialByTopic(String title);

}
