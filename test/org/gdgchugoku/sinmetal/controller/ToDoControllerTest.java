package org.gdgchugoku.sinmetal.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.gdgchugoku.sinmetal.meta.MemberMeta;
import org.gdgchugoku.sinmetal.meta.ToDoMeta;
import org.gdgchugoku.sinmetal.model.Member;
import org.gdgchugoku.sinmetal.model.ToDo;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ToDoControllerTest extends ControllerTestCase {

    private LocalServiceTestHelper helper;

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
    public void post() throws NullPointerException, IllegalArgumentException,
            IOException, ServletException {
        tester.request.setMethod("POST");
        tester.param("memo", "TODOメモ");
        tester.start("/toDo");

        assertThat(
            "レスポンスコードが204",
            tester.response.getStatus(),
            is(HttpServletResponse.SC_NO_CONTENT));
    }

    @Test
    public void get() throws IOException, NullPointerException,
            IllegalArgumentException, ServletException {
        final User user = new User("test@exsample.co.jp", "gmail.com");
        List<Key> toDoKeys = new ArrayList<Key>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 5; i++) {
            ToDo toDo = new ToDo();
            toDo.setMemo("TODOメモ" + i);
            toDo.setEntryDate(calendar.getTime());
            Key key = Datastore.put(toDo);
            toDoKeys.add(key);
            calendar.add(Calendar.HOUR, 1);
        }
        Member member = Datastore.get(MemberMeta.get(), Member.createKey(user));
        member.setToDoKeys(toDoKeys);
        Datastore.put(member);
        
        tester.request.setMethod("GET");
        tester.start("/toDo");

        assertThat(
            "ToDoControllerのインスタンスが使用される",
            tester.getController(),
            instanceOf(ToDoController.class));
        assertThat(
            "レスポンスコードが200",
            tester.response.getStatus(),
            is(HttpServletResponse.SC_OK));
        assertThat(
            "Content-Typeはapplication/json",
            tester.response.getContentType(),
            is("application/json"));
        assertThat(
            "Chancter-Encodingはutf-8",
            tester.response.getCharacterEncoding(),
            is("utf-8"));
        String outputAsString = tester.response.getOutputAsString();
        ToDo[] models = ToDoMeta.get().jsonToModels(outputAsString);
        assertThat("ToDoエンティティの件数と同じ", models.length, is(5));
    }
}
