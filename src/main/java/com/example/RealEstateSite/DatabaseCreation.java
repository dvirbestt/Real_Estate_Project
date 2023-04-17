package com.example.RealEstateSite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseCreation {

    public static void createDatabase (){
        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_estate_app",
                    "root","root1234");
            con.close();

        }catch (Exception e){
            try{

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root",
                        "root1234");
                Statement statement = con.createStatement();
                statement.execute("CREATE DATABASE real_estate_app");
                con.close();

            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }
}