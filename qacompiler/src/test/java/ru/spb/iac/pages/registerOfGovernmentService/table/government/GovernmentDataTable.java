package ru.spb.iac.pages.registerOfGovernmentService.table.government;

import ru.spb.iac.appHelper.Helper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ru.spb.iac.pages.registerOfGovernmentService.table.Table;
import ru.spb.iac.pages.registerOfGovernmentService.table.municipal.MunicipalDataTable;

import static ru.spb.iac.pages.registerOfGovernmentService.table.government.Locators.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GovernmentDataTable extends Table{

    public GovernmentDataTable(WebDriver webDriver){
        super(webDriver);
    }

    public GovernmentDataTable searching_through_overall_serch(String query) {
        open_search(overall_search_tab);
        inputValue(search, query);
        click(saveChangeSearch);
        return new GovernmentDataTable(webDriver);
    }

    private GovernmentDataTable open_search(String tab) {
        click(full_search_menu);
        click(tab);
        return new GovernmentDataTable(webDriver);
    }

    public void click(String str) {
        $(str).shouldHave(Condition.visible).click();
    }

    public GovernmentDataTable searching_through_advanced_serch(String type, String query){
        open_search(advanced_search_tab);
        click(extendedSearch);
        $(searchReset).click();
        select_property_in_search(type);
        select_property_value_in_search(query);
        click(saveChangeSearch);
        return new GovernmentDataTable(webDriver);
    }

    private void select_property_value_in_search(String query) {
        click(extendeValueSearch);
        $(extendeSearchField).clear();
        $(extendeSearchField).sendKeys(query);
        helper.waitForElementPresent(extendeSoughtValue);
        $$(extendeSoughtValue).last().click();
    }

    private void select_property_in_search(String type) {
        click(extendedMultiSearch);
        $(inputExtendedValueSearch).sendKeys(type);
        $(inputExtendedValueSearch).pressEnter();
    }

    public void open_custom_columns(String... property){
        click(columnSettings);
        Assert.assertTrue(helper.isElementPresent(columnModalLabel));
        for (int i = 0; i < property.length; i++) {
            inputValue(columnSettingsSearch, property[i]);
            select_property_to_display_in_table();
            click(saveColumn);
        }
        helper.waitForElementPresent(".dataTables_scrollHead [aria-label='" + property + "']");
        helper.scrolling($(".dataTables_scrollHead [aria-label='" + property + "']"));
    }

    private GovernmentDataTable inputValue(String element, String service) {
        $(element).shouldHave(Condition.visible).clear();
        $(element).shouldHave(Condition.visible).sendKeys(service);
        return new GovernmentDataTable(webDriver);
    }

    private void select_property_to_display_in_table(){
        click(filteredGroup);
        click(columnSettingsCheckBoxProperty);
    }

    public MunicipalDataTable open_municipal_service_data_table() {
        click(municipalTableTab);
        return new MunicipalDataTable(webDriver);
    }

    public CreatorNewAuthorityFullCycle open_create_authority(){
        click(createAuthorityButton);
        return new CreatorNewAuthorityFullCycle(webDriver);
    }

    private void add_filter_in_search(){
        $(addFilterSearchButton).shouldHave(Condition.visible).click();
    }

    private void check_search_result(String... etc) {
        for (int i = 0; i < etc.length; i++) {
            for (SelenideElement element : $$("#grid-table-government tbody tr")) {
                if (element.find("td").toString().contains(etc[i])) {
                    System.out.println(element.find("td"));
                }
            }
        }
    }
}
