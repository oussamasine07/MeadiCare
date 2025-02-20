package com.medicare.validateinput;


import java.util.Map;

public class Input {

    public Map<String, String> map;

    public Input (Map<String, String > map) {
        this.map = map;
    }


    public String getOld ( String fieldName ) {
        return map.get(fieldName);
    }
}
