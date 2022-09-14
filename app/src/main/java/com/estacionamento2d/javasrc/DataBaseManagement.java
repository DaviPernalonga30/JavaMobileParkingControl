package com.estacionamento2d.javasrc;



import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author davip
 */

//Essa questão do Extends Async pode ser a salvação, mas ainda nao entendo
    //pq ta falando que o connect n da certo.
public class DataBaseManagement{
    final private String url= "jdbc:postgresql://10.0.2.2:5050/2D-Estacionamento";
    final private String user = "postgres";
    final private String password = "postgres";
    protected Connection con;


    public void setCon(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection(url, user, password);

                    System.out.println("connected:");
                }
                catch (Exception e)
                {

                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }


    public DataBaseManagement(){
        this.setCon();


    }




    public void insertIntoSubscriber(Subscriber sub){
        String sqlcmd = "INSERT INTO public.subscriber "
                + "(str_name, str_carmodel, str_contact, str_initdate, str_enddate, str_license, str_weekdays, bool_ismensalist, bool_ismotorbike) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";



        try(java.sql.PreparedStatement st = this.con.prepareStatement(sqlcmd)){
            st.setString(1, sub.getName());
            st.setString(2, sub.getCarModel());
            st.setString(3, sub.getContact());
            st.setString(4, sub.getSubscriptionDate());
            st.setString(5, sub.getSubscriptionDeadLine());
            st.setString(6, sub.getLicense());
            st.setString(7, sub.getWeekDays());
            st.setBoolean(8, sub.getIsMensalist());
            st.setBoolean(9, sub.getIsMotorBike());

            st.executeUpdate();


            //System.out.println("deu certo: " + sqlcmd);



        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public java.util.ArrayList selectFromSubscriber(){
        //depois criar uma regra para que só sejam carregados
        //aqueles que em 3 meses ainda foram mensalistas.

        java.util.ArrayList subList = new ArrayList();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                String query = "SELECT * FROM public.subscriber";

                try(java.sql.Statement st = con.createStatement()){
                    java.sql.ResultSet rs = st.executeQuery(query);
                    while(rs.next()){
                        Subscriber sub = new Subscriber();
                        sub.setName(rs.getString("str_name"));
                        sub.setCarModel(rs.getString("str_carmodel"));
                        sub.setContact(rs.getString("str_contact"));
                        sub.setManualSubscriptionDate(rs.getString("str_initdate"));
                        sub.setManualSubscriptionDeadLine(rs.getString("str_enddate"));
                        sub.setLicense(rs.getString("str_license"));
                        sub.setWeekDays(rs.getString("str_weekdays"));
                        sub.setIsMensalist(rs.getBoolean("bool_ismensalist"));
                        sub.setIsMotorBike(rs.getBoolean("bool_ismotorbike"));
                        sub.setPostgresId(rs.getInt("id_mensalista"));

                        subList.add(sub);

                    }


                } catch (SQLException ex) {
                    Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
                }




            }
        });
        th.start();
        try
        {
            th.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return subList;




    }


    public void updateItemFromSubscriber(Subscriber subOld, Subscriber subNew){
        String sqlcmd = "UPDATE public.subscriber "
                + "SET str_name = ?, str_carmodel = ?, str_contact = ?, str_initdate = ?, str_enddate = ?, str_license = ?, str_weekdays = ?, bool_ismensalist = ?, bool_ismotorbike = ? "
                + "WHERE id_mensalista = ?;";


        String query = "SELECT id_mensalista, str_name FROM public.subscriber WHERE UPPER(str_name)=?";

        if(subNew.getPostgresId() == 0){
            //System.out.println("primeiro");
            try(java.sql.PreparedStatement st = this.con.prepareStatement(query)){

                st.setString(1, subOld.getName().toUpperCase());

                java.sql.ResultSet rs = st.executeQuery();
                while(rs.next()){

                    subNew.setPostgresId(rs.getInt("id_mensalista"));

                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try(java.sql.PreparedStatement st = this.con.prepareStatement(sqlcmd)){
            st.setString(1, subNew.getName());
            st.setString(2, subNew.getCarModel());
            st.setString(3, subNew.getContact());
            st.setString(4, subNew.getSubscriptionDate());
            st.setString(5, subNew.getSubscriptionDeadLine());
            st.setString(6, subNew.getLicense());
            st.setString(7, subNew.getWeekDays());
            st.setBoolean(8, subNew.getIsMensalist());
            st.setBoolean(9, subNew.getIsMotorBike());
            st.setInt(10, subNew.getPostgresId());

            st.executeUpdate();

            //System.out.println("deu certo: " + sqlcmd);

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Criar a regra de update
        //https://www.javaguides.net/2020/02/java-jdbc-postgresql-update-example.html


    }


    public void insertIntoVeicule(VeiculeClass veic){
        String sqlcmd = "INSERT INTO public.veicule"
                + "(str_license, str_timein, str_timeout, bool_issubscriber, bool_haskey, bool_ismotorbike, str_date) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";


        try(java.sql.PreparedStatement st = this.con.prepareStatement(sqlcmd)){
            st.setString(1, veic.getLicense());
            st.setString(2, veic.getTimeIn());
            st.setString(3, veic.getTimeOut());
            st.setBoolean(4, veic.getIsSubscriber());
            st.setBoolean(5, veic.getHasKey());
            st.setBoolean(6, veic.getIsMotorBike());
            st.setString(7, veic.getDate());

            st.executeUpdate();

            //System.out.println("deu certo: " + sqlcmd);


        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public java.util.ArrayList selectFromVeicule(){
        java.util.ArrayList<VeiculeClass> veicList= new java.util.ArrayList();
        String sqlcmd = "SELECT * FROM public.veicule WHERE str_date=?";
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String auxDate = formatter.format(java.util.Calendar.getInstance().getTime());
        //fazer a parte do calendário e do formatter.



        this.setCon();
        try(java.sql.PreparedStatement st = this.con.prepareStatement(sqlcmd)){
            st.setString(1, auxDate);

            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                VeiculeClass aux = new VeiculeClass();
                aux.setLicense(rs.getString("str_license"));
                aux.setManualTimeIn(rs.getString("str_timein"));
                aux.setManualTimeOut(rs.getString("str_timeout"));
                aux.setManualIsSubscriber(rs.getBoolean("bool_issubscriber"));
                aux.setHasKey(rs.getBoolean("bool_haskey"));
                aux.setIsMotorBike(rs.getBoolean("bool_ismotorbike"));
                aux.setManualDate(rs.getString("str_date"));
                aux.setPostgresId(rs.getInt("id_veiculo"));

                veicList.add(aux);

            }




        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }



        return veicList;
    }

    public void updateItemFromVeicule(VeiculeClass veicOld, VeiculeClass veicNew){
        String sqlcmd = "UPDATE public.veicule "
                + "SET str_license = ?, str_timein = ?, str_timeout = ?, bool_issubscriber = ?, bool_haskey = ?, bool_ismotorbike = ? "
                + "WHERE id_veiculo = ?";


        String query = "SELECT id_veiculo, str_license FROM public.veicule WHERE str_license=? and str_date=?";


        if(veicNew.getPostgresId() == 0){
            try(java.sql.PreparedStatement st = this.con.prepareStatement(query)){
                //System.out.println(veicOld.getLicense());
                st.setString(1, veicOld.getLicense());
                st.setString(2, veicOld.getDate());

                java.sql.ResultSet rs = st.executeQuery();

                while(rs.next()){
                    veicNew.setPostgresId(rs.getInt("id_veiculo"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try(java.sql.PreparedStatement st = this.con.prepareStatement(sqlcmd)){
            //System.out.println(veicOld.getLicense());
            //System.out.println(veicNew.getLicense());
            st.setString(1, veicNew.getLicense());
            st.setString(2, veicNew.getTimeIn());
            st.setString(3, veicNew.getTimeOut());
            st.setBoolean(4, veicNew.getIsSubscriber());
            st.setBoolean(5, veicNew.getHasKey());
            st.setBoolean(6, veicNew.getIsMotorBike());
            st.setInt(7, veicNew.getPostgresId());


            st.executeUpdate();



        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }






}

