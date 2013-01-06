package org.gdgchugoku.sinmetal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import org.gdgchugoku.sinmetal.meta.MemberMeta;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    private User user;
    
    private List<Key> toDoKeys;

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the toDoKeys
     */
    public List<Key> getToDoKeys() {
        return toDoKeys;
    }

    /**
     * @param toDoKeys the toDoKeys to set
     */
    public void setToDoKeys(List<Key> toDoKeys) {
        this.toDoKeys = toDoKeys;
    }
    
    public void addToDoKey(Key key) {
        if (toDoKeys == null) {
            toDoKeys = new ArrayList<Key>();
        }
        
        toDoKeys.add(key);
    }
    
    public static Key createKey(User user) {
        return Datastore.createKey(MemberMeta.get(), user.getEmail());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Member other = (Member) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
