package eapli.base.warehousemanagement.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class UploadFile {

    public static void main(String[] args)  {

        //entrada: string json com dados do armazém
        String jsonStr =
        "{" +
                 "\"Warehouse\":\"Warehouse Plan\"" +
                 "\"Length\": 2800," +
                 "\"Width\": 2000," +
                 "\"Square\": 100," +
                 "\"Unit\":\"cm\"," +
                 "\"Aisles\":" +
                         "[" +
                         "{" +
                         "\"Id\":1," +
                         "\"begin\":{ \"lsquare\":5,\"wsquare\":1}," +
                         "\"end\":{ \"lsquare\":16,\"wsquare\":1}," +
                         "\"depth\":{ \"lsquare\":16,\"wsquare\":1}," +
                         "\"accessibility\":\"w+\"," +
                         "\"rows\":"+
                            "[" +
                            "{" +
                                "\"Id\":1," +
                                "\"begin\":{ \"lsquare\":5,\"wsquare\":1}," +
                                "\"end\":{ \"lsquare\":7,\"wsquare\":1}," +
                                "\"shelves\":3" +
                            "}," +
                            "{" +
                                "\"Id\":2," +
                                "\"begin\":{ \"lsquare\":8,\"wsquare\":1}," +
                                "\"end\":{\"lsquare\":12,\"wsquare\":1}," +
                                "\"shelves\":4" +
                            "}," +
                            "{" +
                                "\"Id\":3," +
                                "\"begin\":{ \"lsquare\":13,\"wsquare\":1}," +
                                "\"end\":{ \"lsquare\":16,\"wsquare\":1}," +
                                "\"shelves\":8" +
                            "}" +
                        "]" +
                 "}," +
                 "{" +
                        "\"Id\":2," +
                        "\"begin\":{ \"lsquare\":5,\"wsquare\":8}," +
                        "\"end\":{ \"lsquare\":16,\"wsquare\":8}," +
                        "\"depth\":{ \"lsquare\":16,\"wsquare\":9}," +
                        "\"accessibility\":\"w-\"," +
                        "\"rows\":"+
                            "[" +
                            "{" +
                                "\"Id\":1," +
                                "\"begin\":{ \"lsquare\":5,\"wsquare\":8}," +
                                "\"end\":{ \"lsquare\":9,\"wsquare\":8}," +
                                "\"shelves\":5" +
                                "}," +
                                "{" +
                                "\"Id\":2," +
                                "\"begin\":{ \"lsquare\":10,\"wsquare\":8}," +
                                "\"end\":{ \"lsquare\":11,\"wsquare\":8}," +
                                "\"shelves\":1" +
                                "}," +
                                "{" +
                                "\"Id\":3," +
                                "\"begin\":{ \"lsquare\":12,\"wsquare\":8}," +
                                "\"end\":{ \"lsquare\":13,\"wsquare\":8}," +
                                "\"shelves\":4" +
                                "}," +
                                "{" +
                                "\"Id\":4," +
                                "\"begin\":{ \"lsquare\":14,\"wsquare\":8}," +
                                "\"end\":{ \"lsquare\":16,\"wsquare\":8}," +
                                "\"shelves\":6" +
                                "}" +
                            "]" +
                 "}," +
                 "{" +
                        "\"Id\":3," +
                        "\"begin\":{ \"lsquare\":5,\"wsquare\":11}," +
                        "\"end\":{ \"lsquare\":16,\"wsquare\":11}," +
                        "\"depth\":{ \"lsquare\":16,\"wsquare\":10}," +
                        "\"accessibility\":\"w+\"," +
                        "\"rows\":"+
                            "[" +
                            "{" +
                                "\"Id\":1," +
                                "\"begin\":{ \"lsquare\":5,\"wsquare\":11}," +
                                "\"end\":{ \"lsquare\":11,\"wsquare\":11}," +
                                "\"shelves\":4" +
                                "}," +
                                "{" +
                                "\"Id\":2," +
                                "\"begin\":{ \"lsquare\":12,\"wsquare\":11}," +
                                "\"end\":{ \"lsquare\":16,\"wsquare\":11}," +
                                "\"shelves\":10" +
                                "}," +
                            "]" +
                 "}," +
                 "{" +
                        "\"Id\":4," +
                        "\"begin\":{ \"lsquare\":5,\"wsquare\":18}," +
                        "\"end\":{ \"lsquare\":16,\"wsquare\":18}," +
                        "\"depth\":{ \"lsquare\":16,\"wsquare\":18}," +
                        "\"accessibility\":\"w-\"," +
                        "\"rows\":"+
                            "[" +
                            "{" +
                                "\"Id\":1," +
                                "\"begin\":{ \"lsquare\":5,\"wsquare\":18}," +
                                "\"end\":{ \"lsquare\":8,\"wsquare\":18}," +
                                "\"shelves\":4" +
                                "}," +
                                "{" +
                                "\"Id\":2," +
                                "\"begin\":{ \"lsquare\":9, \"wsquare\":18}," +
                                "\"end\":{ \"lsquare\":12,\"wsquare\":18}," +
                                "\"shelves\":4" +
                                "}," +
                                "{" +
                                "\"Id\":3," +
                                "\"begin\":{ \"lsquare\":13,\"wsquare\":18}," +
                                "\"end\":{ \"lsquare\":16,\"wsquare\":18}," +
                                "\"shelves\":8" +
                                "}," +
                            "]" +
                 "}" +
        "]," +
        "\"AGVDocks\":[" +
                "{" +
                        "\"Id\":\"D1\"" +
                        "\"begin\":" + "{" + "\"lsquare\":1," + "\"wsquare\":3" +  "}," +
                        "\"end\":" + "{" + "\"lsquare\":1," + "\"wsquare\":3" + "}," +
                        "\"depth\":" + "{" + "\"lsquare\":1," + "\"wsquare\":3" + "}," +
                        "\"accessibility\":\"l+\"," +
                "}," +
                "{" +
                        "\"Id\":\"D2\"" +
                        "\"begin\":" + "{" + "\"lsquare\":1," + "\"wsquare\":5" +  "}," +
                        "\"end\":" + "{" + "\"lsquare\":1," + "\"wsquare\":5" + "}," +
                        "\"depth\":" + "{" + "\"lsquare\":1," + "\"wsquare\":5" + "}," +
                        "\"accessibility\":\"l+\"," +
                "}," +
                "{" +
                        "\"Id\":\"D3\"" +
                        "\"begin\":" + "{" + "\"lsquare\":1," + "\"wsquare\":13" +  "}," +
                        "\"end\":" + "{" + "\"lsquare\":1," + "\"wsquare\":13" + "}," +
                        "\"depth\":" + "{" + "\"lsquare\":1," + "\"wsquare\":13" + "}," +
                        "\"accessibility\":\"l+\"," +
                "}," +
                "{" +
                        "\"Id\":\"D4\"" +
                        "\"begin\":" + "{" + "\"lsquare\":1," + "\"wsquare\":15" +  "}," +
                        "\"end\":" + "{" + "\"lsquare\":1," + "\"wsquare\":15" + "}," +
                        "\"depth\":" + "{" + "\"lsquare\":1," + "\"wsquare\":15" + "}," +
                        "\"accessibility\":\"l+\"," +
                "}," +
                "{" +
                        "\"Id\":\"D5\"" +
                        "\"begin\":" + "{" + "\"lsquare\":20," + "\"wsquare\":4" +  "}," +
                        "\"end\":" + "{" + "\"lsquare\":20," + "\"wsquare\":4" + "}," +
                        "\"depth\":" + "{" + "\"lsquare\":20," + "\"wsquare\":4" + "}," +
                        "\"accessibility\":\"l-\"," +
                "}," +
                "{" +
                        "\"Id\":\"D6\"" +
                        "\"begin\":" + "{" + "\"lsquare\":20," + "\"wsquare\":14" +  "}," +
                        "\"end\":" + "{" + "\"lsquare\":20," + "\"wsquare\":14" + "}," +
                        "\"depth\":" + "{" + "\"lsquare\":20," + "\"wsquare\":14" + "}," +
                        "\"accessibility\":\"l-\"," +
                "}," +
        "]" +
    "}";

        //importa a string para um JSONObject
        JSONObject string = (JSONObject) new JSONObject().get(jsonStr);

        //Obtém os objetos
        JSONObject length = (JSONObject) string.get("Length");
        JSONObject width = (JSONObject) string.get("Width");
        JSONObject square = (JSONObject) string.get("Square");
        JSONObject unit = (JSONObject) string.get("Unit");
        JSONArray aisles = (JSONArray) string.get("Aisles");
        JSONArray rows = (JSONArray) string.get("Rows");
        JSONArray AGVDock = (JSONArray) string.get("AGVDocks");

        System.out.println("Length: " + length);
        System.out.println("Width: " + width);
        System.out.println("Square: " + square);
        System.out.println("Unit: " + unit);

       for(int i = 0; i < aisles.toArray().length; i++){
           JSONObject f = (JSONObject) aisles.get(i);

           System.out.println("Id: " + f.get("Id"));
           System.out.println("Begin: " + f.get("begin"));
           System.out.println("End" + f.get("end"));
           System.out.println("Depth" + f.get("depth"));
           System.out.println("Accessibility" + f.get("accessibility"));
           System.out.println("Rows" + f.get("rows"));

           for(int j = 0; j < rows.toArray().length; i++){
               JSONObject h = (JSONObject) rows.get(j);
               System.out.println("Id: " + h.get("Id"));
               System.out.println("Begin: " + h.get("begin"));
               System.out.println("End" + h.get("end"));
               System.out.println("Shelves" + h.get("shelves"));
           }
       }

        for(int p = 0; p < AGVDock.toArray().length; p++){
            JSONObject t = (JSONObject) AGVDock.get(p);
            System.out.println("Id: " + t.get("Id"));
            System.out.println("Begin: " + t.get("begin"));
            System.out.println("End" + t.get("end"));
            System.out.println("Depth" + t.get("depth"));
            System.out.println("Accessibility" + t.get("accessibility"));
        }
        System.out.println();
    }
}
