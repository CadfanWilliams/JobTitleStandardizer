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
    @DisplayName("should throw on null input")
    void shouldThrowOnNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> normaliser.normalise(null));
    }


}
