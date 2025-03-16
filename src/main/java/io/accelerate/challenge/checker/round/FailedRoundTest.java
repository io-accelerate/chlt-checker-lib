package io.accelerate.challenge.checker.round;

import io.accelerate.challenge.definition.schema.RoundTestAssertion;

public record FailedRoundTest(String requestId, RoundTestAssertion failedAssertion, Object actualValue) {}
