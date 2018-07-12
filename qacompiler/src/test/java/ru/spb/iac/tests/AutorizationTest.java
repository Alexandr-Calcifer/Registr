package ru.spb.iac.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class AutorizationTest extends TestStarter {
    @Test
    public void test() {
        homePage
            .authorisation(approver, approverPassword, true);
    }
}
