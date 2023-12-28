import java.io.*;
import java.net.*;

  class Thread_Socket_Client extends Thread
  {
    static public  PrintWriter pred;
    static public  BufferedReader plec;  
    static public  InetAddress adr;
    static public  Socket Client;
    static public  boolean Fin = false;
    static public  Thread_Gestion_Option_Client GESTION_ET_OPTIONS_CLIENT;
    static public  final int Port = 8080;
    
    Thread_Socket_Client (Thread_Gestion_Option_Client GOC) throws Exception 
    {
     GESTION_ET_OPTIONS_CLIENT = GOC ;
    }
    //===================================================================
    public boolean CONNEX_DECONNEX(boolean CONNEXION)//throws Exception
    {
     if( ! CONNEXION )
     {
       try{
        adr= InetAddress.getLocalHost();
        Client = new Socket(adr,Port);
        plec = new BufferedReader(new InputStreamReader(Client.getInputStream()));
        pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Client.getOutputStream())),true);
        ENVOYER (GESTION_ET_OPTIONS_CLIENT.NOM_CLIENT);
        System.out.println("\n     "+(char)26+" CLIENT CONNECTER ... ");
        return true;
        }
       catch(Exception e)
        {
        System.out.println("\n     "+(char)26+" SERVEUR INTROUVABLE ... ");
        return false;
        }
     }
     else
     { 
       try{
        ENVOYER(Protocole_COM.DECONNEXION_CLIENT);
        plec.close();
        pred.close();
        Client.close();
        System.out.println("\n     "+(char)26+" CLIENT DECONNECTER ... ");
        return false;
        }
       catch(Exception e)
        {
        System.out.println("\n     "+(char)26+" ERREUR DE DECONNEXION ... ");
        return true;
        }
     }

    }
    //===================================================================
    public void ENVOYER (String msg) 
    {
     pred.println(msg);
    }
    //===================================================================
    public boolean JEU_OUVERT () 
    {
     pred.println(Protocole_COM.JEU_OUVERT);
     try{
           String MSG_SERVEUR = plec.readLine();
           if ( MSG_SERVEUR.equals( Protocole_COM.OUI ) )
           {
            return true; 
           }
           else
           {
            return false;
           } 
    }
    catch(Exception e)
    {
     return false;
    }
      
    }
    public double Vers_Reel(String S)
    {
     return Double.valueOf(S).doubleValue();
    }

    //===================================================================
    public void run () 
    {     
     try{     
       
       String MSG_CLIENT="";

       while(!Fin)
       { 
         //if(GESTION_ET_OPTIONS_CLIENT.CONNEXION)
         {  
           MSG_CLIENT = plec.readLine();
           if ( MSG_CLIENT.equals( Protocole_COM.BELLET_PERDU ) )
           {
            System.out.println("\n     "+(char)26+" LE SERVEUR LES NUMERO GANIANTS : ");
            System.out.println("\n     "+(char)26+" ECHOUER : AUCUN NUMERO N'EST CORRECTE ... ");
            System.out.println("\n     "+(char)26+" BENIFICE = 0 $");
           }
           else
           if ( MSG_CLIENT.equals( Protocole_COM.BELLET_GAGNIER ) )
           {
            String NBG = plec.readLine();
            String BEN = plec.readLine();
            System.out.println("\n     "+(char)26+" LE SERVEUR ANNONCE LES NUMERO GANIANTS : ");
            System.out.println("\n     "+(char)26+" LE SERVEUR : VOUS AVEZ GANGEZ ");
            System.out.println("\n     "+(char)26+" LE NOMBRE DE CHIFFRE GANGIANT EST "+NBG );
            GESTION_ET_OPTIONS_CLIENT.Solde+=Vers_Reel(BEN);
            System.out.println("\n     "+(char)26+" LE NOUVEAU SOLDE EST "+NBG );
            
           }
         }
        }   
      }
      catch(Exception e)
      {
               
      } 
     
     }

}


