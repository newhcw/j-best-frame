import com.j.best.common.multi.thread.MultiThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class Main {
    public static void main(String[] args) {

        Map map = MultiThreadUtil
                .start(Arrays.asList("1", "2"), 4,
                        new Supplier() {
                            @Override
                            public Object get() {
                                throw new RuntimeException("行情调用失败");
                                // return "行情数据返回";
                            }
                        }, new Consumer<Exception>() {
                            @Override
                            public void accept(Exception ex) {
                               log.info(ex.toString());
                                System.out.println(ex);
                            }
                        });


        System.out.println(map.toString());
    }
}