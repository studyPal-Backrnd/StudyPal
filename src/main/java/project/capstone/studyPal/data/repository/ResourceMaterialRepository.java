package project.capstone.studyPal.data.repository;

import project.capstone.studyPal.data.models.ResourceMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceMaterialRepository extends JpaRepository<ResourceMaterial, Long> {
    Optional<ResourceMaterial> getResourceMaterialByTitle(String title);
}
