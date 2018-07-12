package ru.spb.iac.pages.authorization;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;

public class Authorization {

    private WebDriver webDriver;

    public Authorization(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openPageAuthorisation(String str) {
        open(str);
    }
}
