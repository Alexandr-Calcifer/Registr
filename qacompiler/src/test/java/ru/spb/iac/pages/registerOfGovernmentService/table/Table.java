package ru.spb.iac.pages.registerOfGovernmentService.table;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import ru.spb.iac.appHelper.Helper;

import static com.codeborne.selenide.Selenide.$;

public class Table {

    public Helper helper;
    public WebDriver webDriver;

    public Table(WebDriver webDriver){
        this.webDriver = webDriver;
        helper = new Helper(webDriver);
    }

    public void click(String str) {
        $(str).shouldHave(Condition.visible).click();
    }


}
