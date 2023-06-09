package project.capstone.studyPal.dto.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import project.capstone.studyPal.dto.response.BookItem;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadingModesDeserializer extends JsonDeserializer<Map<String, Boolean>> {


    @Override
    public Map<String, Boolean> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        LinkedHashMap<String, Object> rawMap = jsonParser.readValueAs(LinkedHashMap.class);
        Map<String, Boolean> readingModes = new LinkedHashMap<>();

        // Convert the values to Boolean and update the map
        for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
            readingModes.put(entry.getKey(), (Boolean) entry.getValue());
        }

        return readingModes;
    }
}
