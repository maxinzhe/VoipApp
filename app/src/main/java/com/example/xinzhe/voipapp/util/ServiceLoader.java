package com.example.xinzhe.voipapp.util;

/**
 * Created by Xinzhe on 2016/1/23.
 */
public class ServiceLoader {
    String topOrder;
    public  ServiceLoader(String topOrder){
        this.topOrder=topOrder;

    }
    public void startToManage(){
        try{
            String[] secOrder=topOrder.split("\\$");
            if(secOrder.length>1){
             switch(secOrder[0])   {
                 case "online":
                     /**
                      * detect online activity is present,if on than call a function notification to refresh the list
                      * from the database,if not present just update the database;
                      */

                     break;
                 case "offline":
                     break;
                 case "newcontacts":
                     break;
                 case "newpay":
                     break;
             }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
