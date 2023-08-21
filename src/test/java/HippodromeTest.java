import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


// мы мокаем класс, а можем ли мы создавать разные объекты одного мокнутого класса
public class HippodromeTest {


    @Test
    public void isNull() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });

        assertEquals(exception.getMessage(), "Horses cannot be null.");

    }

    @Test
    public void isEmpty(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            new Hippodrome(new ArrayList<>());
        });

        assertEquals(exception.getMessage(), "Horses cannot be empty.");
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

        Horse horse = Mockito.mock(Horse.class);

        for (int i = 0; i < 50; i++) {
            horses.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        verify(horse, times(50)).move();

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
