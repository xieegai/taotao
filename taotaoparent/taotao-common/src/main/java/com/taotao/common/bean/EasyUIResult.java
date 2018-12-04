package com.taotao.common.bean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;

/**
 * @author zjj
 * @date 18-11-1 0:15
 */
public class EasyUIResult {

    private static final ObjectMapper MAPPER = new ObjectMapper();


    private Long tatal;

    private List<?> rows;

    public EasyUIResult() {
    }

    public EasyUIResult(Long tatal, List<?> rows) {
        this.tatal = tatal;
        this.rows = rows;
    }

    public Long getTatal() {
        return tatal;
    }

    public void setTatal(Long tatal) {
        this.tatal = tatal;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public static EasyUIResult formetToList(String jsonData, Class<?> clazz){

        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("rows");

            List<?> list = null;

            if (data.isArray() && data.size() > 0){
                JsonParser jsonParser = data.traverse();
                CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
                list = MAPPER.readValue(jsonParser, collectionType);
            }

            return new EasyUIResult(jsonNode.get("tatal").longValue(), list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
