import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HorseTest {

    @Test
    public void nameIsNull() {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.3);
        });

        //Text check
        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", " ", "\n", "\r"})
    public void nameIsEmpty(String args) {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(args, 22);
        });

        //Text check
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void speedLessThanZero() {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Asd", -2.2);
        });

        //Text check
        assertEquals("Speed cannot be negative.", exception.getMessage());

    }

    @Test
    public void distanceLessThanZero() {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Asd", 2.2, -22);
        });

        //Text check
        assertEquals("Distance cannot be negative.", exception.getMessage());

    }

    @Test
    public void getName() {
        Horse horse = new Horse("Asd", 2.2);
        assertEquals("Asd", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("Asd", 2.2);
        assertEquals(2.2, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("Asd", 2.2);
        assertEquals(0, horse.getDistance());

        horse = new Horse("Asd", 2.2, 34);
        assertEquals(34, horse.getDistance());
    }

    @Test
    public void move() {

        try (MockedStatic<Horse> fakeClass = Mockito.mockStatic(Horse.class)) {

            Horse horse = new Horse("Asd", 2.2);
            horse.move();

            fakeClass.verify(() -> {
                Horse.getRandomDouble(0.2, 0.9);
            }, times(1));

        }

    }

    @ParameterizedTest
    @ValueSource(doubles = {3, 23, 1, 2, 3})
    public void moveCheckDistance(double arg) {

        try (MockedStatic<Horse> fakeClass = Mockito.mockStatic(Horse.class)) {
            fakeClass.when(() -> {
                Horse.getRandomDouble(0.2, 0.9);
            }).thenReturn(arg);

            Horse horse = new Horse("Asd", 2.2);
            horse.move();

            assertEquals(horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9),
                    horse.getDistance());

        }

    }


}