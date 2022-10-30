package acs.logic.utils;
import java.util.Set;
import javax.persistence.AttributeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SetToJsonConverter implements AttributeConverter<Set<String>, String>{
    private ObjectMapper jackson;

    public SetToJsonConverter() {
        this.jackson = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        // use jackson for marshalling the attributes
        try {
            return this.jackson
                    .writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        // use jackson for unmarshalling the json
        try {
            return this.jackson.readValue(dbData, Set.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
