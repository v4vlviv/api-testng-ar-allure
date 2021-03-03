package booking;

import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;


public class SimpleTest {

    @Test
    public void simpleTest() {
        step("step 1");
        step("step 2");
    }
}

