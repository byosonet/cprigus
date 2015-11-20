package com.cprigus.services.god.persistence.hbm;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.rowset.serial.SerialClob;

/**
 *
 * @author User
 */
public class DeliveryFailed implements java.io.Serializable{
    
    private int idFailed;
    private String mailTo;
    private String mailFrom;
    private String subject;
    private String name;
    private Clob body;
    private String typeFailed;
    private Date dateSend;
    private String detalleClob;
    
    public DeliveryFailed(){
        
    }
    
    public DeliveryFailed(int idFailed, String mailTo, String mailFrom, 
            String subject, String name, Clob body, String typeFailed, Date dateSend, String detalleClob){
        
        this.idFailed = idFailed;
        this.mailTo = mailTo;
        this.mailFrom = mailFrom;
        this.subject = subject;
        this.name = name;
        this.body = body;
        this.typeFailed = typeFailed;
        this.dateSend = dateSend;
        this.detalleClob = detalleClob;
        
    }

    public int getIdFailed() {
        return idFailed;
    }

    public void setIdFailed(int idFailed) {
        this.idFailed = idFailed;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clob getBody() {
        return body;
    }

    public void setBody(Clob body) {
        this.body = body;
    }

    public String getTypeFailed() {
        return typeFailed;
    }

    public void setTypeFailed(String typeFailed) {
        this.typeFailed = typeFailed;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public String getDetalleClob() {
        return detalleClob;
    }

    public void setDetalleClob(String detalleClob) {
        this.detalleClob = detalleClob;
    }
    
    public String readClob(Clob clob) throws SQLException, IOException {
        StringBuilder sb = new StringBuilder((int) clob.length());
        Reader r = clob.getCharacterStream();
        char[] cbuf = new char[2048];
        int n;
        while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
            sb.append(cbuf, 0, n);
        }
        return sb.toString();
    }
    
     public Clob covertirStringToClob(String des) throws SQLException{
        Clob myClobFile = new SerialClob(des.toCharArray());
        return myClobFile;
    }

    @Override
    public String toString() {
        return "DeliveryFailed{" + "idFailed=" + idFailed + ", mailTo=" + mailTo + ", mailFrom=" + mailFrom + ", subject=" + subject + ", name=" + name + ", body=" + body + ", typeFailed=" + typeFailed + ", dateSend=" + dateSend + ", detalleClob=" + detalleClob + '}';
    }

}
