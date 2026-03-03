package test;

import normaliser.Normaliser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NormaliserTest {

    private final Normaliser normaliser = new Normaliser();

    @Test
    void shouldMatchJavaEngineerToSoftwareEngineer() {
        assertEquals("Software engineer",
                normaliser.normalise("Java engineer"));
    }

    @Test
    void shouldMatchCSharpEngineerToSoftwareEngineer() {
        assertEquals("Software engineer",
                normaliser.normalise("C# engineer"));
    }

    @Test
    void shouldMatchAccountantToAccountant() {
        assertEquals("Accountant",
                normaliser.normalise("Accountant"));
    }

    @Test
    void shouldMatchChiefAccountantToAccountant() {
        assertEquals("Accountant",
                normaliser.normalise("Chief Accountant"));
    }

    @Test
    void shouldThrowOnNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> normaliser.normalise(null));
    }
}