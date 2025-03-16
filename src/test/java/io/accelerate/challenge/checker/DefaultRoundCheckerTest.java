package io.accelerate.challenge.checker;

import io.accelerate.challenge.checker.round.DefaultRoundChecker;
import io.accelerate.challenge.checker.round.FailedRoundTest;
import io.accelerate.challenge.checker.round.RoundResponseToCheck;
import io.accelerate.challenge.definition.schema.MethodCall;
import io.accelerate.challenge.definition.schema.RoundTest;
import io.accelerate.challenge.definition.schema.RoundTestAssertion;
import io.accelerate.challenge.definition.schema.RoundTestAssertionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

class DefaultRoundCheckerTest {
    private DefaultRoundChecker checker;

    @BeforeEach
    void setUp() {
        checker = new DefaultRoundChecker();
    }

    @Test
    void no_round_tests_means_nothing_to_fail() {
        List<FailedRoundTest> failedRoundTests = checker.checkResponses(List.of(), List.of());

        assertThat(failedRoundTests, hasSize(0));
    }

    @Test
    void checks_received_responses_against_round_tests() {
        RoundTestAssertion roundTestAssertion = new RoundTestAssertion(RoundTestAssertionType.EQUALS, "someValue");
        List<RoundTest> roundTests = List.of(
                new RoundTest("someId", new MethodCall("someMethod", List.of()), roundTestAssertion)
        );
        List<RoundResponseToCheck> receivedResponses = List.of(
                new RoundResponseToCheck("someId", "someValue")
        );

        List<FailedRoundTest> failedRoundTests = checker.checkResponses(roundTests, receivedResponses);

        assertThat(failedRoundTests, hasSize(0));

    }
    
    @Test
    void returns_failed_round_tests_for_each_round_test_that_failed() {
        RoundTestAssertion roundTestAssertion = new RoundTestAssertion(RoundTestAssertionType.EQUALS, "someValue");
        List<RoundTest> roundTests = List.of(
                new RoundTest("someId", new MethodCall("someMethod", List.of()), roundTestAssertion)
        );
        List<RoundResponseToCheck> receivedResponses = List.of(
                new RoundResponseToCheck("someId", "wrongValue")
        );

        List<FailedRoundTest> failedRoundTests = checker.checkResponses(roundTests, receivedResponses);

        assertThat(failedRoundTests, contains(new FailedRoundTest("someId", roundTestAssertion, "wrongValue")));
    }

    @Test
    void the_lack_of_a_response_should_be_treated_as_null() {
        RoundTestAssertion roundTestAssertion = new RoundTestAssertion(RoundTestAssertionType.EQUALS, "someValue");
        List<RoundTest> roundTests = List.of(
                new RoundTest("someId", new MethodCall("someMethod", List.of()), roundTestAssertion)
        );
        List<RoundResponseToCheck> receivedResponses = List.of();

        List<FailedRoundTest> failedRoundTests = checker.checkResponses(roundTests, receivedResponses);

        assertThat(failedRoundTests, contains(new FailedRoundTest("someId", roundTestAssertion, null)));
    }

    @Test
    void when_multiple_responses_present_only_consider_the_first_one() {
        RoundTestAssertion roundTestAssertion = new RoundTestAssertion(RoundTestAssertionType.EQUALS, "someValue");
        List<RoundTest> roundTests = List.of(
                new RoundTest("someId", new MethodCall("someMethod", List.of()), roundTestAssertion)
        );
        List<RoundResponseToCheck> receivedResponses = List.of(
                new RoundResponseToCheck("someId", "wrongValue"),
                new RoundResponseToCheck("someId", "someValue")
        );

        List<FailedRoundTest> failedRoundTests = checker.checkResponses(roundTests, receivedResponses);

        assertThat(failedRoundTests, contains(new FailedRoundTest("someId", roundTestAssertion, "wrongValue")));
    }

}