package ru.spb.iac.pages.registerOfGovernmentService.table.municipal;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.spb.iac.appHelper.Helper;
import ru.spb.iac.pages.registerOfGovernmentService.table.Table;
import ru.spb.iac.pages.registerOfGovernmentService.table.government.CreatorNewAuthorityFullCycle;
import ru.spb.iac.pages.registerOfGovernmentService.table.government.GovernmentDataTable;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.spb.iac.pages.registerOfGovernmentService.table.municipal.Locators.*;

public class MunicipalDataTable extends Table{

    public MunicipalDataTable(WebDriver webDriver){
        super(webDriver);
    }

    public GovernmentDataTable open_public_service_data_table() {
        click(governmentTableTab);
        return new GovernmentDataTable(webDriver);
    }

    public void click(String str) {
        $(str).shouldHave(Condition.visible).click();
    }

    public CreatorNewMunicipalAuthority open_create_authority(){
        helper.waitForElementPresent(createAuthorityButton);
        click(createAuthorityButton);
        helper.waitForElementPresent("#fuelux-wizard-container");
        Assert.assertTrue(helper.isElementContains(head, "Добавление полномочия ОМСУ"));
        Assert.assertEquals($$(firstStepNecessarily).size(), 2);
        return new CreatorNewMunicipalAuthority(webDriver);
    }
}
