package ru.spb.iac.appHelper;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spb.iac.pages.Locators;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Helper {

    WebDriver webDriver;
    JsonObject rootJsonObject;
    JsonObject childJsonObject;
    private String fileName = "config.json";
    private static String getServicesList = ".collapsed.no_link";
    private static String patternNumberServise = "\\d{4}";

    public Helper(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void scrolling(SelenideElement selenideElement) {
        sleep(500);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView(true);"
                ,selenideElement);
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(String elementPresent) {
        try {
            $(elementPresent);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementContains(String expected, String actual) {
        try {
            $(expected).toString().contains(actual);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForElementPresent(String someLocator){
        WebDriverWait waitForOne = new WebDriverWait(webDriver, 10);
        waitForOne.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(someLocator)));
    }

    public void waitForElementPresentText(String someLocator, String text){
        WebDriverWait waitForOne = new WebDriverWait(webDriver, 10);
        waitForOne.until(ExpectedConditions.textToBePresentInElementLocated((By.cssSelector(someLocator)), text));
    }

    public void writeValuesInJsonFile(String rootGroupName, String groupName, String serviceName, String serveceId) {
        File file = new File(fileName);
        JSONObject rootJsonObject = new JSONObject();
        JSONArray rootGroups = new JSONArray(); // первоначальный массив
        JSONArray groups = new JSONArray();
        JSONObject groupObject = new JSONObject();
        JSONObject serviceObject = new JSONObject();

        if (file.length() > 0) {
            try {
                rootJsonObject = (JSONObject) JSONValue.parse(new FileReader(file));
                rootGroups = (JSONArray) rootJsonObject.get(rootGroupName);
                serviceObject.put(serviceName, serveceId);
                groups.add(serviceObject);
                groupObject.put(groupName, groups);
                rootGroups.add(groupObject);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            serviceObject.put(serviceName, serveceId);
            groups.add(serviceObject);
            groupObject.put(groupName, groups);
            rootGroups.add(groupObject);
            rootJsonObject.put(rootGroupName, rootGroups);
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(rootJsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsoneFile(String groupName, String key) {
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) {
            try {
                JSONObject jsonObject = (JSONObject) JSONValue.parse(new FileReader(file));
                JSONArray jsonArray;
                Iterator<JSONObject> iterator;
                JSONObject temporaryJsonObject;
                if(jsonObject.containsKey(groupName)){
                    jsonArray = (JSONArray) jsonObject.get(groupName);
                    iterator = jsonArray.iterator();
                    while (iterator.hasNext()) {
                        temporaryJsonObject = iterator.next();
                        if(temporaryJsonObject.containsKey(key)){
                            return temporaryJsonObject.get(key).toString();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Value: " + key + " not found";
    }

    public void addServiceNumberInJsonFile(String rootGroupName, String groupName, String serviceName) {
        ElementsCollection elements = $$(getServicesList);
        for (SelenideElement elems : elements) {
            if(elems.toString().contains(serviceName)){
                Pattern pattern = Pattern.compile(patternNumberServise);
                Matcher matcher = pattern.matcher(elems.toString());
                if(matcher.find()) {
                    System.out.println(matcher.group());
                    writeValuesInJsonFile(rootGroupName, groupName, serviceName, matcher.group());
                }
            }
        }
    }

    public String getTomorrowIsDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatForDateNow.format(dateNow.getTime() + Locators.oneDayInMillisecond);
        return date;
    }

    public void click(String by){
        $(by).click();
    }
}
