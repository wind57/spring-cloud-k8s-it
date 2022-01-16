package nested;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedTest {

    @Nested
    class One {
        @Test
        public void test() {
            Assertions.assertTrue(1 == 1);
        }
    }

    @Nested
    class Two {

        @Test
        public void test() {
            Assertions.assertTrue(2 == 2);
        }
    }

}
