package project.capstone.studyPal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.services.books.v1.model.Volume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("volumeInfo")
    private VolumeInfo volumeInfo;

}
