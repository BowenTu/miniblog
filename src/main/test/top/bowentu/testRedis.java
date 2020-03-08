package top.bowentu;

import org.junit.Test;
import top.bowentu.common.utils.RedisPool;

public class testRedis {
    @Test
    public void testPing(){
        System.out.println(RedisPool.getResource().ping());
    }

    @Test
    public void testRedisPool(){
        RedisPool.set("test","success");
        System.out.println(RedisPool.get("test"));
    }
}
