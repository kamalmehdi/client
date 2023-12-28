import java.io.*;
import java.net.*;


class Main_Client extends Object
{
   public static Thread_Gestion_Option_Client   Affichage_ET_Option;
   
   public static void main(String[] args) throws Exception 
   {  
        Affichage_ET_Option  = new Thread_Gestion_Option_Client(); 
              
        Affichage_ET_Option.start();
                
    }
}

//=================================================================================================
//=================================================================================================
//=================================================================================================