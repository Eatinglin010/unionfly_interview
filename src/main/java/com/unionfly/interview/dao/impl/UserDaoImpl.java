package com.unionfly.interview.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.unionfly.interview.dao.UserDao;
import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String createUser(UserRegisterRequest userRegisterRequest) {
        User user = new User(userRegisterRequest);
        User userRegistereg = mongoTemplate.insert(user);
        return userRegistereg.getId();
    }

    @Override
    public User getUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        List<User> users = mongoTemplate.find(query, User.class);
        return users.size()!=0?users.get(0):null;
    }

    @Override
    public User getUserByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<User> users = mongoTemplate.find(query, User.class,"user");
        return users.size()!=0?users.get(0):null;
    }

    @Override
    public User getUserByJwt(String jwt) {
        Query query = new Query();
        query.addCriteria(Criteria.where("jwt").is(jwt));
        List<User> users = mongoTemplate.find(query, User.class,"user");
        return users.size()!=0?users.get(0):null;
    }

    @Override
    public String deleteById(String userId) {
        User user = this.getUserById(userId);
        if(user!=null){

            String acount = user.getAccount();
            mongoTemplate.remove(user);
            return "成功刪除:"+acount;
        }
        return "刪除錯誤:查無此帳號";
    }

    @Override
    public void updateUser(String userId, User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        Update update = new Update();
        if(user.getPassword()!=null){
            update.set("password",user.getPassword());
        }if(user.getEmail()!=null){
            update.set("email",user.getEmail());
        }if(user.getAccount()!=null){
            update.set("account",user.getAccount());
        }if(user.getJwt()!=null){
            update.set("jwt",user.getJwt());
        }
        update.currentDate("updateTime");
        mongoTemplate.updateFirst(query,update,"user");
    }

}
