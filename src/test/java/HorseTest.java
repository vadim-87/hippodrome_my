import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    Horse horse;

    @Test
    void whenConstructorWithNullFirstParameter_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2.5, 6.8));
    }

    @Test
    void whenConstructorWithNullFirstParameter_thenMessageNameCannotBeNull() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 2.5, 6.8)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "  \t  "})
    void whenConstructorWithEmptyOrSpaceFirstParameter_thenIllegalArgumentException(String namesArgs) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(namesArgs, 12, 5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "  \t  "})
    void whenConstructorWithEmptyOrSpaceFirstParameter_thenMessageNameCannotBeBlank(String namesArgs) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(namesArgs, 2.5, 6.8)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void whenConstructorWithNegativeNumSecondParameter_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("kek", -5.0, 90.0)
        );
    }

    @Test
    void whenConstructorWithNegativeNumSecondParameter_thenMessageSpeedCannotBeNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("anyName", -2.5, 69.8)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void whenConstructorWithNegativeNumThirdParameter_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("kek", 5.0, -90.0)
        );
    }

    @Test
    void whenConstructorWithNegativeNumThirdParameter_thenMessageDistanceCannotBeNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("anyName", 2.5, -69.8)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        String name = "kek";
        horse = new Horse(name, 5);
        assertEquals(name, horse.getName());
    }
    @Test
    void getSpeedTest() {
        int speed = 5;
        horse = new Horse("kek", speed);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        int distance = 78;
        horse = new Horse("kek", 5, distance);
        assertEquals(78, horse.getDistance());
        horse = new Horse("kek", 17);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move() {

    }
}

