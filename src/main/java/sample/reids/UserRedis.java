package sample.reids;

import javax.validation.constraints.NotNull;

/**
 * redis接口
 * Create By Xie ZuoZhi On 2018/5/11
 */
public interface UserRedis {

    String findData(@NotNull String key );

    void addData( String key, String value );
}
