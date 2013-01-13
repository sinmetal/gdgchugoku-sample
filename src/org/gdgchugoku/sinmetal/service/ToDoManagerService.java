package org.gdgchugoku.sinmetal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gdgchugoku.sinmetal.model.Member;
import org.gdgchugoku.sinmetal.model.ToDo;
import org.slim3.datastore.EntityNotFoundRuntimeException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.google.appengine.api.users.User;

public class ToDoManagerService {

    private final Logger logger = Logger.getLogger(ToDoManagerService.class
        .getName());

    private final MemberService memberService = new MemberService();

    private final ToDoService toDoService = new ToDoService();

    public Key put(User user, String memo) {
        DatastoreService datastoreService =
            DatastoreServiceFactory.getDatastoreService();
        TransactionOptions txOps = TransactionOptions.Builder.withXG(true);

        Transaction tx = null;
        try {
            tx = datastoreService.beginTransaction(txOps);
            final Key toDoKey = toDoService.put(tx, memo);
            final Member member = memberService.get(user);
            member.addToDoKey(toDoKey);
            memberService.put(tx, member);
            tx.commit();
            return toDoKey;
        } catch (Exception e) {
            logger.log(Level.FINE, e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
                throw new RuntimeException("Datastore put error");
            }
        }
    }

    public List<ToDo> get(User user) {
        try {
            final Member member = memberService.get(user);
            return toDoService.get(member.getToDoKeys());
        } catch (EntityNotFoundRuntimeException e) {
            memberService.put(user);
            return new ArrayList<ToDo>();
        }
    }
}
