package ru.spb.iac.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;



public class TestStarter extends TestBase{

    @BeforeAll
    public void init(){
        initChromeDriver();
        authorization.openPageAuthorisation(url_registr);
    }

    @AfterAll
    public void exit(){
        homePage.logOut(logOut);
        webDriver.close();
    }
}
