package ru.spb.iac.pages.registerOfGovernmentService.PropertySettings;

import ru.spb.iac.appHelper.Helper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static ru.spb.iac.pages.registerOfGovernmentService.PropertySettings.Locators.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreatorProperty extends PropertySettings{

    public WebDriver webDriver;
    Helper helper;

    public CreatorProperty(WebDriver webDriver){
        super(webDriver);
        this.webDriver = webDriver;
        helper = new Helper(webDriver);
    }

    public PropertySettings createPropertyGroupe(String name, boolean fullCycle, String tab, String... users){
        $(groupName).shouldHave(Condition.visible).sendKeys(name);
        $(groupParticipatesCycle).shouldHave(Condition.visible).click();
        if(fullCycle == false){
            $(participationOfTheGroupInTheFullCycle).shouldHave(Condition.visible).click();
        }
        Selenide.$(Locators.accessSetting).click();
        for (int i = 0; i < users.length; i++) {
            $(searchIOGV).shouldHave(Condition.visible).clear();
            $(searchIOGV).shouldHave(Condition.visible).sendKeys(users[i]);
            $(readAccess).shouldHave(Condition.visible).click();
        }
        $(save).shouldHave(Condition.visible).click();
        checkPropertyGroupSettings(tab, name);
        return new PropertySettings(webDriver);
    }

    public PropertySettings createProperty(String propertyName, String propertyShortName, String hint, String propertyType, int quantity_of_values_in_the_list, String groupName){
        String localName = propertyName + (int)(Math.random() * 999);
        $(name).shouldHave(Condition.visible).sendKeys(localName);
        $(shortName).shouldHave(Condition.visible).sendKeys(propertyShortName);
        $(titleHint).shouldHave(Condition.visible).sendKeys(hint);
        $(type).selectOption(propertyType);
        type_check(propertyType, true, quantity_of_values_in_the_list);
        $(propertyGroup).selectOption(groupName);
        helper.writeValuesInJsonFile("PropertySettings", groupName, "groupName", groupName);
        helper.writeValuesInJsonFile("PropertySettings", groupName,"propertyName", localName);
        helper.writeValuesInJsonFile("PropertySettings", groupName, "propertyShortName", propertyShortName);
        helper.writeValuesInJsonFile("PropertySettings", groupName, "propertyType", propertyType);
        helper.writeValuesInJsonFile("PropertySettings", groupName,"quantity_of_values_in_the_list", String.valueOf(quantity_of_values_in_the_list));
        if(propertyName.contains("Service")) {
            $(propertySortGroup).selectOption(Selenide.$$(propertySortGroupList).size() - 1);
        }
        $(save).shouldHave(Condition.visible).click();
        searching(localName);
        return new PropertySettings(webDriver);
    }

    private PropertySettings checkPropertyGroupSettings(String tab, String name) {
        $(tab).waitUntil(Condition.visible, 10000);
        click(tab);
        if(tab.contains("services")){
            $(serviceGroups).waitUntil(Condition.visible, 10000);
            Assert.assertTrue($$(serviceGroups).last().toString().contains(name));
        }
        else if(tab.contains("authority")) {
            $(authorityGroups).waitUntil(Condition.visible, 10000);
            Assert.assertTrue($$(authorityGroups).last().toString().contains(name));
        }
        else if(tab.contains("all")){
            $(allServices).waitUntil(Condition.visible, 10000);
            Assert.assertTrue($$(Locators.allServices).last().toString().contains(name));
        }
        return new PropertySettings(webDriver);
    }

    private PropertySettings checkPropertySettings(String name){
        Assert.assertTrue($(searchQueryInAllServices).toString().contains(name));
        return new PropertySettings(webDriver);
    }

    private void searching(String name){
        helper.scrolling($(head));
        click(filter);
        $(filterPropertyName).selectOption(name);
        click(applyFilter);
        checkPropertySettings(name);
    }

    private void type_check(String str, boolean whichMultiple, int quantity_of_values_in_the_list){
        switch (str){
            case "Строка": fill_in_the_line_type_field(whichMultiple);
                break;
            case "Число": fill_in_the_number_type_field(whichMultiple);
                break;
            case "Текст": fill_in_the_text_type_field(whichMultiple);
                break;
            case "Дата": fill_in_the_date_type_field(whichMultiple);
                break;
            case "Дата/Время":fill_in_the_dateTime_type_field(whichMultiple);
                break;
            case "Список": fill_in_the_list_type_field(whichMultiple, quantity_of_values_in_the_list);
                break;
            case "Классификатор": fill_in_the_classifier_type_field(whichMultiple);
                break;
            case "Привязка к элементам таблицы БД": fill_in_the_dbTable_type_field(whichMultiple);
                break;
            case "Ссылка": fill_in_the_link_type_field(whichMultiple);
                break;
            case "Файл": fill_in_the_file_type_field(whichMultiple);
                break;
            case "Чекбокс": fill_in_the_checkBox_type_field(whichMultiple);
                break;
        }
    }

    private void fill_in_the_line_type_field(boolean whichMultiple){
        if(whichMultiple) {
            $(Locators.multiple).shouldHave(Condition.visible).click();
        }
    }

    private void fill_in_the_number_type_field(boolean whichMultiple){
        $(showMeasuce).shouldHave(Condition.visible).click();
        if(whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
    }

    private void fill_in_the_text_type_field(boolean whichMultiple){
        $(showWysiwyg).shouldHave(Condition.visible).click();
        if(whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
    }

    private void fill_in_the_date_type_field(boolean whichMultiple){
        if(whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
    }

    private void fill_in_the_dateTime_type_field(boolean whichMultiple){
        if(whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
    }

    private void fill_in_the_list_type_field(boolean whichMultiple, int quantity_of_values_in_the_list){
        if(whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
        for (int i = 0; i <= quantity_of_values_in_the_list; i++) {
            $$(defaultValue).get(i).shouldHave(Condition.visible).click();
            $$(valueName).get(i).shouldHave(Condition.visible).sendKeys("Value" + (int)(Math.random() * 99999));
            $$(typeValuesCode).get(i).shouldHave(Condition.visible).sendKeys(String.valueOf((int)(Math.random() * 999)));
            if(i < quantity_of_values_in_the_list) {
                $(Locators.addPropertyValuesForm).click();
            }
        }
        if(quantity_of_values_in_the_list > 0){
            $$(deletePropertyValuesForm).get($$(valueList).size() - 1).shouldHave(Condition.visible).click();
        }
        $$(defaultValue).get(0).shouldHave(Condition.visible).click();
        $(refillList).shouldHave(Condition.visible).click();
    }

    private void fill_in_the_classifier_type_field(boolean whichMultiple){
        if(whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
        $(refillList).shouldHave(Condition.visible).click();
        $(classifierTree).shouldHave(Condition.visible).click();
        $(classifier).shouldHave(Condition.visible).selectOption(0);
    }

    private void fill_in_the_dbTable_type_field(boolean whichMultiple){
        if(whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
        $(elementsTable).selectOption(1);
    }

    private void fill_in_the_link_type_field(boolean whichMultiple) {
        if (whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
    }

    private void fill_in_the_file_type_field(boolean whichMultiple) {
        if (whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
    }

    private void fill_in_the_checkBox_type_field(boolean whichMultiple) {
        if (whichMultiple) {
            $(multiple).shouldHave(Condition.visible).click();
        }
    }

}
