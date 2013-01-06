package org.gdgchugoku.sinmetal.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-01-06 13:30:58")
/** */
public final class ToDoMeta extends org.slim3.datastore.ModelMeta<org.gdgchugoku.sinmetal.model.ToDo> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo, java.util.Date> entryDate = new org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo, java.util.Date>(this, "entryDate", "entryDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo> memo = new org.slim3.datastore.StringAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo>(this, "memo", "memo");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<org.gdgchugoku.sinmetal.model.ToDo, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final ToDoMeta slim3_singleton = new ToDoMeta();

    /**
     * @return the singleton
     */
    public static ToDoMeta get() {
       return slim3_singleton;
    }

    /** */
    public ToDoMeta() {
        super("ToDo", org.gdgchugoku.sinmetal.model.ToDo.class);
    }

    @Override
    public org.gdgchugoku.sinmetal.model.ToDo entityToModel(com.google.appengine.api.datastore.Entity entity) {
        org.gdgchugoku.sinmetal.model.ToDo model = new org.gdgchugoku.sinmetal.model.ToDo();
        model.setEntryDate((java.util.Date) entity.getProperty("entryDate"));
        model.setKey(entity.getKey());
        model.setMemo((java.lang.String) entity.getProperty("memo"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        org.gdgchugoku.sinmetal.model.ToDo m = (org.gdgchugoku.sinmetal.model.ToDo) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("entryDate", m.getEntryDate());
        entity.setProperty("memo", m.getMemo());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        org.gdgchugoku.sinmetal.model.ToDo m = (org.gdgchugoku.sinmetal.model.ToDo) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        org.gdgchugoku.sinmetal.model.ToDo m = (org.gdgchugoku.sinmetal.model.ToDo) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        org.gdgchugoku.sinmetal.model.ToDo m = (org.gdgchugoku.sinmetal.model.ToDo) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        org.gdgchugoku.sinmetal.model.ToDo m = (org.gdgchugoku.sinmetal.model.ToDo) model;
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
        org.gdgchugoku.sinmetal.model.ToDo m = (org.gdgchugoku.sinmetal.model.ToDo) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getEntryDate() != null){
            writer.setNextPropertyName("entryDate");
            encoder0.encode(writer, m.getEntryDate());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getMemo() != null){
            writer.setNextPropertyName("memo");
            encoder0.encode(writer, m.getMemo());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected org.gdgchugoku.sinmetal.model.ToDo jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        org.gdgchugoku.sinmetal.model.ToDo m = new org.gdgchugoku.sinmetal.model.ToDo();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("entryDate");
        m.setEntryDate(decoder0.decode(reader, m.getEntryDate()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("memo");
        m.setMemo(decoder0.decode(reader, m.getMemo()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}