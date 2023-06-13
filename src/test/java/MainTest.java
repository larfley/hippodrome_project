import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Disabled("Тест отключен")
    @Test
    @Timeout(value = 22,unit = TimeUnit.SECONDS)
    public void mainTest() throws Exception{
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4),
                new Horse("Туз Пик", 2.5),
                new Horse("Зефир", 2.6),
                new Horse("Пожар", 2.7),
                new Horse("Лобстер", 2.8),
                new Horse("Пегас", 2.9),
                new Horse("Вишня", 3)
        );

        Long startTime = System.currentTimeMillis();
        Main.main(new String[0]);
        Long finishTime = System.currentTimeMillis() - startTime;
        assertTrue(finishTime <= 22,"Метод выполняется дольше 22 секунд");
    }

}