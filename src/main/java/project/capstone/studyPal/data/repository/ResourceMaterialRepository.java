package project.capstone.studyPal.data.repository;

import project.capstone.studyPal.data.models.ResourceMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceMaterialRepository extends JpaRepository<ResourceMaterial, Long> {
    ResourceMaterial getResourceMaterialByTitle(String title);
}
