package org.gdgchugoku.sinmetal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gdgchugoku.sinmetal.meta.ToDoMeta;
import org.gdgchugoku.sinmetal.model.ToDo;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;


public class ToDoService {

    private final ToDoMeta meta = ToDoMeta.get();
    
    public Key put(Transaction tx, String memo) {
        final ToDo toDo = new ToDo();
        //TODO Keyをsetするのを忘れていた
        toDo.setMemo(memo);
        toDo.setEntryDate(new Date());
        return Datastore.put(tx, toDo);
    }
    
    public List<ToDo> get(List<Key> keys) {
        if (keys == null || keys.size() == 0) {
            return new ArrayList<ToDo>();
        }
        return Datastore.get(meta, keys);
    }
}
