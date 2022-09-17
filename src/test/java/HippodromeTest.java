import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void noneHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void noneHorsesMessageException() {
        try {
            new Hippodrome(new ArrayList<>());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorses() {
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horsesList.add(new Horse ("name of horse " + i, 1.0, 1.0));
        }

        Hippodrome hippodrome = new Hippodrome(horsesList);
        assertEquals(horsesList, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);

        new Hippodrome(horseList).move();

        for (Horse horse : horseList) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner() {
        Horse horse1 = new Horse("horse1", 20.0, 10.0);
        Horse horse2 = new Horse("horse2", 21.0, 12.0);
        Horse horse3 = new Horse("horse3", 22.0, 14.0);
        Horse horse4 = new Horse("horse4", 23.0, 16.0);
        Horse horse5 = new Horse("horse5", 24.0, 18.0);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5));

        assertSame(horse5, hippodrome.getWinner());
    }
}
