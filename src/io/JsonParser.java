package io;

import gameObjects.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**Guarda los datos en el disco duro local del equipo
 * @author Raul Garcia & Alejandro Molero
 */
public class JsonParser {
    /**
     * Lee el archivo data.json
     * @return ArrayList de ScoreData con todos los datos almacenados
     * @throws FileNotFoundException
     */
    public static ArrayList<ScoreData> readFile() throws FileNotFoundException {
        ArrayList<ScoreData> dataList = new ArrayList<ScoreData>();

        File file = new File(Constants.SCORE_PATH);
        if(!file.exists() || file.length()==0){
            return dataList;
        }
        JSONTokener parser = new JSONTokener(new FileInputStream(file));
        JSONArray jsonList = new JSONArray(parser);

        for(int i = 0; i < jsonList.length(); i++){
            JSONObject object =(JSONObject)jsonList.get(i);
            ScoreData data = new ScoreData();
            data.setScore(object.getInt("score"));
            data.setDate(object.getString("date"));
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * Escribe un archivo
     * @param dataList Lista de arrays de ScoreData que se va escribir en el archivo
     * @throws IOException
     */
    public static void writeFile(ArrayList<ScoreData> dataList) throws IOException {
        File outputFile = new File(Constants.SCORE_PATH);
        outputFile.getParentFile().mkdirs();
        outputFile.createNewFile();

        JSONArray jsonList = new JSONArray();
        for(ScoreData data: dataList){
            JSONObject object = new JSONObject();
            object.put("score", data.getScore());
            object.put("date", data.getDate());

            jsonList.put(object);
        }
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile.toURI()));
        jsonList.write(writer);
        writer.close();
    }
}
