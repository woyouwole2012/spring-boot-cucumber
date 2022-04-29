package com.woyou.springcucumber.utils;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class YamlReader {

    private String yamlPath = "src/test/resources/application.yaml";
    private String resultJson ;


    public YamlReader(){
        Yaml yaml = new Yaml();
        try {
            Object load = yaml.load(new FileInputStream(new File(yamlPath)));
            resultJson = new Gson().toJson(load);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getYamlValue(String key){
        if(resultJson != null ) {
            return JsonPath.read(resultJson, key);
        }

        return null;
    }
}
