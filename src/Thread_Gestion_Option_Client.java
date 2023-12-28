import java.io.*;
import java.net.*;
  
  class Thread_Gestion_Option_Client extends Thread
  {
   public String  NOM_CLIENT;
   public double  Solde = 0;
   static public boolean Fin  = false ;
   static public boolean CONNEXION = false ;    
   static public int NOMBRE_BILLET = 0 ;
   static public Thread_Socket_Client ClientSocket ;
   //===================================================================
   public void MENU()
   {
    System.out.println("\n __________________________________________________________________________\n");
    System.out.println(" *************  UNIVERSITE DE BEJAIA  "+(char)184+"  2007-2008  ************* ");
    System.out.println(" ******************  JEU DE LOTO CILENT/SERVEUR  **************** ");
    System.out.println(" **************************_ LE CLIENT _************************* ");
    System.out.println();
    System.out.println("   1 "+(char)26+" ETABLIR-SUPPRIMER UNE CONNEXION "+((CONNEXION)?"[ CONNECTER + ]":"[ DECONNECTER - ]"));
    System.out.println("   2 "+(char)26+" ACHETER UN BILLET DU JEU [ JOUER ] - [ Solde = "+Solde+" $ ]");
    System.out.println("   3 "+(char)26+" ENVOYER UN SIMPLE MESSAGE AU SERVEUR ");
    System.out.println("   4 "+(char)26+" A PROPOS ... ");
    System.out.println("   5 "+(char)26+" QUITTER \n");
    System.out.print  ("     "+(char)26+" CHOIX = ");
   }
   //===================================================================
   public void P_ID_CLIENT()
   {
    System.out.println(" __________________________________________________________________________\n");
    System.out.println(" *************  UNIVERSITE DE BEJAIA  "+(char)184+"  2007-2008  ************* ");
    System.out.println(" ******************  JEU DE LOTO CILENT/SERVEUR  **************** ");
    System.out.println(" **************************_ LE CLIENT _************************* ");
    System.out.println();
    System.out.print("     "+(char)26+" ENTRER VOTRE NOM = ");
    NOM_CLIENT = Lire.Chaine();
    System.out.print("     "+(char)26+" ENTRER LE SOLDE INITIALE ( Reel $ ) = ");
    Solde = Lire.Reel();
   }
   //===================================================================
   public void ACTUALISER_AFFICHAGE ()
   {
    MENU();
   }
   //===================================================================
    public void A_PROPOS()
    {
     System.out.println  ("\n\n   A Propos ... \n\n");
     System.out.println  ("     "+(char)4+" JEU LOTO - CLlient(s) / Serveur avec JAVA ...");
     System.out.println  ("     "+(char)4+" VERSION : 1.0 ");
     System.out.println  ("     "+(char)4+" LES CONCEPTEURS : MEHDI Kamel ");
     System.out.println  ("                         MEHIDI Lamine ");
     System.out.println  ("     "+(char)4+" Copyright "+(char)184+" UNIVERSITE DE BEJAIA 2007-2008  ");
     System.out.println  ("\n     "+(char)4+" Clicker Sur ENTRER pour Continue ... ");
     String FIN= Lire.Chaine();
    }
   //===================================================================
   public int CHOIX()
   {
    int choix = 0;
     MENU();
     choix = Lire.Entier();
     
    while((choix>5)||(choix<1))
    { 
     System.out.println("     "+(char)4+" LE CHOIX EST  0 < CHOIX < 6 "); 
     System.out.print  ("     "+(char)26+" CHOIX = "); 
     choix = Lire.Entier();
    } 
    
    return choix;
   }
   //===================================================================
   public void ENV_MESSAGE()   
   { 
   if(CONNEXION)
   {
     System.out.print("     "+(char)4+" LE MESSAGE A ENVOYER : ");
     String MSG = Lire.Chaine(); 
     ClientSocket.ENVOYER ( MSG ) ;
   }
   else
   {
     System.out.println("     "+(char)26+" IMPOSSIBLE : VOUS ETES DECONNECTER ... ");
   }
   }
   //===================================================================
   public void ACHETER_UN_BILLET()   
   {  
   if(CONNEXION)
   { 
     if(ClientSocket.JEU_OUVERT())
     { 
      if(Solde>20.0)
      {     
       int NB[]=new int[6];
       System.out.println("     "+(char)4+" INTRODUIRE LES 6 NOMBRES DU BILLET : ");
       for(int i=0;i<6;i++)
       {
           System.out.print("     "+(char)4+" BILLET["+i+"] = ");
           NB[i]=Lire.Entier();
       } 
       System.out.println("     "+(char)4+" LE JEU EST OUVERT => BILLET DEPOSE ");
       Solde = Solde - 20 ;
       ClientSocket.ENVOYER (Protocole_COM.DEPOSER_BILLET);
       for(int i=0;i<6;i++)
       ClientSocket.ENVOYER (""+NB[i]);
      }
      else
      {
       System.out.println("     "+(char)4+" IMPOSSIBLE : VOUS NE POUVEZ PAS ACHETER UN BILLET [ 20 $ ]");       
      }  
     }
     else{
      System.out.println("     "+(char)4+" IMPOSSIBLE : LE JEU EST FERMER ");
     }
   }
   else
   {
     System.out.println("     "+(char)26+" IMPOSSIBLE : VOUS ETES DECONNECTER ... ");
   }
   }
   //===================================================================
   public void ENV_MESSAGE(String MSG)   
   { 
   if(CONNEXION)
   { 
     ClientSocket.ENVOYER ( MSG ) ;
   }
   else
   {
     System.out.println("     "+(char)26+" IMPOSSIBLE : VOUS ETES DECONNECTER AVEC LE SERVEUR ... ");
   }
   }
   //===================================================================
   public void CONNEX_DECONNEX()
   {  
     CONNEXION = ClientSocket.CONNEX_DECONNEX ( CONNEXION ) ;
   }
   //===================================================================
    Thread_Gestion_Option_Client () throws Exception
    {
     try{
       ClientSocket = new Thread_Socket_Client(this);
       ClientSocket.start();
     }
     catch(Exception e)
     {
       System.out.println("   IMPOSSIBLE DE LANCER LE CLIENT ");        
     }  
    }

    public void run ()
    {
     P_ID_CLIENT();
     String Touche;
     while(!Fin)
     {
      int Choix = CHOIX();
      
      switch(Choix){
      case 1 : CONNEX_DECONNEX(); break; 
      case 2 : ACHETER_UN_BILLET();break; 
      case 3 : ENV_MESSAGE();break;
      case 4 : A_PROPOS();break;
      case 5 : 
               if(CONNEXION) ClientSocket.ENVOYER(Protocole_COM.DECONNEXION_CLIENT);
               System.out.print ("     "+(char)4+" FERMETURE DU CLIENT");
               try{
               for(int i=3;i>=0;i--)
               {
                Thread.sleep(1000);
                System.out.print (" "+i);
               }
               }
               catch(Exception e)
               {
               
               }  
               System.exit(0); 
               break;
      }
      }
     }
}


