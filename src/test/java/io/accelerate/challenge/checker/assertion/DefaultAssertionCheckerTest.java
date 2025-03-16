package io.accelerate.challenge.checker.assertion;

import io.accelerate.challenge.definition.schema.RoundTestAssertion;
import io.accelerate.challenge.definition.schema.RoundTestAssertionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class DefaultAssertionCheckerTest {
    
    private AssertionChecker checker;

    @BeforeEach
    void setUp() {
        checker = new DefaultAssertionChecker();
    }
    
    @Test
    void assertion_equals_positive_test() {

        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.EQUALS, "someValue"), 
                "someValue");

        assertThat(assertionPassed, is(true));
    }

    @Test
    void assertion_equals_negative_test() {

        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.EQUALS, "someValue"),
                "wrongValue");

        assertThat(assertionPassed, is(false));  
    }

    @Test
    void assertion_contains_string_positive_test() {

        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.CONTAINS_STRING, "someValue"),
                "string that has someValue in it");

        assertThat(assertionPassed, is(true));
    }

    @Test
    void assertion_contains_string_negative_test() {

        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.CONTAINS_STRING, "someValue"),
                "wrongValue");

        assertThat(assertionPassed, is(false));
    }

    @Test
    void assertion_contains_string_ignoring_case_positive_test() {
        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.CONTAINS_STRING_IGNORING_CASE, "someValue"),
                "string that has SomeValue in different case");

        assertThat(assertionPassed, is(true));
    }

    @Test
    void assertion_contains_string_ignoring_case_negative_test() {
        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.CONTAINS_STRING_IGNORING_CASE, "someValue"),
                "wrongValue");

        assertThat(assertionPassed, is(false));
    }

    @Test
    void assertion_is_null_positive_test() {
        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.IS_NULL, true),
                null);

        assertThat(assertionPassed, is(true));
    }

    @Test
    void assertion_is_null_negative_test() {
        boolean assertionPassed = checker.checkAssertion(
                new RoundTestAssertion(RoundTestAssertionType.IS_NULL, false),
                null);

        assertThat(assertionPassed, is(false));
    }

}