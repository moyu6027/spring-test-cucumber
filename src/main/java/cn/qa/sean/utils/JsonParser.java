package cn.qa.sean.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonParser {
    private final ObjectMapper objectMapper;

    @Value("data/")
    private String requestBodyRootResourceFolder;

    @Value("schema/")
    private String responseSchemaResourceFolder;

    /**
     * Reads a http request payload body JSON file from {@code classpath:testDataRootPath}.
     * <p>
     * {@code testDataRootPath} is specified by {@code http.request.payload.root.resource.directory} property.
     *
     * @param relativeFilePath - relative path of the JSON file to load in {@code testDataRootPath}
     * @return The http request payload body.
     */
    @SneakyThrows
    public JsonNode parseHttpRequestBodyJsonFromFile(String relativeFilePath) {
        var resource = Resources.getResource(requestBodyRootResourceFolder + relativeFilePath);
        return objectMapper.readTree(Resources.toString(resource, StandardCharsets.UTF_8));
    }

    /**
     * Reads a http request payload body fragment JSON file from {@code classpath:testDataFragmentRootPath}.
     * <p>
     * {@code testDataRootPath} is specified by {@code http.request.payload.fragment.root.resource.directory} property.
     *
     * @param relativeFilePath - relative path of the JSON file to load in {@code testDataRootPath}
     * @return The http request payload body.
     */
    @SneakyThrows
    public JsonNode parseResponseSchemaJsonFromFile(String relativeFilePath) {
        var resource = Resources.getResource(responseSchemaResourceFolder + relativeFilePath);
        return objectMapper.readTree(Resources.toString(resource, StandardCharsets.UTF_8));
    }
}

