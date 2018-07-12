package ru.spb.iac.pages;

import ru.spb.iac.pages.registerOfGovernmentService.card.AuthorityCard;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static ru.spb.iac.pages.Locators.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class Notifications{

    WebDriver webDriver;

    public Notifications(){
        super();
    }

    public Notifications(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public AuthorityCard go_to_transition_to_authority_through_notifications(String notification) {
        ElementsCollection notifications = $$(transitionToAuthorityThroughNotificationsButton);
        if(notifications.size() > 0){
            Assert.assertTrue($$(byXpath(notificationNames)).first().toString().contains(notification));
            notifications.first().shouldHave(Condition.visible).click();
        }else {
            System.out.println("Не пришло уведомление по полномочию " + authorityName);
        }
        return new AuthorityCard(webDriver);
    }

}
