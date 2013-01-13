package org.gdgchugoku.sinmetal.model;

import java.io.Serializable;
import java.util.Date;

import org.gdgchugoku.sinmetal.meta.ToDoMeta;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@Model(schemaVersion = 1, schemaVersionName = "schemaVersion")
public class ToDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String memo;

    private Date entryDate;

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
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate
     *            the entryDate to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    
    public static Key createKey(User user, String memo) {
        final Key memberKey = Member.createKey(user);
        // ユーザのToDoの中で、同じ内容のものは複数登録できない
        final String keyName = String.format("%s,%s", memberKey.getName(), memo);
        return Datastore.createKey(ToDoMeta.get(), keyName);
    }
    
    public static ToDo getInstance(User user, String memo) {
        final ToDo instance = new ToDo();
        instance.setKey(ToDo.createKey(user, memo));
        instance.setMemo(memo);
        instance.setEntryDate(new Date());
        return instance;
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
        ToDo other = (ToDo) obj;
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
