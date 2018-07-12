package ru.spb.iac.statistics;

import org.junit.jupiter.api.Test;
import ru.spb.iac.appHelper.Helper;
import ru.spb.iac.appHelper.CryptoPro;
import ru.spb.iac.pages.Locators;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static ru.spb.iac.pages.Locators.cryptoProKey;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Statistics {

    WebDriver webDriver;
    private String statisticsMO = "/statistics/omsu";
    private String fourthQuarty = "IV квартал";
    private String table = ".//*[@class='DTFC_LeftWrapper']//a";

    public Statistics(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void fillStatistics() {
        Statistics statistics = new Statistics(webDriver);
        Helper helper = new Helper(webDriver);
        statistics.authorisation()
                .checkStatistic(Locators.statisticsLocal + statisticsMO);

        List<String> list = Arrays.asList("");
        Iterator<String> iter = list.iterator();
        for(;iter.hasNext();){
            String elem = iter.next();
            System.out.println(elem);
            $(byXpath(".//*[@id='iogvSelect_chosen']//b")).click();
            $(byXpath(".//*[@id='iogvSelect_chosen']/div/div/input")).sendKeys(elem);
            $(byXpath(".//*[@id='iogvSelect_chosen']/div/div/input")).pressEnter();

            $(byXpath(".//*[@id='applyButton']")).click();
            statistics.selectQuarter(fourthQuarty)
                    .showQuantityEntry("Все")
                    .enteringStatistics(table);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public Statistics authorisation() {
        $(byXpath("//*[@id=\"ramka-approver\"]/div/form/table/tbody/tr[1]/td[2]/input")).clear();
        $(byXpath("//*[@id=\"ramka-approver\"]/div/form/table/tbody/tr[1]/td[2]/input")).sendKeys("stp67");
        $(byXpath("//*[@id=\"ramka-approver\"]/div/form/table/tbody/tr[2]/td[2]/input")).clear();
        $(byXpath("//*[@id=\"ramka-approver\"]/div/form/table/tbody/tr[2]/td[2]/input")).sendKeys("stpstp34");
        $(byXpath("//*[@id=\"ramka-approver\"]/div/form/table/tbody/tr[4]/td[2]/input")).click();
        return new Statistics(webDriver);
    }

    public Statistics selectQuarter(String quarter) {
        $(byXpath(".//*[@id='quarterSelect']")).selectOption(quarter);
        $(byXpath(".//*[@id='iogvSelect_chosen']/a")).click();
        $(byXpath(".//*[@id='iogvSelect_chosen']/div/ul/li[1]")).click();
        $(byXpath(".//*[@id='applyButton']")).click();
        return new Statistics(webDriver);
    }

    public Statistics checkStatistic(String s) {
        open(s);
        return new Statistics(webDriver);
    }

    public Statistics showQuantityEntry(String quantity){
        $(byXpath(".//*[@id='table-statselect_length']/label/select")).selectOption(quantity);
        return new Statistics(webDriver);
    }

    public void enteringStatistics(String table) {
        Helper helper = new Helper(webDriver);
        CryptoPro cryptoPro = new CryptoPro();

        ElementsCollection elementsCollection = $$(byXpath(table));
        for (SelenideElement selenideElement: elementsCollection) {
            helper.scrolling(selenideElement);
            selenideElement.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cryptoPro.cryptoProCSP("Подтверждение доступа");
            $(byXpath(cryptoProKey)).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cryptoPro.cryptoProCSP("КриптоПро CSP");
            fillInTheFieldsStatistics();
        }
    }

    String randomStatistics(){
        int i = (int) (Math.random() * 99);
        return String.valueOf(i);
    }

    public ElementsCollection getListMO() {
        $(byXpath(".//*[@id='iogvSelect_chosen']//b")).click();
        ElementsCollection elementsCollection = $$(byXpath(".//*[@id='iogvSelect']"));
        return elementsCollection;
    }

    public Statistics fillInTheFieldsStatistics() {
        $(byId("inputText_147")).clear();
        $(byId("inputText_147")).sendKeys(randomStatistics());
        $(byId("inputText_148")).clear();
        $(byId("inputText_148")).sendKeys(randomStatistics());
        $(byId("inputText_149")).clear();
        $(byId("inputText_149")).sendKeys(randomStatistics());
        $(byId("inputText_150")).clear();
        $(byId("inputText_150")).sendKeys(randomStatistics());
        $(byId("inputText_151")).clear();
        $(byId("inputText_151")).sendKeys(randomStatistics());
        $(byId("inputText_152")).clear();
        $(byId("inputText_152")).sendKeys(randomStatistics());
        $(byId("inputText_153")).clear();
        $(byId("inputText_153")).sendKeys(randomStatistics());
        $(byId("inputText_212")).clear();
        $(byId("inputText_212")).sendKeys(randomStatistics());
        $(byId("inputText_154")).clear();
        $(byId("inputText_154")).sendKeys(randomStatistics());
        $(byId("inputText_197")).clear();
        $(byId("inputText_197")).sendKeys(randomStatistics());
        $(byId("button_iblock_forward")).click();
        $(byId("inputText_156")).clear();
        $(byId("inputText_156")).sendKeys(randomStatistics());
        $(byId("inputText_157")).clear();
        $(byId("inputText_157")).sendKeys(randomStatistics());
        $(byId("inputText_158")).clear();
        $(byId("inputText_158")).sendKeys(randomStatistics());
        $(byId("inputText_159")).clear();
        $(byId("inputText_159")).sendKeys(randomStatistics());
        $(byId("inputText_160")).clear();
        $(byId("inputText_160")).sendKeys(randomStatistics());
        $(byId("inputText_161")).clear();
        $(byId("inputText_161")).sendKeys(randomStatistics());
        $(byId("inputText_162")).clear();
        $(byId("inputText_162")).sendKeys(randomStatistics());
        $(byId("inputText_163")).clear();
        $(byId("inputText_163")).sendKeys(randomStatistics());
        $(byId("inputText_164")).clear();
        $(byId("inputText_164")).sendKeys(randomStatistics());
        $(byId("inputText_165")).clear();
        $(byId("inputText_165")).sendKeys(randomStatistics());
        $(byId("inputText_166")).clear();
        $(byId("inputText_166")).sendKeys(randomStatistics());
        $(byId("inputText_167")).clear();
        $(byId("inputText_167")).sendKeys(randomStatistics());
        $(byId("inputText_168")).clear();
        $(byId("inputText_168")).sendKeys(randomStatistics());
        $(byId("inputText_169")).clear();
        $(byId("inputText_169")).sendKeys(randomStatistics());
        $(byId("inputText_170")).clear();
        $(byId("inputText_170")).sendKeys(randomStatistics());
        $(byId("inputText_171")).clear();
        $(byId("inputText_171")).sendKeys(randomStatistics());
        $(byId("inputText_172")).clear();
        $(byId("inputText_172")).sendKeys(randomStatistics());
        $(byId("inputText_173")).clear();
        $(byId("inputText_173")).sendKeys(randomStatistics());
        $(byId("inputText_174")).clear();
        $(byId("inputText_174")).sendKeys(randomStatistics());
        $(byId("inputText_175")).clear();
        $(byId("inputText_175")).sendKeys(randomStatistics());
        $(byId("inputText_176")).clear();
        $(byId("inputText_176")).sendKeys(randomStatistics());
        $(byId("inputText_177")).clear();
        $(byId("inputText_177")).sendKeys(randomStatistics());
        $(byId("inputText_178")).clear();
        $(byId("inputText_178")).sendKeys(randomStatistics());
        $(byId("button_iblock_forward")).click();
        $(byId("inputText_179")).clear();
        $(byId("inputText_179")).sendKeys(randomStatistics());
        $(byId("inputText_193")).clear();
        $(byId("inputText_193")).sendKeys(randomStatistics());
        $(byId("inputText_180")).clear();
        $(byId("inputText_180")).sendKeys(randomStatistics());
        $(byId("inputText_194")).clear();
        $(byId("inputText_194")).sendKeys(randomStatistics());
        $(byId("inputText_181")).clear();
        $(byId("inputText_181")).sendKeys(randomStatistics());
        $(byId("inputText_195")).clear();
        $(byId("inputText_195")).sendKeys(randomStatistics());
        $(byId("button_iblock_forward")).click();
        $(byId("inputText_182")).clear();
        $(byId("inputText_182")).sendKeys(randomStatistics());
        $(byId("inputText_238")).clear();
        $(byId("inputText_238")).sendKeys(randomStatistics());
        $(byId("inputText_183")).clear();
        $(byId("inputText_183")).sendKeys(randomStatistics());
        $(byId("inputText_184")).clear();
        $(byId("inputText_184")).sendKeys(randomStatistics());
        $(byId("inputText_185")).clear();
        $(byId("inputText_185")).sendKeys(randomStatistics());
        $(byId("inputText_186")).clear();
        $(byId("inputText_186")).sendKeys(randomStatistics());
        $(byId("inputText_187")).clear();
        $(byId("inputText_187")).sendKeys(randomStatistics());
        $(byId("inputText_18")).clear();
        $(byId("inputText_18")).sendKeys(randomStatistics());
        $(byId("inputText_189")).clear();
        $(byId("inputText_189")).sendKeys(randomStatistics());
        $(byId("inputText_20")).clear();
        $(byId("inputText_20")).sendKeys(randomStatistics());
        $(byId("inputText_190")).clear();
        $(byId("inputText_190")).sendKeys(randomStatistics());
        $(byId("button_iblock_forward")).click();
        $(byId("inputText_36")).clear();
        $(byId("inputText_36")).sendKeys(randomStatistics());
        $(byId("inputText_199")).clear();
        $(byId("inputText_199")).sendKeys(randomStatistics());
        $(byId("inputText_21")).clear();
        $(byId("inputText_21")).sendKeys(randomStatistics());
        $(byId("inputText_192")).clear();
        $(byId("inputText_192")).sendKeys(randomStatistics());
        $(byId("inputText_53")).clear();
        $(byId("inputText_53")).sendKeys(randomStatistics());
        $(byId("inputText_69")).clear();
        $(byId("inputText_69")).sendKeys(randomStatistics());
        $(byXpath("//*[@id='button_iblock_submit']")).click();
        return new Statistics(webDriver);
    }
}
