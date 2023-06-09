package project.capstone.studyPal.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.dto.request.DeleteResourceRequest;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;
import project.capstone.studyPal.dto.response.ApiResponse;
import project.capstone.studyPal.dto.response.BookItem;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.service.studyPalService.resourceMaterialService.ResourceMaterialService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/api/v1/studypal/resources/")
@RestController
public class ResourceMaterialController {

    private final ResourceMaterialService resourceMaterialService;

    @GetMapping("{title}")
    public ResponseEntity<?> searchResourceMaterials(@Valid @RequestParam @PathVariable(value = "title") String title) throws GeneralSecurityException, IOException {
        List<BookItem> books = resourceMaterialService.searchResourceMaterials(title);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping("add")
    public ResponseEntity<?> addResourceMaterial(@Valid @RequestBody ResourceMaterialRequest resourceMaterialRequest) throws GeneralSecurityException, IOException {
        resourceMaterialService.addResourceMaterial(resourceMaterialRequest);
        String bookTitle = resourceMaterialRequest.getBookItem().getVolumeInfo().getTitle();
        ApiResponse apiResponse = ApiResponse.builder()
                .message(bookTitle + " successfully added tto your shelf")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeResourceMaterial(@Valid @PathVariable(value = "id") Long resourceId, @Valid @RequestBody @NotNull DeleteResourceRequest deleteResourceRequest) {
        if (!Objects.equals(resourceId, deleteResourceRequest.getResourceId())) throw new LogicException("Bad request");
        resourceMaterialService.removeResourceMaterial(deleteResourceRequest.getUserId(), deleteResourceRequest.getResourceId());
        return ResponseEntity.status(HttpStatus.OK).body("Resource material successfully deleted");
    }

    @GetMapping("{id}")
    public ResponseEntity<?> viewResourceMaterial(@Valid @PathVariable @RequestParam Long id) {
        ResourceMaterial material = resourceMaterialService.getResourceMaterialById(id);
        return ResponseEntity.status(HttpStatus.OK).body(material);
    }


}
