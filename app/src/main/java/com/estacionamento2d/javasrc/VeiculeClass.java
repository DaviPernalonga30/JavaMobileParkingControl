package com.estacionamento2d.javasrc;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author davip
 */
public class VeiculeClass implements Serializable{
    private SimpleDateFormat    formatter;
    private String              license;
    private String              date;
    private String              timeIn;
    private String              timeOut = "";
    private Boolean             isSubscriber;
    private Boolean             hasKey = false;
    private Boolean             isMotorBike = false;
    private int                 postgresId = 0;




    public void setLicense(String aux){
        this.license = aux.toUpperCase();
    }

    public String getLicense(){
        return this.license.toUpperCase();
    }

    public void setManualTimeIn(String aux){
        this.timeIn = aux;
    }

    public String getTimeIn(){
        return this.timeIn;
    }

    public void setManualTimeOut(String aux){
        this.timeOut = aux;
    }

    public String getTimeOut(){
        return this.timeOut;
    }

    public void setAutoTimeIn(){
        formatter = new SimpleDateFormat("HH:mm");
        this.timeIn = formatter.format(Calendar.getInstance().getTime());
    }

    public void setAutoTimeOut(){
        formatter = new SimpleDateFormat("HH:mm");
        this.timeOut = formatter.format(Calendar.getInstance().getTime());
    }

    public void setIsSubscriber(ArrayList<Subscriber> list){
        String auxLicense = this.getLicense();
        this.isSubscriber = false;
        for(int i = 0; i<list.size(); i = i+1){
            list.get(i).setAutoIsMensalist();
            if(list.get(i).getLicense().equals(auxLicense)){
                if(list.get(i).getIsMensalist() == true){
                    this.isSubscriber = true;
                }
            }
        }
    }

    public void setManualIsSubscriber(boolean aux){
        this.isSubscriber = aux;
    }

    public Boolean getIsSubscriber(){
        return this.isSubscriber;

    }

    public void setHasKey(Boolean key){
        this.hasKey = key;
    }

    public Boolean getHasKey(){
        return this.hasKey;
    }

    public void setIsMotorBike(boolean aux){
        this.isMotorBike = aux;
    }

    public boolean getIsMotorBike(){
        return this.isMotorBike;
    }

    public void setDate(){
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date = formatter.format(Calendar.getInstance().getTime());
    }

    public void setManualDate(String str){
        this.date = str;
    }

    public String getDate(){
        return this.date;
    }

    public void setPostgresId(int aux){
        this.postgresId = aux;
    }

    public int getPostgresId(){
        return this.postgresId;
    }



}
