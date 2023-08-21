import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

//can be isEmpty with regex
//can we replace our double values to random double values "2.2 -> random"

class HorseTest {

    @Test
    public void isNull() {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.3);
        });

        //Text check
        assertEquals(exception.getMessage(), "Name cannot be null.");

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", " ", "\n", "\r"})
    public void isEmpty(String args) {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(args, 22);
        });

        //Text check
        assertEquals(exception.getMessage(), "Name cannot be blank.");
    }

    @Test
    public void speedLessThanZero() {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Asd", -2.2);
        });

        //Text check
        assertEquals(exception.getMessage(), "Speed cannot be negative.");

    }

    @Test
    public void distanceLessThanZero() {

        //Exception check
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Asd", 2.2, -22);
        });

        //Text check
        assertEquals(exception.getMessage(), "Distance cannot be negative.");

    }

    @Test
    public void getName() {
        Horse horse = new Horse("Asd", 2.2);
        assertEquals(horse.getName(), "Asd");
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("Asd", 2.2);
        assertEquals(horse.getSpeed(), 2.2);
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("Asd", 2.2);
        assertEquals(horse.getDistance(), 0);

        horse = new Horse("Asd", 2.2, 34);
        assertEquals(horse.getDistance(), 34);
    }

    @Test
    public void move() {

        try (MockedStatic<Horse> fakeClass = Mockito.mockStatic(Horse.class)) {
            fakeClass.when(() -> {
                Horse.getRandomDouble(0.2, 0.9);
            }).thenReturn((Math.random() * (0.9 - 0.2)) + 0.9);

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