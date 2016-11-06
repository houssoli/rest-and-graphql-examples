package se.ivankrizsan.restexample.helpers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

/**
 * Helper methods that creates JSON representation from objects and vice versa.
 *
 * @author Ivan Krizsan
 */
public final class JsonConverter {
    /**
     * Default constructor.
     * Made private to prevent instantiation of this helper class.
     */
    private JsonConverter() {
    }

    /**
     * Creates a JSON representation of supplied object.
     *
     * @param inObjectToSerialize Object to create JSON representation from.
     * @return JSON representation of supplied object.
     * @throws Exception If error occurs creating JSON representation.
     */
    public static String objectToJson(final Object inObjectToSerialize)
        throws Exception {
        final ObjectMapper theJsonObjectMapper = createAndConfigureJsonObjectMapper();
        final ObjectWriter theJsonObjectWriter = theJsonObjectMapper.writer();
        final String theJsonString = theJsonObjectWriter.writeValueAsString(inObjectToSerialize);
        return theJsonString;
    }

    /**
     * Creates an object of supplied type from supplied JSON string.
     */
    public static <T> T jsonToObject(final String inJsonRepresentation, final Class<T> inDestinationType)
        throws IOException {
        final ObjectMapper theJsonObjectMapper = createAndConfigureJsonObjectMapper();
        final ObjectReader theJsonObjectReader = theJsonObjectMapper.readerFor(inDestinationType);
        return theJsonObjectReader.readValue(inJsonRepresentation);
    }

    /**
     * Creates and configures the object mapper used when converting between
     * JSON representation and objects.
     *
     * @return Jackson object mapper.
     */
    private static ObjectMapper createAndConfigureJsonObjectMapper() {
        final ObjectMapper theJsonObjectMapper = new ObjectMapper();
        /* Do not include properties which value is null in JSON representation. */
        theJsonObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return theJsonObjectMapper;
    }
}