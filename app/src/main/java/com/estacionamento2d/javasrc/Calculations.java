package com.estacionamento2d.javasrc;

import java.util.ArrayList;

/**
 *
 * @author davip
 */
public class Calculations {
    private float valTurnCar;
    private float valFractionHrCar;
    private float valTurnMoto;
    private float valFractionHrMoto;
    private float dayReturn;
    private float valDevido;



    //Implementar mais somas aqui.
    //inclusive de mensalistas e avulsos.
    public void calcEndDay(java.util.ArrayList<VeiculeClass> veicList){
        float sum = 0;

        for(int i = 0; i<veicList.size(); i=i+1) {
            VeiculeClass aux = veicList.get(i);
            if(!aux.getTimeIn().equals("--")){
                if(!aux.getTimeOut().equals("")){
                    if (aux.getIsSubscriber() == false) {
                        if (aux.getIsMotorBike() == true) {
                            sum = sum + this.valTurnMoto;
                        } else {
                            sum = sum + this.valTurnCar;
                        }
                    }
                }

            }


        }
        //criar getters e setters.
        this.setDayReturn(sum);
    }


    //getters
    public float getValTurnCar(){
        return this.valTurnCar;
    }
    public float getValTurnMoto(){
        return this.valTurnMoto;
    }
    public float getValFractionHrCar(){
        return this.valFractionHrCar;
    }
    public float getValFractionHrMoto(){
        return this.valFractionHrMoto;
    }
    public float getDayReturn(ArrayList<VeiculeClass> veic){
        calcEndDay(veic);
        return this.dayReturn;
    }

    public float getValDevido(VeiculeClass veic){
        if(veic.getIsSubscriber()== true){
            return 0;
        }
        if(veic.getIsMotorBike() == true){
            this.setValDevido(this.getValTurnMoto());
        }
        else{
            this.setValDevido(this.getValTurnCar());
        }

        return this.valDevido;
    }




    //Setters
    public void setValTurnCar(float aux){
        this.valTurnCar = aux;
    }
    public void setValTurnMoto(float aux){
        this.valTurnMoto = aux;
    }
    public void setValFractionHrCar(float aux){
        this.valFractionHrCar = aux;
    }
    public void setValFractionHrMoto(float aux){
        this.valFractionHrMoto = aux;
    }
    public void setDayReturn(float aux){
        this.dayReturn = aux;

    }
    public void setValDevido(float aux){
        this.valDevido = aux;
    }
}
