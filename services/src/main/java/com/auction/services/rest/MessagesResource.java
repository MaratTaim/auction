package com.auction.services.rest;

import com.auction.commons.entity.User;
import com.auction.services.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("messages")
public class MessagesResource {

    private Logger logger = LoggerFactory.getLogger("MESSAGES");

    @Inject
    private UserDao userDao;

    @PersistenceContext(unitName = "psunit1")
    private EntityManager entityManager;

    @GET
    public String message() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        List<String> names =  query.getResultList().stream().map(User::getName).collect(Collectors.toList());
        logger.info("Print Names " + new ArrayList<>(names));
        return "[hello, docker, "+userDao.getUser()+"]" + " Users [" + new ArrayList<>(names) + "]";
    }
}
