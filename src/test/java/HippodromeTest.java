import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HippodromeTest {


    @Test
    void horseValidNullException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        },"Horses cannot be null.");
    }
    @Test
    void horseValidEmptyException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<Horse> horses = Collections.emptyList();
            new Hippodrome(horses);
        },"Horses cannot be empty.");
    }

    public class getHorsesTest{
        @Test
        public void testGetHorses(){
            List<Horse> originalHorses = createOriginalHorses();
            Hippodrome hippodrome = new Hippodrome(originalHorses);
            List<Horse> returnedHorses = hippodrome.getHorses();
            Assertions.assertEquals(originalHorses.size(), returnedHorses.size());
            for (int i = 0; i < originalHorses.size(); i++) {
                Assertions.assertSame(originalHorses.get(i), returnedHorses.get(i));
            }
        }
        @Test
        private List<Horse> createOriginalHorses(){
            List<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                Horse horse = new Horse("Horse" + (i+1),i+1);
                horses.add(horse);
                }
            return horses;
        }

    }

    public class moveTest{
        @Test
        public void moveAllHorses(){
            List<Horse> mockedHorses = createMockedHorses();
            Hippodrome hippodrome = new Hippodrome(mockedHorses);
            hippodrome.move();
            for (Horse horse:mockedHorses){
                Mockito.verify(horse).move();
            }
        }
        @Test
        public List<Horse> createMockedHorses(){
            List<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                Horse horse = Mockito.mock(Horse.class);
                horses.add(horse);
            }
            return horses;
        }
    }

    @Test
    public void getWinnerTest(){
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Make",5));
        horses.add(new Horse("Susy",6));
        horses.add(new Horse("Madison",3));

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.getWinner();


    }
}