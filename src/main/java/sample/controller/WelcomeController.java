/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sample.entity.Orange;
import sample.mapper.MySQLUserMapper;
import sample.entity.Customer;
import sample.entity.User;
import sample.mongo.UserRepository;
import sample.reids.UserRedis;

import javax.validation.constraints.NotNull;
import java.util.*;

@Controller
@Validated
public class WelcomeController {

    @Value("${application.message: Hello World}")
    private String message;
    @Autowired
    private Customer customer;
    @Autowired
    private UserRedis userRedis;
    @Autowired
    private MySQLUserMapper mySQLUserMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Orange orange;

    @GetMapping("/")
    public String welcome( ModelMap model ) {
        model.put("time", new Date());
        model.put("message", this.customer);
        return "welcome";
    }
    @GetMapping("/findMySQL")
    public String mySQLUserMapper( ModelMap model ) {
        model.put("time", new Date());
        model.put("message", mySQLUserMapper.findAll());
        return "welcome";
    }
    @PostMapping("/saveUser")
    public String saveUser( @NotNull(message = "lastName不能为空") @RequestParam String lastName, @NotNull(message = "firstName不能为空") @RequestParam(defaultValue = "firstName") String firstName ) {
        userRepository.save(new User(firstName, lastName));
        return "welcome";
    }

    @GetMapping("/findAll")
    public String findAllUsers( ModelMap model ) {
        model.put("time", new Date());
        model.put("message", userRepository.findAll());
        return "welcome";
    }

    @GetMapping("/findUser")
    public String findUser( ModelMap model, @NotNull(message = "lastName不能为空") @RequestParam(required = false) String lastName ) {
        List<User> list;
        list = userRepository.findByLastName(lastName);
        model.put("time", new Date());
        model.put("message", list);
        return "welcome";
    }

    @GetMapping("/findUser/{lastName}/{firstName}")
    public String findUser( ModelMap model, @PathVariable String lastName, @PathVariable String firstName ) {
        List<User> list;
        if (StringUtils.isEmpty(firstName)) {
            list = userRepository.findByLastName(lastName);
        } else {
            list = userRepository.findByFirstNameAndLastName(firstName, lastName);
        }
        model.put("time", new Date());
        model.put("message", list);
        return "welcome";
    }

    @GetMapping("/findRedis/{key}")
    public String findRedis( ModelMap model, @PathVariable String key ) {
        model.put("time", new Date());
        model.put("message", userRedis.findData(key));
        return "welcome";
    }

    @GetMapping("/saveRedis")
    public String saveRedis( ModelMap model, @RequestParam String key, @RequestParam String value ) {
        userRedis.addData(key, value);
        model.put("time", new Date());
        model.put("message", userRedis.findData(key));
        return "welcome";
    }

}
