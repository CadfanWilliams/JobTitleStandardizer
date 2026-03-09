package normaliser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TokenIntersectionComparator")
class TokenIntersectionComparatorTest {

    private final TokenIntersectionComparator comparator = new TokenIntersectionComparator();

    @Test
    @DisplayName("should return 1.0 for identical strings")
    void shouldReturnPerfectScoreForIdenticalStrings() {
        assertEquals(1.0, comparator.compare("Software engineer", "Software engineer"));
    }

    @Test
    @DisplayName("should return 1.0 for identical strings regardless of case")
    void shouldBeCaseInsensitive() {
        assertEquals(1.0, comparator.compare("SOFTWARE ENGINEER", "Software engineer"));
    }

    @Test
    @DisplayName("should return 0.0 for completely different strings")
    void shouldReturnZeroForNoOverlap() {
        assertEquals(0.0, comparator.compare("Bricklayer", "Software engineer"));
    }

    @Test
    @DisplayName("should return 0.0 for null input")
    void shouldReturnZeroForNullInput() {
        assertEquals(0.0, comparator.compare(null, "Software engineer"));
    }

    @Test
    @DisplayName("should return 0.0 for null target")
    void shouldReturnZeroForNullTarget() {
        assertEquals(0.0, comparator.compare("Java engineer", null));
    }
}
