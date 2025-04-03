package io.accelerate.challenge.checker.assertion;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class StringUtilsTest {

    // Tests for stripTrailingSpaces

    @Test
    void stripTrailingSpaces_withTrailingSpaces() {
        String input = "Hello World   \nThis is a test   \n";
        String expected = "Hello World\nThis is a test";
        assertThat(StringUtils.stripTrailingSpaces(input), is(expected));
    }

    @Test
    void stripTrailingSpaces_withSpacesInMiddle() {
        String input = "Hello     World";
        String expected = "Hello     World";
        assertThat(StringUtils.stripTrailingSpaces(input), is(expected));
    }

    @Test
    void stripTrailingSpaces_withNoTrailingSpaces() {
        String input = "Hello\nWorld";
        String expected = "Hello\nWorld";
        assertThat(StringUtils.stripTrailingSpaces(input), is(expected));
    }

    @Test
    void stripTrailingSpaces_withEmptyString() {
        String input = "";
        String expected = "";
        assertThat(StringUtils.stripTrailingSpaces(input), is(expected));
    }

    @Test
    void stripTrailingSpaces_withOnlySpaces() {
        String input = "   ";
        String expected = "";
        assertThat(StringUtils.stripTrailingSpaces(input), is(expected));
    }

    // Tests for removeEmptyLines

    @Test
    void removeEmptyLines_withEmptyLines() {
        String input = "Hello\n\nWorld\n\n";
        String expected = "Hello\nWorld";
        assertThat(StringUtils.removeEmptyLines(input), is(expected));
    }

    @Test
    void removeEmptyLines_withNoEmptyLines() {
        String input = "Hello\nWorld";
        String expected = "Hello\nWorld";
        assertThat(StringUtils.removeEmptyLines(input), is(expected));
    }

    @Test
    void removeEmptyLines_withOnlyEmptyLines() {
        String input = "\n\n";
        String expected = "";
        assertThat(StringUtils.removeEmptyLines(input), is(expected));
    }
}
