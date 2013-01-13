package org.gdgchugoku.sinmetal.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ToDoTest extends AppEngineTestCase {

    @Test
    public void createKeyのテスト() throws Exception {
        final User user = new User("test@hoge.com", "hoge.com");
        final String memo = "TODOメモ";
        final Key toDoKey = ToDo.createKey(user, memo);
        assertThat(toDoKey, is(notNullValue()));
    }
    
    @Test
    public void getInstanceのテスト() throws Exception {
        final User user = new User("test@hoge.com", "hoge.com");
        final String memo = "TODOメモ";
        final ToDo instance = ToDo.getInstance(user, memo);
        assertThat(instance, is(notNullValue()));
        assertThat(instance.getKey(), is(notNullValue()));
        assertThat(instance.getMemo(), is(memo));
        assertThat(instance.getEntryDate(), is(notNullValue()));

    }
}
