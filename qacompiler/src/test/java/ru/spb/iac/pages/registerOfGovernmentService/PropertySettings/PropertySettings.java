package ru.spb.iac.pages.registerOfGovernmentService.PropertySettings;

import ru.spb.iac.appHelper.Helper;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;

import static ru.spb.iac.pages.registerOfGovernmentService.PropertySettings.Locators.propertyAddButton;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PropertySettings {

    public WebDriver webDriver;
    public Helper helper;

    public PropertySettings(WebDriver webDriver){
        this.webDriver = webDriver;
        helper = new Helper(webDriver);
    }

    public PropertySettings click(String str){
        $(str).shouldHave(Condition.visible).click();
        return new PropertySettings(webDriver);
    }

    public CreatorProperty go_to_property_add(String property) {
        click(propertyAddButton);
        click(property);
        return new CreatorProperty(webDriver);
    }
}
