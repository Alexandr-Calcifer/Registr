package ru.spb.iac.pages;

public class Locators {

    public static String loginName = "#login_name";
    public static String loginPassword = "#login_password";
    public static String logInSend = ".send_lp";
    public static String navBar = ".dropdown-toggle";
//    public static String logOut_pre = "[href='https://rgu-pre.gu.spb.ru/logout']";
//    public static String logOut_prod = "[href='https://rgu.gu.spb.ru/logout']";
    static String logOut_Dev = "[href='https://rgu-dev.gu.spb.ru/logout']";
    static String navBarMenu = ".dropdown-menu";
    public static String newAuthority = ".btn-bottom a";
    public static String transitionToAuthority = ".nav.navbar-nav a";
    static String transitionToAuthorityThroughNotificationsButton = ".btn.btn-info";
    static String notificationNames = ".//td/a";

    // Блок создания нового полномочия

    public static String iogvItemNumber = ".col-md-12.input-sm";
    public static String newAuthorityName = ".main-container textarea";
    public static String authoritySelector = ".col-md-12.form-control.select2-helper.select2-hidden-accessible";
    public static String saveButton = "#btn_save";

    ////////////////////////////////////////////////////////

    public static String authorityName = "Autotest_Authority_full_cycle";
    public static String staticServiceName = "Service";
    public static String staticFunctionName = "Function";
    public static String staticFunctionMonitorAndControl = "Function monitor and control";
    public static final int oneDayInMillisecond = 86400000;
    public static String information = "сведений";
    public static String generalInformation = "Общие сведения";
    static String serviceDeliveryProcess = "Процесс предоставления услуги";
    public static String textForInputTestData = "This text includes a test data set...";
    public static String pressCommittee = "Комитет по печати и взаимодействию со средствами массовой информации";
    public static String iac = "СПб ГУП \"Санкт-Петербургский информационно-аналитический центр\"";
    public static String informationAndCommunicationCommittee = "Комитет по информатизации и связи";
    public static String archivalCommittee = "Архивный комитет Санкт-Петербурга";

    // По статистике

    public static String statistics_url = "http://statistic-dev.gu.spb.ru";
    public static String statisticsLocal = "http://192.168.44.228/";
    public static String cryptoProKey = ".//a[@value='7C0B142EEFCA8605D77616EADA017320480B1FF1']";
    static String statisticsAdministrations = "/statistics/ar";


    // ЭКДЛ

    public static String applicationConditionsCheckbox = ".form-horizontal:not(.myContainer)[id='1'] .form-horizontal:not(.myContainer)[id='1'] .checkbox.form-control:not(:disabled)";
    public static String stepOneFields = ".form-horizontal[style='display: block;'] .form-input.form-control:not(.ui-autocomplete-input):not(:disabled)";

}
