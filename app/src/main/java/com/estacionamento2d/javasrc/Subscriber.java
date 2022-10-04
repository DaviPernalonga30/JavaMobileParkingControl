package com.estacionamento2d.javasrc;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author davip
 */
public class Subscriber implements Serializable {
    private String      name;
    private String      carModel;
    private String      subscriptionDate;
    private String      subscriptionDeadLine;
    private String      weekDays;
    private String      contact;
    private String      license;
    private Boolean     isMensalist = true;
    private Boolean     isMotorBike = false;
    private int         postgresId = 0;




    //Setters
    public void setName(String aux){
        this.name = aux;
    }
    public void setCarModel(String aux){
        this.carModel = aux;
    }
    public void setManualSubscriptionDate(String aux){
        this.subscriptionDate = aux;
    }
    public void setManualSubscriptionDeadLine(String aux){
        this.subscriptionDeadLine = aux;
    }
    public void setWeekDays(String aux){
        this.weekDays = aux;
    }
    public void setContact(String aux){
        this.contact = aux;
    }
    public void setLicense(String aux){
        this.license = aux;
    }
    public void setIsMensalist(Boolean aux){
        this.isMensalist = aux;
    }

    public void setIsMotorBike(boolean aux){
        this.isMotorBike = aux;
    }
    public void setPostgresId(int aux){
        this.postgresId = aux;
    }


    //Getters
    public String getName(){
        return this.name;
    }
    public String getCarModel(){
        return this.carModel;
    }
    public String getSubscriptionDate(){
        return this.subscriptionDate;
    }
    public String getSubscriptionDeadLine(){
        return this.subscriptionDeadLine;
    }
    public String getWeekDays(){
        return this.weekDays;
    }
    public String getContact(){
        return this.contact;
    }
    public String getLicense(){
        return this.license;
    }
    public Boolean getIsMensalist(){
        return this.isMensalist;

    }

    public void setAutoSubscriptionDate(){
        String aux;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        aux = formatter.format(Calendar.getInstance().getTime());
        this.setManualSubscriptionDate(aux);
    }

    public void setAutoSubscriptionDeadLine(){
        String aux = this.getSubscriptionDate();
        String aux2, aux3, aux4;
        int auxint1, auxint2;
        aux2    = aux.substring(0, 2);
        aux3    = aux.substring(3, 5);
        aux4    = aux.substring(6, 10);
        auxint1 = Integer.parseInt(aux3);
        auxint2 = Integer.parseInt(aux4);
        if(auxint1 == 12){
            auxint1 = 1;
            auxint2 = auxint2 + 1;
        }
        else{
            auxint1 = auxint1 + 1;
        }
        if(auxint1 > 9){
            aux3 = String.valueOf(auxint1);
        }
        else{
            aux3 = "0" + String.valueOf(auxint1);
        }

        aux = aux2+ "/" + aux3 + "/" + String.valueOf(auxint2);
        this.setManualSubscriptionDeadLine(aux);


    }


    public void setAutoIsMensalist(){
        String aux1, aux2, aux3;
        int auxint1, auxint2, auxint3;
        int dateaux1, dateaux2, dateaux3;
        SimpleDateFormat formatter1, formatter2, formatter3;
        formatter1 = new SimpleDateFormat("dd");
        aux1 = formatter1.format(Calendar.getInstance().getTime());
        dateaux1 = Integer.parseInt(aux1);
        formatter2 = new SimpleDateFormat("MM");
        aux2 = formatter2.format(Calendar.getInstance().getTime());
        dateaux2 = Integer.parseInt(aux2);
        formatter3 = new SimpleDateFormat("yyyy");
        aux3 = formatter3.format(Calendar.getInstance().getTime());
        dateaux3 = Integer.parseInt(aux3);


        auxint1 = Integer.parseInt(this.getSubscriptionDeadLine().substring(0, 2));
        auxint2 = Integer.parseInt(this.getSubscriptionDeadLine().substring(3, 5));
        auxint3 = Integer.parseInt(this.getSubscriptionDeadLine().substring(6, 10));





        if(dateaux3 <= auxint3){
            if(dateaux2 < auxint2){
                this.setIsMensalist(true);
                return;

            }
            else if(dateaux2 > auxint2){
                this.setIsMensalist(false);
                return;
            }

            else{
                if(dateaux1 <= auxint1){
                    this.setIsMensalist(true);
                    return;
                }
            }

        }
        this.setIsMensalist(false);
    }

    public boolean getIsMotorBike(){
        return this.isMotorBike;
    }

    public int getPostgresId(){
        return this.postgresId;
    }


}
