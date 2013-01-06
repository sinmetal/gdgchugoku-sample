package org.gdgchugoku.sinmetal.service;

import java.util.Date;

import org.gdgchugoku.sinmetal.model.ToDo;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;


public class ToDoService {

    public Key put(Transaction tx, String memo) {
        final ToDo toDo = new ToDo();
        toDo.setMemo(memo);
        toDo.setEntryDate(new Date());
        return Datastore.put(tx, toDo);
    }
}
