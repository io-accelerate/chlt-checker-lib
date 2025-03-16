package io.accelerate.challenge.checker.assertion;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.accelerate.challenge.definition.schema.RoundTestAssertion;

import java.util.Objects;

public class DefaultAssertionChecker implements AssertionChecker {
    private final ObjectMapper objectMapper;

    public DefaultAssertionChecker() {
        this.objectMapper = new ObjectMapper();;
    }

    @Override
    public boolean checkAssertion(RoundTestAssertion roundTestAssertion, Object actualValue) {
        boolean assertionPassed = false;
        
        //Compare as Json nodes
        JsonNode responseJsonNode = asJsonNode(actualValue);

        switch (roundTestAssertion.type()) {
            case EQUALS -> assertionPassed = Objects.equals(asJsonNode(roundTestAssertion.value()), responseJsonNode);
            case CONTAINS_STRING -> assertionPassed = responseJsonNode.asText().contains((String)roundTestAssertion.value());
            case CONTAINS_STRING_IGNORING_CASE -> {
                String expectedContainsToLower = ((String) roundTestAssertion.value()).toLowerCase();
                assertionPassed = responseJsonNode.asText().toLowerCase().contains(expectedContainsToLower);
            }
            case IS_NULL -> assertionPassed = responseJsonNode.isNull() == ((Boolean) roundTestAssertion.value());
        }

        return assertionPassed;
    }

    private JsonNode asJsonNode(Object value) {
        return objectMapper.valueToTree(value);
    }
}
