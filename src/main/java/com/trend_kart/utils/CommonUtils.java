package com.trend_kart.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class CommonUtils {
    public final ObjectMapper objectMapper = new ObjectMapper();

    public <T> ResponseEntity<T> generateResponse(T data) {
        return ResponseEntity.ok(data);
   }

   public Map<String, Object> convertToMap(Object object) {
        if (object == null) return Collections.emptyMap();

        // TypeReference<>() can handle generic types like Map<String, Object>, List<TestDTO>, etc.
        // TypeReference<> will remember the generic type at runtime, helps in de-serialising the objects.
        // new TypeReference<>() {} is creating a subclass of TypeReference<Map<String, Object>>, which will capture the full type info
        return objectMapper.convertValue(object, new TypeReference<>() {});
   }
}
