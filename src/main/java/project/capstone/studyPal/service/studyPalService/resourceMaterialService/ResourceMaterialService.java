package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;

public interface ResourceMaterialService {
    void addResourceMaterial(ResourceMaterialRequest resourceMaterialRequest);

}
