package org.gdgchugoku.sinmetal.service;

import java.util.ArrayList;

import org.gdgchugoku.sinmetal.meta.MemberMeta;
import org.gdgchugoku.sinmetal.model.Member;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.User;

public class MemberService {

    private final MemberMeta meta = MemberMeta.get();

    public Key put(User user) {
        return put(null, user);
    }

    public Key put(Transaction tx, User user) {
        final Member member = new Member();
        member.setKey(Member.createKey(user));
        member.setUser(user);
        member.setToDoKeys(new ArrayList<Key>());
        return Datastore.put(tx, member);
    }
    
    public Key put(Transaction tx, Member member) {
        return Datastore.put(tx, member);
    }

    public Member get(User user) {
        return Datastore.get(meta, Member.createKey(user));
    }
}
