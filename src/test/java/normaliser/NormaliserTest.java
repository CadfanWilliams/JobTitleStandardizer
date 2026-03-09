package normaliser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Normaliser")
class NormaliserTest {

    private final Normaliser normaliser = new Normaliser();

    @Test
    @DisplayName("should normalise 'Java engineer' to 'Software engineer'")
    void shouldNormaliseJavaEngineerToSoftwareEngineer() {
        assertEquals(Optional.of("Software engineer"), normaliser.normalise("Java engineer"));
    }

    @Test
    @DisplayName("should normalise 'C# engineer' to 'Software engineer'")
    void shouldNormaliseCSharpEngineerToSoftwareEngineer() {
        assertEquals(Optional.of("Software engineer"), normaliser.normalise("C# engineer"));
    }

    @Test
    @DisplayName("should normalise 'Accountant' to 'Accountant'")
    void shouldNormaliseAccountantToAccountant() {
        assertEquals(Optional.of("Accountant"), normaliser.normalise("Accountant"));
    }

    @Test
    @DisplayName("should normalise 'Chief Accountant' to 'Accountant'")
    void shouldNormaliseChiefAccountantToAccountant() {
        assertEquals(Optional.of("Accountant"), normaliser.normalise("Chief Accountant"));
    }

    @Test
    @DisplayName("should normalise 'Chief Quantity Surveyor' to 'Quantity surveyor'")
    void shouldNormaliseChiefQuantitySurveyorToQuantitySurveyor() {
        assertEquals(Optional.of("Quantity surveyor"), normaliser.normalise("Chief Quantity Surveyor"));
    }

    @Test
    @DisplayName("should normalise 'Solutions Architect' to 'Architect'")
    void shouldNormaliseSolutionsArchitectToArchitect() {
        assertEquals(Optional.of("Architect"), normaliser.normalise("Solutions Architect"));
    }

    @Test
    @DisplayName("should return 'Architect' unchanged")
    void shouldReturnArchitectUnchanged() {
        assertEquals(Optional.of("Architect"), normaliser.normalise("Architect"));
    }

    @Test
    @DisplayName("should return 'Software engineer' unchanged")
    void shouldReturnSoftwareEngineerUnchanged() {
        assertEquals(Optional.of("Software engineer"), normaliser.normalise("Software engineer"));
    }

    @Test
    @DisplayName("should match all-uppercase input case-insensitively")
    void shouldMatchAllUppercaseInput() {
        assertEquals(Optional.of("Software engineer"), normaliser.normalise("JAVA ENGINEER"));
    }

    @Test
    @DisplayName("should match all-lowercase input case-insensitively")
    void shouldMatchAllLowercaseInput() {
        assertEquals(Optional.of("Software engineer"), normaliser.normalise("java engineer"));
    }

    @Test
    @DisplayName("should strip leading whitespace before matching")
    void shouldStripLeadingWhitespace() {
        assertEquals(Optional.of("Software engineer"), normaliser.normalise("  Java engineer"));
    }

    @Test
    @DisplayName("should strip trailing whitespace before matching")
    void shouldStripTrailingWhitespace() {
        assertEquals(Optional.of("Software engineer"), normaliser.normalise("Java engineer  "));
    }

    @Test
    @DisplayName("should return empty Optional for 'Bricklayer' — no close match")
    void shouldReturnEmptyForBricklayer() {
        assertTrue(normaliser.normalise("Bricklayer").isEmpty());
    }

    @Test
    @DisplayName("should return empty Optional for 'Pilot' — no close match")
    void shouldReturnEmptyForPilot() {
        assertTrue(normaliser.normalise("Pilot").isEmpty());
    }

    @Test
    @DisplayName("should throw on null input")
    void shouldThrowOnNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> normaliser.normalise(null));
    }


}
