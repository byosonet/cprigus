package com.cprigus.services.god.persistence.hbm;

/**
 *
 * @author User
 */
public class PropiedadSistema implements java.io.Serializable{
    private int id;
    private String key;
    private String value;
    private int active;
    
    public PropiedadSistema(){
    }
    
    public PropiedadSistema(int id, String key, String value, int active){
        this.id = id;
        this.key = key;
        this.value = value;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "PropiedadSistema{" + "id=" + id + ", key=" + key + ", value=" + value + ", active=" + active + '}';
    }

}
