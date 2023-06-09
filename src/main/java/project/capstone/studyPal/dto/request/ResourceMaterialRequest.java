package project.capstone.studyPal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.capstone.studyPal.dto.response.BookItem;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceMaterialRequest {
    private Long userId;
    private BookItem bookItem;
}
