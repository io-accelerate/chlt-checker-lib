package io.accelerate.challenge.checker.assertion;

import io.accelerate.challenge.definition.schema.RoundTestAssertion;

public interface AssertionChecker {
    boolean checkAssertion(RoundTestAssertion roundTestAssertion, Object actualValue);
}
