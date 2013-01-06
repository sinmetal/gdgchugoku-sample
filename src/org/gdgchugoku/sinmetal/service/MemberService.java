package org.gdgchugoku.sinmetal.service;

import org.gdgchugoku.sinmetal.model.Member;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;


public class MemberService {

    public Key put(User user) {
        final Member member = new Member();
        member.setKey(Member.createKey(user));
        member.setUser(user);
        return Datastore.put(member);
    }
}
