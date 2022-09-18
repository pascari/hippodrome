import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 20.0, 10.0));
    }

    @Test
    public void nullNameMessageException() {
        try {
            new Horse(null, 20.0, 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\n", "\t"})
    public void blankNameException(String name) {
        try {
            new Horse(name, 20.0, 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1.0, 10.0));
    }

    @Test
    public void negativeSpeedMessageException() {
        try {
            new Horse("name", -1.0, 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 20.0, -1.0));
    }

    @Test
    public void negativeDistanceMessageException() {
        try {
            new Horse("name", 20.0, -1.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getName() {
        Horse horse = new Horse("Lightning", 20.0, 10.0);
        assertEquals("Lightning", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("Lightning", 20.0, 10.0);
        assertEquals(20.0, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("Lightning", 20.0, 10.0);
        assertEquals(10.0, horse.getDistance());
    }

    @Test
    public void noDistanceData() {
        Horse horse = new Horse("Lightning", 20.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Lightning", 20.0, 10.0).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Lightning", 20.0, 10.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(10.0 + 20.0 * random, horse.getDistance());
        }
    }
}
