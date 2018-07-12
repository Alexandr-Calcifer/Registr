import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public final class Study {

    private static final String fileName = "config.json";
    Study(){}

    static String randomStatistics(){
        int i = (int) (Math.random() * 99);
        return String.valueOf(i);
    }

    static String fr(final String e){
        final String d;
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatForDateNow.format(dateNow.getTime() + 86400000);
        return date;
    }

    static final void gt(){
        int g = 3;
        for (int i = 0; i <= 3; i++) {
            if(i == g){
                System.out.println(i);
            }else{
                System.out.println(g);
                continue;
            }
        }
    }

    public static void writeValuesInJsonFile(String rootGroupName, String groupName, String serviceName, String serveceId) {
        File file = new File(fileName);
        JSONObject rootJsonObject = new JSONObject();
        JSONArray rootGroups = new JSONArray(); // первоначальный массив
        JSONArray groups = new JSONArray();
        JSONObject groupObject = new JSONObject();
        JSONObject serviceObject = new JSONObject();

        if (file.length() > 0) {
            try {  // написать добавление к группе, а не создание такой же группы
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

    public static String readJsoneFile(String groupName, String key) {
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
                            System.out.println(temporaryJsonObject);
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

    public static void main(String[] args) {
        writeValuesInJsonFile("RootGroup","Group", "service", "1");
        writeValuesInJsonFile("RootGroup","Group", "authority", "4");
        readJsoneFile("RootGroup", "Group");
    }
}
