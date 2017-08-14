/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ads.interfaces.impl;
import com.ads.beans.User;
import com.ads.interfaces.UserDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Rahul
 */
//@Component("userDAOImpl")
@Repository
@Scope("singleton")
@Transactional(readOnly = false)
public class UserDAOImpl implements UserDAO{
    
    @Autowired(required = true)
    @Qualifier("hibernateTemplate")
    HibernateTemplate hibernateTemplate;

    public UserDAOImpl() {
        System.out.println("UserDAOImpl created..");
    }
    
    @Override
    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    @Override
    public void addUser(User user) {
        hibernateTemplate.save(user);
    }

    @Override
    public User getUserById(String id) {
        User user  = hibernateTemplate.get(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        hibernateTemplate.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return hibernateTemplate.loadAll(User.class);
    }

}
