package org.gdgchugoku.sinmetal.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gdgchugoku.sinmetal.meta.ToDoMeta;
import org.gdgchugoku.sinmetal.model.Member;
import org.gdgchugoku.sinmetal.model.ToDo;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ToDoManagerServiceTest extends AppEngineTestCase {

    private LocalServiceTestHelper helper;

    private ToDoManagerService service = new ToDoManagerService();

    @Override
    public void setUp() throws Exception {
        helper =
            new LocalServiceTestHelper(
                new LocalDatastoreServiceTestConfig()
                    .setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

        helper.setUp();
        super.setUp();

        Map<String, Object> envAttributes = new HashMap<String, Object>();
        envAttributes.put(
            "com.google.appengine.api.users.UserService.user_id_key",
            "42");

        tester.environment.setEmail("test@exsample.co.jp");
        tester.environment.setAttributes(envAttributes);
        
        saveTestMember();
    }
    
    private void saveTestMember() {
        final User user = new User("test@exsample.co.jp", "gmail.com");
        final Member member = new Member();
        member.setKey(Member.createKey(user));
        member.setUser(user);
        member.setToDoKeys(new ArrayList<Key>());
        Datastore.put(member);
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        helper.tearDown();
    }

    @Test
    public void putされた内容確認() {
        final User user = new User("test@exsample.co.jp", "gmail.com");
        final String MEMO = "TODOメモ";

        final Key toDoKey = service.put(user, MEMO);
        final ToDo toDo = Datastore.get(ToDoMeta.get(), toDoKey);
        assertThat("メモが保存されている", toDo.getMemo(), is(MEMO));
        assertThat("登録日時が保存されている", toDo.getEntryDate(), is(notNullValue()));

        final Member member = new MemberService().get(user);
        List<Key> toDoKeys = member.getToDoKeys();
        assertThat(toDoKeys, is(notNullValue()));
        assertThat(toDoKeys.size(), is(1));
        assertThat(toDoKeys.get(0), is(toDoKey));
    }
}
