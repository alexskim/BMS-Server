package cc.asjks.bms.server.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapJsonInterconversion {
	//Json 转换 Map
	public static Map<String, Object> Json2Map(String j) {
		Map<String, Object> map=new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try{
			map = mapper.readValue(j, new TypeReference<HashMap<String,Object>>(){});
			return map;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//Map 转换 Json
	public static String Map2Json(Map<String, Object> m){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            m.put("name", "zitong");
            m.put("age", "26");
            json = mapper.writeValueAsString(m);
            return json;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
	
	/*public static void main(String[] args) {
		String json="{\"password\":\"123\",\"uid\":\"456\"}";
		Map map=Json2Map(json);
		System.out.println(map.get("password"));
	}*/
}
