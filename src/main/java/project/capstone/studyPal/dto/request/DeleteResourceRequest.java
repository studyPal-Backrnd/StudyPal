package project.capstone.studyPal.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteResourceRequest {
    private Long userId;
    private Long resourceId;
}
