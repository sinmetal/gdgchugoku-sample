package org.gdgchugoku.sinmetal.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-01-06 21:28:48")
/** */
public final class MemberMeta extends org.slim3.datastore.ModelMeta<org.gdgchugoku.sinmetal.model.Member> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.Member, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.Member, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<org.gdgchugoku.sinmetal.model.Member, java.util.List<com.google.appengine.api.datastore.Key>, com.google.appengine.api.datastore.Key> toDoKeys = new org.slim3.datastore.CollectionAttributeMeta<org.gdgchugoku.sinmetal.model.Member, java.util.List<com.google.appengine.api.datastore.Key>, com.google.appengine.api.datastore.Key>(this, "toDoKeys", "toDoKeys", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.Member, com.google.appengine.api.users.User> user = new org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.Member, com.google.appengine.api.users.User>(this, "user", "user", com.google.appengine.api.users.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.Member, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.Member, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final MemberMeta slim3_singleton = new MemberMeta();

    /**
     * @return the singleton
     */
    public static MemberMeta get() {
       return slim3_singleton;
    }

    /** */
    public MemberMeta() {
        super("Member", org.gdgchugoku.sinmetal.model.Member.class);
    }

    @Override
    public org.gdgchugoku.sinmetal.model.Member entityToModel(com.google.appengine.api.datastore.Entity entity) {
        org.gdgchugoku.sinmetal.model.Member model = new org.gdgchugoku.sinmetal.model.Member();
        model.setKey(entity.getKey());
        model.setToDoKeys(toList(com.google.appengine.api.datastore.Key.class, entity.getProperty("toDoKeys")));
        model.setUser((com.google.appengine.api.users.User) entity.getProperty("user"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        org.gdgchugoku.sinmetal.model.Member m = (org.gdgchugoku.sinmetal.model.Member) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("toDoKeys", m.getToDoKeys());
        entity.setProperty("user", m.getUser());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        org.gdgchugoku.sinmetal.model.Member m = (org.gdgchugoku.sinmetal.model.Member) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        org.gdgchugoku.sinmetal.model.Member m = (org.gdgchugoku.sinmetal.model.Member) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        org.gdgchugoku.sinmetal.model.Member m = (org.gdgchugoku.sinmetal.model.Member) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        org.gdgchugoku.sinmetal.model.Member m = (org.gdgchugoku.sinmetal.model.Member) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        org.gdgchugoku.sinmetal.model.Member m = (org.gdgchugoku.sinmetal.model.Member) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getToDoKeys() != null){
            writer.setNextPropertyName("toDoKeys");
            writer.beginArray();
            for(com.google.appengine.api.datastore.Key v : m.getToDoKeys()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getUser() != null){
            writer.setNextPropertyName("user");
            encoder0.encode(writer, m.getUser());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected org.gdgchugoku.sinmetal.model.Member jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        org.gdgchugoku.sinmetal.model.Member m = new org.gdgchugoku.sinmetal.model.Member();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("toDoKeys");
        {
            java.util.ArrayList<com.google.appengine.api.datastore.Key> elements = new java.util.ArrayList<com.google.appengine.api.datastore.Key>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("toDoKeys");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    com.google.appengine.api.datastore.Key v = decoder0.decode(reader, (com.google.appengine.api.datastore.Key)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setToDoKeys(elements);
            }
        }
        reader = rootReader.newObjectReader("user");
        m.setUser(decoder0.decode(reader, m.getUser()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}