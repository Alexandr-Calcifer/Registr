package ru.spb.iac.pages.registerOfGovernmentService.table.municipal;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.spb.iac.appHelper.Helper;
import ru.spb.iac.pages.home.HomePage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;
import static ru.spb.iac.pages.registerOfGovernmentService.table.municipal.Locators.*;

public class CreatorNewMunicipalAuthority {
    private WebDriver webDriver;
    private HomePage homePage;
    private Helper helper;

    public CreatorNewMunicipalAuthority(WebDriver webDriver){
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        helper = new Helper(webDriver);
    }

    public void create_new_authority(int quantityIogvNames,  String name, boolean withoutService, String serviceName) {
        fill_in_first_step(quantityIogvNames, name);
        click(continueButton);
        fill_in_second_step(quantityIogvNames, name, withoutService);
        fill_in_last_step(quantityIogvNames, name, withoutService, serviceName);
        click(save);
        assertTrue(helper.isElementContains(modalDialogMessage, "Полномочие ОМСУ будет добавлено! Вы будете перенаправлены на главную страницу Реестра."));
        click(modalDialogAccept);
        homePage.home_page_is_open();
    }

    private void fill_in_last_step(int quantityIogvNames, String name, boolean withoutService, String serviceName) {
        Assert.assertEquals(quantityIogvNames + 1, $$(qantityAuthority).size());
        assertTrue(helper.isElementContains(stepTwoAlert, name));
        assertTrue(helper.isElementPresent(addService));
        assertTrue(helper.isElementPresent(save));

        click(addService);
        assertTrue(helper.isElementPresent(cancelService));
        assertTrue(helper.isElementPresent(saveService));

        $(newServiceField).clear();
        $(newServiceField).setValue(serviceName);
        click(saveService);
    }

    private void fill_in_second_step(int quantityIogvNames,  String name, boolean withoutService) {
        Assert.assertEquals(quantityIogvNames + 1, $$(qantityAuthority).size());
        assertTrue(helper.isElementContains(stepTwoAlert, name));
        assertTrue(helper.isElementPresent(save));
        assertTrue(helper.isElementPresent(continueButton));

        if(withoutService){
            $(save).click();
        }else{
            $(continueButton).click();
        }
    }

    private void fill_in_first_step(int quantityIogvNames, String name) {
        for (int i = 0; i<$$(necessarilyFields).size(); i++) {
            if(i==0){
                for (int j = 0; j < quantityIogvNames; j++) {
                    $$(necessarilyFields).get(i).selectOption(j);
                }
            }else{
                $$(necessarilyFields).get(i).selectOption(0);
            }
        }
        $(authorityName).setValue(name);
    }

    public void click(String str) {
        $(str).shouldHave(Condition.visible).click();
    }

}
