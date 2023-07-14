import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HippodromeTest {
    @Mock
    List<Horse> emptyList = new ArrayList<>();

    @Test
    void whenConstructorWithNullFirstParameter_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    void whenConstructorWithNullFirstParameter_thenMessageHorsesCannotBeNull() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void whenConstructorWithEmptyListParameter_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
    }

    @Test
    void whenConstructorWithEmptyListParameter_thenMessageHorsesCannotBeEmpty() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(emptyList)
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horseList.add(new Horse("Horse_" + i, 7, 9));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(hippodrome.getHorses(), horseList);
    }

    @Test
    void moveTest() {

        List<Horse> mockedHorseList = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            Horse mockHorse = mock(Horse.class);
            mockedHorseList.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(mockedHorseList);
        hippodrome.move();

        for (Horse mockHorse : mockedHorseList) {
            Mockito.verify(mockHorse).move();
        }

    }
    @Test
    void getWinner() {
        List<Horse> testHorses = new ArrayList<>();
        Horse kek = new Horse("kek", 5, 7);
        Horse lol = new Horse("kek", 5, 9);
        testHorses.add(kek);
        testHorses.add(lol);
        Hippodrome hippodromeTest = new Hippodrome(testHorses);
        assertEquals(lol, hippodromeTest.getWinner());

    }
}