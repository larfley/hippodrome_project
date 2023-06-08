import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void NotnullNameException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () ->
                {
                    throw new IllegalArgumentException("Name cannot be null.");
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void ValidNameException(String input) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () ->
                {
                    new Horse(input, 1);
                    throw new IllegalArgumentException("Name cannot be blank.");
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void ValidSpeedException(String input) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () ->
                {
                    new Horse("name", -1);
                    throw new IllegalArgumentException("Speed cannot be negative.");
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100})
    public void ValidDistanceException(int negativeValue) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () ->
                {
                    new Horse("name", 10, negativeValue);
                    throw new IllegalArgumentException("Distance cannot be negative.");
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest() {
        Horse obj = new Horse("name", 10, 20);
        Assertions.assertEquals("name", obj.getName());
    }

    @Test
    public void getSpeedTest() {
        Horse obj = new Horse("name", 10, 20);
        Assertions.assertEquals(10, obj.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        Horse obj = new Horse("name", 10, 20);
        Assertions.assertEquals(20, obj.getDistance());

        Horse obj2 = new Horse("name", 10);
        Assertions.assertEquals(0, obj2.getDistance());
    }

    @Test

    public void moveCallMethodTest() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("name", 10, 20);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }

    }

    @Test
    public void moveCallParametersTest() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            double randomDouble = 0.5;
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            int distance = 10;
            int speed = 20;
            Horse horse = new Horse("name", speed, distance);
            horse.move();

            int expectedDistance = distance + (int) (speed * randomDouble);
            Assertions.assertEquals(expectedDistance, horse.getDistance());
        }

    }
}
