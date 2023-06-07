package project.capstone.studyPal.dto.response;

import com.google.api.services.books.v1.model.Volume.VolumeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookItem {
    private VolumeInfo volumeInfo;
}
