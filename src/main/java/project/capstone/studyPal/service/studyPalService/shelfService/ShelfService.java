package project.capstone.studyPal.service.studyPalService.shelfService;

import project.capstone.studyPal.data.models.ResourceMaterial;

public interface ShelfService {
    void addResourceToShelf(ResourceMaterial resourceMaterial);
    void removeResourceFromShelf(ResourceMaterial resourceMaterial);

}
