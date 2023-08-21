import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HippodromeTest {


    @Test
    public void nameIsNull() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });

        assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    public void nameIsEmpty(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            new Hippodrome(new ArrayList<>());
        });

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses(){

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Asd", i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());

    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (int i = 0; i < 50; i++) {
            verify(horses.get(i), times(1)).move();
        }

    }

    @Test
    public void getWinner(){

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Asd", i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get(), hippodrome.getWinner());

    }


}
