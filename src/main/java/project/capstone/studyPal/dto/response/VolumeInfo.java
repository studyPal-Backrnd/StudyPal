package project.capstone.studyPal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.capstone.studyPal.dto.serializer.ReadingModesDeserializer;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolumeInfo {
    private String title;
    private String description;
    private List<String> authors;
    private String infoLink;

    @JsonProperty("readingModes")
    @JsonDeserialize(using = ReadingModesDeserializer.class)
    private Map<String, Boolean> readingModes;

}
