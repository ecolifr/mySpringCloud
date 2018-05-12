package sample.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sample.entity.User;

import java.util.List;

/**
 * MongoDB查询接口，实现由spring-boot-starter-data-mongodb包自动实现
 * Create By Xie ZuoZhi On 2018/5/9
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * 根据lastName查询
     * @param lastName
     * @return
     */
    List<User> findByLastName(String lastName);

    /**
     * 根据firstName和lastName查询
     * @param firstName
     * @param lastName
     * @return
     */
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
