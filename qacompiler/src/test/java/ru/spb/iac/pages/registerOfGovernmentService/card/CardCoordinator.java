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

public class CardCoordinator extends AuthorityCard {

    public CardCoordinator(WebDriver webDriver) {
        super(webDriver);
    }

    public CardCoordinator open_groups() {
        helper.waitForElementPresent(listOfVisibleServiceGroups);
        ElementsCollection elements = $$(listOfVisibleServiceGroups);
        if(elements.first().toString().contains(ru.spb.iac.pages.Locators.information)) {
            elements.first().shouldHave(Condition.visible).click();
        }else{
            for (SelenideElement element : elements) {
                if(element.toString().contains(Locators.information)){
                    element.shouldHave(Condition.visible).click();
                }
                return new CardCoordinator(webDriver);
            }
        }
        return new CardCoordinator(webDriver);
    }

    public CardCoordinator open_servise_groups(String serviceName) {
        if($(buttonGoBackToServiceGroups).has(Condition.visible)){
            $(buttonGoBackToServiceGroups).click();
        }
        ElementsCollection elements = Selenide.$$(getServicesList);
        for (SelenideElement elems : elements) {
            if(elems.toString().contains(serviceName)){
                elems.shouldHave(Condition.visible).click();
            }
        }

        return new CardCoordinator(webDriver);
    }
}
