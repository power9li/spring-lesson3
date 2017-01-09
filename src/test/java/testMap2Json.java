import com.power.spring.lesson3.utils.JSONUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenli on 2017/1/9.
 */
public class testMap2Json {

    @Test
    public void test(){
        Map<String, Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2", true);
        String json = JSONUtils.toJSON(map);
        System.out.println("json = " + json);
    }
}
