import com.j.best.common.multi.thread.MultiThreadUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        Map map = new MultiThreadUtils().doAction(Arrays.asList("1", "2"), 4, new Supplier() {
            @Override
            public Object get() {
                return "行情数据返回";
            }
        });



        System.out.println(map.toString());
    }
}