import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.sun.xml.internal.ws.binding.WebServiceFeatureList.toList;

public class ParserJsonFiles {

    private String fileName = "config.json";

    public static void main(String[] args) {
        ParserJsonFiles ps = new ParserJsonFiles();
        ps.writeValuesInJsonFile("Services", "TestService", "5412");
        System.out.println(ps.readJsoneFile("Services", "TestService"));
    }

    public void writeValuesInJsonFile(String groupName, String serviceName, String serveceId) {
        File file = new File(fileName);
        JSONObject rootJsonObject = new JSONObject();
        JSONArray groups = new JSONArray();
        JSONObject serviceObject = new JSONObject();

        if (file.length() > 0) {
            try {
                rootJsonObject = (JSONObject) JSONValue.parse(new FileReader(file));
                groups = (JSONArray) rootJsonObject.get(groupName);
                serviceObject.put(serviceName, serveceId);
                groups.add(serviceObject);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            serviceObject.put(serviceName, serveceId);
            groups.add(serviceObject);
            rootJsonObject.put(groupName, groups);
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(rootJsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsoneFile(String services, String serviceName) {
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) {
            try {
                JSONObject jsonObject = (JSONObject) JSONValue.parse(new FileReader(file));
                JSONArray jsonArray;
                Iterator<JSONObject> iterator;
                JSONObject temporaryJsonObject;
                if(jsonObject.containsKey(services)){
                    jsonArray = (JSONArray) jsonObject.get(services);
                    iterator = jsonArray.iterator();
                    while (iterator.hasNext()) {
                        temporaryJsonObject = iterator.next();
                        if(temporaryJsonObject.containsKey(serviceName)){
                            return temporaryJsonObject.get(serviceName).toString();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Value: " + serviceName + " not found";
    }

//    public String readJsoneFile(String service, String serviceName) {
//        File file = new File(fileName);
//        if (file.exists() && !file.isDirectory()) {
//            try {
//                JSONObject jsonObject = (JSONObject) JSONValue.parse(new FileReader(file));
//                JSONArray jsonArray = (JSONArray) jsonObject.get(service);
//                System.out.println(jsonArray);
//                Iterator<JSONObject> iterator = jsonArray.iterator();
//                while (iterator.hasNext()) {
//                    if (iterator.next().toString().contains(serviceName)) {
//                        return serviceName;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return "Value: " + serviceName + " not found";
//    }
}
