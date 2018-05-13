package sample.jsp;

import org.junit.Test;
import sample.reids.UserRedis;
import sample.reids.UserRedisImpl;

public class FinalTest {
    @Test
    public void test() {
        UserRedis userRedis = new UserRedisImpl(null);
        String a = "";
        try {
            userRedis.findData(a);
        } catch ( Exception e) {

        }
        a = "s";
    }
}
