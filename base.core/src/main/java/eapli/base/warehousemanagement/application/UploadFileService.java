/*
package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.util.List;


public class UploadFileService {

    private Warehouse warehouse;
    private AGVDock agvDock;
    private Aisle aisle;
    private List<AGVDock> agvDockList;
    private List<Aisle> aisleList;
    private List<Row> rowList;

    public void uploadFile(String file_name) {

        //importa a o fich para um JSONObject
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file_name));   //para ler o ficheiro
            JSONObject jsonObject = (JSONObject) obj;


            //Obtém os objetos
            Integer length = Integer.parseInt(String.valueOf(jsonObject.get("Length")));
            Integer width = Integer.parseInt(String.valueOf(jsonObject.get("Width")));
            Integer square = Integer.parseInt(String.valueOf(jsonObject.get("Square")));
            String unit = (String) jsonObject.get("Unit");

            Plan warehousePlan = new Plan(length, width, square, unit);

            JSONArray aisles = (JSONArray) jsonObject.get("Aisles");

            for (int i = 0; i < aisles.size(); i++) {
                JSONObject f = (JSONObject) aisles.get(i);

                //id
                Integer id = Integer.parseInt(String.valueOf(f.get("Id")));
                //begin
                JSONObject jsonObjectB = (JSONObject) f.get("begin");
                Integer lsquareB = Integer.parseInt(String.valueOf(jsonObjectB.get("lsquare")));
                Integer wsquareB = Integer.parseInt(String.valueOf(jsonObjectB.get("wsquare")));
                //end
                JSONObject jsonObjectE = (JSONObject) f.get("end");
                Integer lsquareE = Integer.parseInt(String.valueOf(jsonObjectE.get("lsquare")));
                Integer wsquareE = Integer.parseInt(String.valueOf(jsonObjectE.get("wsquare")));
                //depth
                JSONObject jsonObjectD = (JSONObject) f.get("depth");
                Integer lsquareD = Integer.parseInt(String.valueOf(jsonObjectD.get("lsquare")));
                Integer wsquareD = Integer.parseInt(String.valueOf(jsonObjectD.get("wsquare")));
                //accessibility
                String access = (String) f.get("accessibility");

                JSONArray rows = (JSONArray) f.get("rows");
                for (int j = 0; j < rows.size(); j++) {
                    JSONObject h = (JSONObject) rows.get(j);

                    //id
                    Integer rowId = Integer.parseInt(String.valueOf(h.get("Id")));
                    //begin
                    JSONObject jsonObjectBRow = (JSONObject) h.get("begin");
                    Integer lsquareBRow = Integer.parseInt(String.valueOf(jsonObjectBRow.get("lsquare")));
                    Integer wsquareBRow = Integer.parseInt(String.valueOf(jsonObjectBRow.get("wsquare")));
                    //end
                    JSONObject jsonObjectERow = (JSONObject) h.get("end");
                    Integer lsquareERow = Integer.parseInt(String.valueOf(jsonObjectERow.get("lsquare")));
                    Integer wsquareERow = Integer.parseInt(String.valueOf(jsonObjectERow.get("wsquare")));
                    //shelves
                    Integer shelves = Integer.parseInt(String.valueOf(h.get("shelves")));

                    Row row = new Row(rowId, lsquareBRow, wsquareBRow, lsquareERow, wsquareERow);
                }

                aisle = new Aisle(id, lsquareB, wsquareB, lsquareE, wsquareE, lsquareD, wsquareD, access);
            }


            JSONArray AGVDock = (JSONArray) jsonObject.get("AGVDocks");

            for (int p = 0; p < AGVDock.size(); p++) {
                JSONObject t = (JSONObject) AGVDock.get(p);

                //id
                String AGVDock_id = (String) t.get("Id");
                //begin
                JSONObject jsonObjectBAGVDock = (JSONObject) t.get("begin");
                Integer lsquareBAGVDock = Integer.parseInt(String.valueOf(jsonObjectBAGVDock.get("lsquare")));
                Integer wsquareBAGVDock = Integer.parseInt(String.valueOf(jsonObjectBAGVDock.get("wsquare")));
                //end
                JSONObject jsonObjectEAGVDock = (JSONObject) t.get("end");
                Integer lsquareEAGVDock = Integer.parseInt(String.valueOf(jsonObjectEAGVDock.get("lsquare")));
                Integer wsquareEAGVDock = Integer.parseInt(String.valueOf(jsonObjectEAGVDock.get("wsquare")));
                //depth
                JSONObject jsonObjectDAGVDock = (JSONObject) t.get("depth");
                Integer lsquareDAGVDock = Integer.parseInt(String.valueOf(jsonObjectDAGVDock.get("lsquare")));
                Integer wsquareDAGVDock = Integer.parseInt(String.valueOf(jsonObjectDAGVDock.get("wsquare")));
                //accessibility
                String accessAGVDock = (String) t.get("accessibility");

                agvDock = new AGVDock(AGVDock_id, lsquareBAGVDock, wsquareBAGVDock, lsquareEAGVDock,
                        wsquareEAGVDock, lsquareDAGVDock, wsquareDAGVDock, accessAGVDock);

            }
            this.warehouse = new Warehouse(warehousePlan, aisle, agvDock);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
