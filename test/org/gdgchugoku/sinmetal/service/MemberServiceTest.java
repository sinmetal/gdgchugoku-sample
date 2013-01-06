package org.gdgchugoku.sinmetal.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.gdgchugoku.sinmetal.meta.MemberMeta;
import org.gdgchugoku.sinmetal.model.Member;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

public class MemberServiceTest extends AppEngineTestCase {

    private MemberService service = new MemberService();

    @Test
    public void put() throws Exception {
        final User user = new User("test@exsample.co.jp", "gmail.com");

        final int before = tester.count(Member.class);
        service.put(user);
        final int after = tester.count(Member.class);
        assertThat("Memberエンティティが1件増えている", after, is(before + 1));
    }

    @Test
    public void putされた内容確認() throws Exception {
        final String EMAIL = "test@exsample.co.jp";
        final User user = new User(EMAIL, "gmail.com");

        final Key key = service.put(user);
        final Member member = Datastore.get(MemberMeta.get(), key);
        assertThat("Userが保存されている", member.getUser().getEmail(), is(EMAIL));
        assertThat(
            "ToDoKey一覧に空のListが保存されている",
            member.getToDoKeys(),
            is(notNullValue()));
    }
}
