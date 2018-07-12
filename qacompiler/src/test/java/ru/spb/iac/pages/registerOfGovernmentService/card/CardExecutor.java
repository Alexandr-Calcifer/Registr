package ru.spb.iac.pages.registerOfGovernmentService.card;

import ru.spb.iac.pages.Locators;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static ru.spb.iac.pages.registerOfGovernmentService.card.Locators.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardExecutor extends AuthorityCard {

    public CardExecutor(WebDriver webDriver) {
        super(webDriver);
    }

    public CardExecutor open_groups() {
        helper.waitForElementPresent(listOfVisibleServiceGroups);
        ElementsCollection elements = $$(listOfVisibleServiceGroups);
        if(elements.first().toString().contains(Locators.information)) {
            elements.first().click();
        }else{
            for (SelenideElement element : elements) {
                if(element.toString().contains(Locators.information)){
                    element.click();
                }
                return new CardExecutor(webDriver);
            }
        }
        return new CardExecutor(webDriver);
    }

    public CardExecutor open_servise_groups(String serviceName) {
        if(Selenide.$(ru.spb.iac.pages.registerOfGovernmentService.card.Locators.buttonGoBackToServiceGroups).has(Condition.visible)){
            Selenide.$(ru.spb.iac.pages.registerOfGovernmentService.card.Locators.buttonGoBackToServiceGroups).click();
        }
        ElementsCollection elements = Selenide.$$(ru.spb.iac.pages.registerOfGovernmentService.card.Locators.getServicesList);
        for (SelenideElement elems : elements) {
            if(elems.toString().contains(serviceName)){
                elems.click();
            }
        }
        return new CardExecutor(webDriver);
    }
}
