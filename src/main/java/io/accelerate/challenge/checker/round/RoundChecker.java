package io.accelerate.challenge.checker.round;

import io.accelerate.challenge.definition.schema.RoundTest;

import java.util.List;

public interface RoundChecker {
    List<FailedRoundTest> checkResponses(List<RoundTest> roundTests, List<RoundResponseToCheck> receivedResponses);
}
