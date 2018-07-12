package ru.spb.iac.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.spb.iac.appHelper.Helper;
import ru.spb.iac.pages.authorization.Authorization;
import ru.spb.iac.pages.registerOfGovernmentService.table.Table;
import ru.spb.iac.pages.registerOfGovernmentService.table.government.CreatorNewAuthorityFullCycle;
import ru.spb.iac.pages.home.HomePage;
import ru.spb.iac.pages.registerOfGovernmentService.card.AuthorityCard;
import ru.spb.iac.pages.registerOfGovernmentService.card.CardCoordinator;
import ru.spb.iac.pages.registerOfGovernmentService.card.CardExecutor;
import ru.spb.iac.pages.registerOfGovernmentService.PropertySettings.CreatorProperty;
import ru.spb.iac.pages.registerOfGovernmentService.PropertySettings.PropertySettings;
import ru.spb.iac.pages.registerOfGovernmentService.table.government.GovernmentDataTable;
import ru.spb.iac.pages.registerOfGovernmentService.table.municipal.MunicipalDataTable;
import ru.spb.iac.pages.registerOfGovernmentService.table.municipal.CreatorNewMunicipalAuthority;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestBase.class)
@TestPropertySource(locations = "classpath:app.properties")
//@TestPropertySource(locations = "classpath:app_prod.properties")
@EnableAsync
@ComponentScan("ru.spb.iac")
public class TestBase{

    @Value("${login}")
    protected String approver;
    @Value("${password}")
    protected String approverPassword;

    @Value("${iogv249}")
    protected String iogv;
    @Value("${passiogv249}")
    protected String passwordIogv;

    @Value("${logOut}")
    protected String logOut;

    @Value("${url_registr}")
    protected String url_registr;

    @Value("${profile}")
    protected String profile;

    @Value("${driverPath}")
    public String driverPath;

    @Value("${proxy.ip}")
    protected String proxyHost;

    @Value("${proxy.userName}")
    protected String proxyUserName;

    @Value("${proxy.password}")
    public String proxyPassword;

    protected WebDriver webDriver;
    public Authorization authorization;
    public HomePage homePage;
    public Helper helper;
    public AuthorityCard authorityCard;
    public CardCoordinator cardCoordinator;
    public Table table;
    public CardExecutor cardExecutor;
    public PropertySettings propertySettings;
    public CreatorProperty creatorProperty;
    public GovernmentDataTable governmentDataTable;
    public MunicipalDataTable municipalDataTable;
    public CreatorNewAuthorityFullCycle creatorNewAuthority;
    public CreatorNewMunicipalAuthority creatorNewMunicipalAuthority;

    public void initChromeDriver() {

        if(webDriver != null){
            return;
        }
        getChromeDriver();
        authorization = new Authorization(webDriver);
        homePage = new HomePage(webDriver);
        helper = new Helper(webDriver);
        table = new Table(webDriver);
        authorityCard = new AuthorityCard(webDriver);
        cardCoordinator = new CardCoordinator(webDriver);
        cardExecutor = new CardExecutor(webDriver);
        propertySettings = new PropertySettings(webDriver);
        creatorProperty = new CreatorProperty(webDriver);
        creatorNewAuthority = new CreatorNewAuthorityFullCycle(webDriver);
        creatorNewMunicipalAuthority = new CreatorNewMunicipalAuthority(webDriver);
        governmentDataTable = new GovernmentDataTable(webDriver);
    }

    private void getChromeDriver() {
        ChromeDriverManager.getInstance().proxy(proxyHost).proxyUser(proxyUserName).proxyPass(proxyPassword).setup();
        webDriver = new ChromeDriver();
        WebDriverRunner.setWebDriver(webDriver);
    }

    /* Подключения профиля браузера при необходимости
    public DesiredCapabilities chrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir="+ profile);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        return capabilities;
    }
    */

}
