package cc.asjks.bms.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
*/


public class EcDtUtils {
    public static List<String> splStr2List(String str, String spl) {
        String[] strs = null;
        List<String> strList = null;
        if (str != null) {
            strs = str.split(spl);
            if (strs != null) {
                strList = new ArrayList<String>();
                for (String st : strs) {
                    strList.add(st != null ? st.trim() : "");
                }
            }
        }
        return strList;
    }

    public static List<HashMap<String, Object>> getBarRndVal() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        List<String> names = splStr2List("周日, 周一, 周二, 周三, 周四, 周五, 周六", ",");
        for (String nam : names) {
            HashMap<String, Object> vals = new HashMap<String, Object>();
            vals.put("name", nam);
            vals.put("value", Math.round(Math.random() * 100));
            list.add(vals);
        }
        return list;
    }

    public static List<HashMap<String, Object>> getPieRndVal() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        List<String> names = splStr2List("A, B, C, D, E, F, G", ",");
        for (String nam : names) {
            HashMap<String, Object> vals = new HashMap<String, Object>();
            vals.put("name", nam);
            vals.put("value", Math.round(Math.random() * 100));
            list.add(vals);
        }
        return list;
    }

 
 

    
    
}
