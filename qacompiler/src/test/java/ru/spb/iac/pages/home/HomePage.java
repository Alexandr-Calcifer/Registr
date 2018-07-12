package ru.spb.iac.pages.home;

import ru.spb.iac.appHelper.Helper;
import ru.spb.iac.pages.registerOfGovernmentService.table.government.CreatorNewAuthorityFullCycle;
import ru.spb.iac.pages.Notifications;
import ru.spb.iac.pages.registerOfGovernmentService.table.government.GovernmentDataTable;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.spb.iac.pages.Locators.*;
import static ru.spb.iac.pages.home.Locators.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HomePage{

    public WebDriver webDriver;
    public Helper helper;
    private CreatorNewAuthorityFullCycle creatorNewAuthority;
    private By locator = byCssSelector("[href='authority/']");

    public By getLocator(){
        return locator;
    }
    public HomePage(){}

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        helper = new Helper(webDriver);
    }

    public HomePage authorisation(String login, String password, boolean cycleAccess) {
//        Selenide.open(url_registr_pre);
        $(loginName).shouldHave(visible).clear();
        $(loginName).shouldHave(visible).sendKeys(login);
        $(loginPassword).shouldHave(visible).clear();
        $(loginPassword).shouldHave(visible).sendKeys(password);
        $(logInSend).shouldHave(visible).click();
        home_page_is_open();
        if(cycleAccess) {
            assertEquals(login, $$(navBar).last().getText());
        }else{
            assertEquals(login, $$(navBar).first().getText());
        }
        return new HomePage(webDriver);
    }

    public GovernmentDataTable open_registr_of_public_service_data_table() {
        $(registr_of_public_municipal_service_page).shouldHave(visible).click();
        return new GovernmentDataTable(webDriver);
    }

    public void open(String str) {
        $(str).shouldHave(visible).click();
    }

    public void home_page_is_open(){
        assertTrue(helper.isElementContains(currentDate, "Добрый вечер, "));
        assertTrue(helper.isElementContains(homePageInformation,"Вы успешно авторизовались в Реестре полномочий исполнительных органов государственной власти Санкт-Петербурга.\n" +
                "\n" +
                "Данный электронный ресурс предназначен для учета, хранения и предоставления пользователям актуальной информации о полномочиях исполнительных органов государственной власти Санкт-Петербурга, в том числе о реализованных в рамках них услугах, функциях, функциях государственного контроля (надзора).\n" +
                "\n" +
                "Реестр полномочий исполнительных органов государственной власти Санкт-Петербурга реализован в рамках подсистемы \"Реестр государственных и муниципальных услуг (функций) Санкт-Петербурга\" Межведомственной автоматизированной информационной системы предоставления в Санкт-Петербурге государственных и муниципальных услуг (МАИС ЭГУ) в 2015 году.\n" +
                "\n" +
                "Для продолжения работы выберите необходимый раздел Реестра."));
    }

    public void open_registr_of_public_service_data_table(By str) {
        $(str).shouldBe(visible).click();
    }

    public Notifications go_to_notifications() {
        $$(transitionToAuthority).last().shouldHave(visible).click();

        return new Notifications(webDriver);
    }

    public void logOut(String logOut) {
        $$(navBar).get(1).shouldHave(visible).click();
        $(logOut).shouldBe(visible).click();
    }
}
