package ru.spb.iac.pages.registerOfGovernmentService.table.government;

import ru.spb.iac.pages.registerOfGovernmentService.card.AuthorityCard;
import ru.spb.iac.pages.home.HomePage;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static ru.spb.iac.pages.Locators.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreatorNewAuthorityFullCycle {

    private WebDriver webDriver;
    private HomePage homePage;

    public CreatorNewAuthorityFullCycle(){}

    public CreatorNewAuthorityFullCycle(WebDriver webDriver){
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
    }

    public ElementsCollection getSelect(String str) {
        ElementsCollection select = $$(byCssSelector(str));
        return select;
    }

    public AuthorityCard create_new_authority(String executor){
        homePage.open(newAuthority);
        setIOGVName(executor)
            .setStateAuthority()
            .setIogvItemNumber()
            .setAuthorityName()
            .saveNewAuthirity();
        return new AuthorityCard(webDriver);
    }

    public CreatorNewAuthorityFullCycle setIOGVName(String iogvName){
        getSelect(authoritySelector).get(1).selectOption(iogvName);
        return new CreatorNewAuthorityFullCycle(webDriver);
    }

    public CreatorNewAuthorityFullCycle setStateAuthority(){
        getSelect(authoritySelector).get(2).selectOption("Дополнительное");
        return new CreatorNewAuthorityFullCycle(webDriver);
    }

    public CreatorNewAuthorityFullCycle setIogvItemNumber() {
        int random = 10000 + new Random().nextInt(10000);
        $(iogvItemNumber).setValue(String.valueOf(random));
        return new CreatorNewAuthorityFullCycle(webDriver);
    }

    public CreatorNewAuthorityFullCycle setAuthorityName() {
        $(newAuthorityName).setValue(authorityName);
        return new CreatorNewAuthorityFullCycle(webDriver);
    }

    public AuthorityCard saveNewAuthirity() {
        $(saveButton).click();
        return new AuthorityCard(webDriver);
    }

}
