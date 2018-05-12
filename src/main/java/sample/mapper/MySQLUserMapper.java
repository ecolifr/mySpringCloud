package sample.mapper;

import sample.entity.MySQLUser;

import java.util.List;

/**
 * Create By Xie ZuoZhi On 2018/5/11
 */
public interface MySQLUserMapper {
    List<MySQLUser> findAll();
}
