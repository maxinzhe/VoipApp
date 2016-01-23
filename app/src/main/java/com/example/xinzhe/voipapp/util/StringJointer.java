package com.example.xinzhe.voipapp.util;

/**
 * Created by Xinzhe on 2016/1/17.
 */


public class StringJointer {
   /**
 * @param text1:first String to be jointed
    *@param symbol:the symbol between the two Strings
    * @param : the second String to be joined
 */
   public static String  topSep="$";
   public static String secSep="%";
   public static String thiSep="#";
   public String joinString(String text1,String symbol,String text2){
      return text1+symbol+text2;
   }
}
