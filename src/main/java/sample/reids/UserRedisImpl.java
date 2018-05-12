package sample.reids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * redis接口实现
 * Create By Xie ZuoZhi On 2018/5/9
 */
@Service
@Validated
public class UserRedisImpl implements UserRedis {
    private final StringRedisTemplate template;

    public UserRedisImpl( StringRedisTemplate template ) {
        this.template = template;
    }

    @Override
    public String findData( @NotNull final String key ) {
        return template.execute(( RedisConnection redisConnection ) -> {
            byte[] keyBytes = template.getStringSerializer().serialize(key);
            if (redisConnection.exists(keyBytes)) {
                byte[] value = redisConnection.get(keyBytes);
                String name = template.getStringSerializer().deserialize(value);
                return name;
            }
            return null;
        });
    }

    @Override
    public void addData( String key, String value ) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        if (!this.template.hasKey(key)) {
            ops.set(key, value);
        }
    }
}
