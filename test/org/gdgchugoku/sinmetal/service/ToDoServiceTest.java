package org.gdgchugoku.sinmetal.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.gdgchugoku.sinmetal.meta.ToDoMeta;
import org.gdgchugoku.sinmetal.model.ToDo;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;

public class ToDoServiceTest extends AppEngineTestCase {

    private ToDoService service = new ToDoService();

    @Test
    public void put() throws Exception {
        final int before = tester.count(ToDo.class);
        service.put(null, "TODOメモ");
        final int after = tester.count(ToDo.class);
        assertThat("ToDoエンティティが1件増えている", after, is(before + 1));
    }

    @Test
    public void putされた内容確認() throws Exception {
        final String MEMO = "TODOメモ";

        final Key key = service.put(null, MEMO);
        final ToDo toDo = Datastore.get(ToDoMeta.get(), key);
        assertThat("メモが保存されている", toDo.getMemo(), is(MEMO));
        assertThat("登録日時が保存されている", toDo.getEntryDate(), is(notNullValue()));
    }
}
