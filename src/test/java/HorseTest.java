import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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

    /***
     * Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9.
     * Для этого нужно использовать MockedStatic и его метод verify;
     * ----------------------------------------------------------------------------------
     * Проверить, что метод присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9).
     * Для этого нужно замокать getRandomDouble,
     * чтобы он возвращал определенные значения, которые нужно задать параметризовав тест.*/
    @Test
    void testMoveCallGetRandomDoubleWithParameters() {

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse = new Horse("kek", 9);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"10.0, 5.0, 0.5", "20.0, 2.0, 0.8", "15.0, 3.0, 0.6"})
    void testMoveAssignToDistanceValueToFormula(double dist, double speed, double randomValue) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse = new Horse("kek", speed, dist);
            horse.move();
            double exp = dist + speed * randomValue;
            double actual = horse.getDistance();
            assertEquals(exp, actual);
        }
    }
}

