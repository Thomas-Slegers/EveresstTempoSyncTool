package be.everesst.everessttemposynctool.model.sync.entities;

import com.cegeka.horizon.camis.sync.logger.model.result.SyncResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;

@Converter(autoApply = true)
public class SyncResultJsonJpaConverter implements AttributeConverter<SyncResult, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enableDefaultTyping(ObjectMapper.DefaultTyping.EVERYTHING)
            .registerModule(new JavaTimeModule());


    @Override
    public String convertToDatabaseColumn(SyncResult attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON string", e);
        }
    }

    @Override
    public SyncResult convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new com.fasterxml.jackson.core.type.TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON string to object", e);
        }
    }
}
