package ru.spb.iac.pages.registerOfGovernmentService.card;

import ru.spb.iac.appHelper.Helper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static ru.spb.iac.pages.Locators.*;

import static ru.spb.iac.pages.registerOfGovernmentService.card.Locators.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AuthorityCard {

    public WebDriver webDriver;
    public Helper helper;

    public AuthorityCard(WebDriver webDriver){
        this.webDriver = webDriver;
        helper = new Helper(webDriver);
    }

    public AuthorityCard fill_out_the_form_to_send_in_iogv(String purposeOfDispatch){
        setDateInField();
        setTextInField(comment, purposeOfDispatch);
        clickButton(sendControl);
        return new AuthorityCard(webDriver);
    }

    public AuthorityCard fill_out_the_form_to_send_for_review(String purposeOfDispatch, boolean viewStatus){
        setDateInField();
        setTextInField(comment, purposeOfDispatch);
        clickButton(sendControl);
        if(viewStatus == false) {
            Assert.assertTrue(helper.isElementPresent(alertWindow));
            Assert.assertTrue(helper.isElementContains(alertWindow, alertTextMessegeInExecuteWindow));
        }
        return new AuthorityCard(webDriver);
    }

    public void setTextInField(String comment, String purposeOfDispatch) {
        SelenideElement element = $(comment);
        element.shouldHave(Condition.visible).clear();
        element.shouldHave(Condition.visible).setValue(purposeOfDispatch);
    }

    public void setDateInField() {
        SelenideElement element = $(byCssSelector(inputNewDate));
        if(element.has(Condition.empty)) {
            System.out.println(helper.getTomorrowIsDate());
            if (!element.has(Condition.disabled)) {
                element.shouldHave(Condition.visible).clear();
                element.shouldHave(Condition.visible).setValue(helper.getTomorrowIsDate());
            }
        }
    }

    public AuthorityCard add_service(String rootGroupName, String groupName, String serviceName) {
        if (checkedContainsService(serviceName) == 0) {
            $(addNewServiceButton).shouldHave(Condition.visible).click();
            $(byXpath(serviceTypeService)).shouldHave(Condition.visible).click();
            $(setServiceName).shouldHave(Condition.visible).setValue(serviceName);
            $(saveServiceButton).shouldHave(Condition.visible).click();
            helper.sleep(1500);
/*            addServiseInPlan();
              Данным метод актуален только для учётки администратора!
              В остальных случаях он не вызывается.
*/
//            helper.addServiceNumberInJsonFile(rootGroupName, groupName, serviceName);
        }
        return new AuthorityCard(webDriver);
    }

    public void addServiseInPlan() {
        if($$(modalDialog).last().shouldHave(Condition.visible).has(Condition.exist)) {
            $(addInPlan).shouldHave(Condition.visible).click();
            $(byXpath(periodOfExicution)).shouldHave(Condition.visible).click();
            $(addNewSchedule).shouldHave(Condition.visible).click();
        }
    }

    public void clickAddServiseInPlanThroughActoions() {
        //add code for action
        $(addInPlan).shouldHave(Condition.visible).click();
        $(byXpath(periodOfExicution)).shouldHave(Condition.visible).click();
        $(addNewSchedule).shouldHave(Condition.visible).click();

    }

    private int checkedContainsService(String serviceName) {
        int n = 0;
        ElementsCollection elements = $$(getServicesList);
        for (SelenideElement selenideElement: elements) {
            if (selenideElement.toString().contains(serviceName)) {
                n++;
            }
        }
        return n;
    }

    public AuthorityCard open_servise_groups(String serviceName) {
        if($(buttonGoBackToServiceGroups).has(Condition.visible)){
            $(buttonGoBackToServiceGroups).shouldHave(Condition.visible).click();
        }
        ElementsCollection elements = $$(getServicesList);
        for (SelenideElement elems : elements) {
            if(elems.toString().contains(serviceName)){
                elems.shouldHave(Condition.visible).click();
            }
        }
        return new AuthorityCard(webDriver);
    }

    public AuthorityCard addFunction(String rootGroupName, String groupName, String serviceName) {
        if (checkedContainsService(staticFunctionName) == 0) {
            $(addNewServiceButton).shouldHave(Condition.visible).click();
            $(byXpath(serviceTypeFunction)).shouldHave(Condition.visible).click();
            $(setServiceName).shouldHave(Condition.visible).setValue(staticFunctionName);
            $(saveServiceButton).shouldHave(Condition.visible).click();
            /*            addServiseInPlan();
              Данным метод актуален только для учётки администратора!
              В остальных случаях он не вызывается.
            */
            helper.addServiceNumberInJsonFile(rootGroupName, groupName, serviceName);
        }
        return new AuthorityCard(webDriver);
    }

    public AuthorityCard addMonitoringAndSupervisionFunction(String rootGroupName, String groupName, String serviceName) {
        if (checkedContainsService(staticFunctionMonitorAndControl) == 0) {
            $(addNewServiceButton).shouldHave(Condition.visible).click();
            $(byXpath(serviceTypeFunctionMonitorAndControl)).shouldHave(Condition.visible).click();
            $(setServiceName).shouldHave(Condition.visible).setValue(staticFunctionMonitorAndControl);
            $(saveServiceButton).shouldHave(Condition.visible).click();
            /*            addServiseInPlan();
              Данным метод актуален только для учётки администратора!
              В остальных случаях он не вызывается.
            */
            helper.addServiceNumberInJsonFile(rootGroupName, groupName, serviceName);
        }
        return new AuthorityCard(webDriver);
    }

    public void openAction(){
        $(action).shouldHave(Condition.visible).click();
    }

    public AuthorityCard addInSchedulePlan(){
        openAction();
        ElementsCollection elementsCollection = $$(".selected_cursor");
        for (SelenideElement element : elementsCollection) {
            if(element.toString().contains("Добавить в План-график"));
        }
        return new AuthorityCard(webDriver);
    }

    public AuthorityCard openGroup(String grouName) {
        ElementsCollection elements = $$(listOfVisibleServiceGroups);
        helper.waitForElementPresent(listOfVisibleServiceGroups);
        for (SelenideElement elems : elements) {
            if(elems.toString().contains(grouName)){
                elems.shouldHave(Condition.visible).click();
            }
        }
        return new AuthorityCard(webDriver);
    }

    public AuthorityCard open_groups() {
        helper.waitForElementPresent(listOfVisibleServiceGroups);
        ElementsCollection elements = $$(listOfVisibleServiceGroups);
        if(elements.first().toString().contains(information)) {
            elements.first().shouldHave(Condition.visible).click();
        }else{
            for (SelenideElement element : elements) {
                if(element.toString().contains(information)){
                    element.shouldHave(Condition.visible).click();
                }
                return new AuthorityCard(webDriver);
            }
        }
        return new AuthorityCard(webDriver);
    }

    public void goBackToServiceList(String serviceAllBack) {
        $(byXpath(serviceAllBack)).click();
        Assert.assertTrue($(getServicesList).has(Condition.visible));
    }

    private AuthorityCard fill_in_the_information_in_the_group() {
        for (SelenideElement element : $$(editVisibleButton)) {
            editFieldClick(element);
            fill_value_in_field($(formControl));
            clickButton(btnSave);
        }
        return new AuthorityCard(webDriver);
    }

    public AuthorityCard fill_out_all_the_information_in_the_groups(){
        helper.waitForElementPresent(currentVisibleGroup);
        for(int i = 0; i < $$(boxServiceGroupListValues).size(); i++){
            selectGroup(i);
            fill_in_the_information_in_the_group();
        }
        return new AuthorityCard(webDriver);
    }

    private void fill_value_in_field(SelenideElement selenideElement) {
        if(selenideElement.has(Condition.exist)) {
            if (selenideElement.getTagName().equals("input")) {
                selenideElement.setValue(helper.getTomorrowIsDate());
                selenideElement.pressEnter();
            }
            else if (selenideElement.getTagName().equals("textarea")) {
                if (selenideElement.getValue().equals(staticServiceName)) {
                    selenideElement.setValue(staticServiceName);
                } else {
                    selenideElement.setValue(textForInputTestData);
                }
            }
            else if (selenideElement.getTagName().equals("select")) {
                selenideElement.selectOption(0);
            }
        }else {
            if($(fileEditor).has(Condition.exist)) {
                $(fileEditor).sendKeys("C:\\Users\\pupenkov\\Desktop\\ManyFiles\\TestProxy\\ManyFiles\\Test.docx");
            }else{
                $(linkEditor).setValue("link");
            }
        }
    }

    public AuthorityCard send_to_approver(String comment) {
        click_buton_send_to_approver(sendToAgreementButton)
        .fill_in_comment(comment);
        clickButton(sendControl);
        return new AuthorityCard(webDriver);
    }

    public AuthorityCard clickButton(String str) {
        $(str).shouldHave(Condition.visible).click();
        return new AuthorityCard(webDriver);
    }

    private AuthorityCard fill_in_comment(String setComment) {
        $(comment).shouldHave(Condition.visible).setValue(setComment);
        return new AuthorityCard(webDriver);
    }

    private AuthorityCard click_buton_send_to_approver(String sendToAgreementButton){
        $(sendToAgreementButton).shouldHave(Condition.visible).click();
        return new AuthorityCard(webDriver);
    }

    private void editFieldClick(SelenideElement element){
        element.shouldHave(Condition.visible).click();
        Assert.assertTrue($(byXpath(btnObjectGroupSave)).has(Condition.visible));
    }

    private void to_approve(int indexToNotAgreed) {
        $$(buttonSuccessNotActive).get(indexToNotAgreed).shouldHave(Condition.visible).click();
    }
    private void to_not_approve(int indexToNotAgreed) {
        $$(buttonNotSuccessNotActive).get(indexToNotAgreed).shouldHave(Condition.visible).click();
    }

    public AuthorityCard to_approve_group(int indexToNotAgreed) {
        ElementsCollection fields = $$(currentVisibleGroup);
        for(int i = 0; i < fields.size(); i++){
            if($$(buttonSuccessChecked).get(i).toString().contains("value=\"3\"")) {
                if (i == indexToNotAgreed) { //дописать проверку для уже согласованных полей
                    to_not_approve(indexToNotAgreed);
                } else {
                    to_approve(i);
                }
            }else{
                continue;
            }
        }
        return new AuthorityCard(webDriver);

    }

    public AuthorityCard set_field_status_in_all_groups(int indexToNotAgreed){
        helper.waitForElementPresent(currentVisibleGroup);
        for(int i = 0; i < $$(boxServiceGroupListValues).size(); i++){
            selectGroup(i);
            if($(currentVisibleGroup).has(Condition.visible)) {
                to_approve_group(indexToNotAgreed);
            }else{
                continue;
            }
        }
        return new AuthorityCard(webDriver);
    }

    private void selectGroup(int i) {
        $(boxServiceGroup).selectOption(i);
    }
}
