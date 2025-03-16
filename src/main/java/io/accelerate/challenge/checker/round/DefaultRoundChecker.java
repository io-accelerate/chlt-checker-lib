package io.accelerate.challenge.checker.round;

import io.accelerate.challenge.checker.assertion.DefaultAssertionChecker;
import io.accelerate.challenge.checker.assertion.AssertionChecker;
import io.accelerate.challenge.definition.schema.RoundTest;
import io.accelerate.challenge.definition.schema.RoundTestAssertion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultRoundChecker implements RoundChecker {

    private final AssertionChecker defaultAssertionChecker;

    public DefaultRoundChecker() {
        defaultAssertionChecker = new DefaultAssertionChecker();
    }

    public List<FailedRoundTest> checkResponses(List<RoundTest> roundTests, List<RoundResponseToCheck> receivedResponses) {
        List<FailedRoundTest> failedRoundTests = new ArrayList<>();

        for (RoundTest roundTest : roundTests) {
            String requestId = roundTest.id();
            
            //Get first response
            Optional<RoundResponseToCheck> actualResponse = getFirstResponseForId(receivedResponses, requestId);
            Object actualValue = null;
            if (actualResponse.isPresent()) {
                actualValue = actualResponse.get().value();
            }

            //Compare as Json nodes
            RoundTestAssertion roundTestAssertion = roundTest.roundTestAssertion();
            boolean assertionPassed = defaultAssertionChecker.checkAssertion(roundTestAssertion, actualValue);

            if (!assertionPassed){
                failedRoundTests.add(new FailedRoundTest(requestId, roundTestAssertion, actualValue));
            }
        }
        return failedRoundTests;
    }

    private static Optional<RoundResponseToCheck> getFirstResponseForId(List<RoundResponseToCheck> receivedResponses, String requestId) {
        return receivedResponses.stream().filter(response ->
                Objects.equals(response.requestId(), requestId)).findFirst();
    }
}
