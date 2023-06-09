package project.capstone.studyPal.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolumeInfo {
    private String title;
    private String description;
    private List<String> authors;
    private String infoLink;
}
