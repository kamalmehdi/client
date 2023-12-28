import java.io.*;

public class Lire 
{
   public static String Chaine() {
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      String S = ""; 
      try  { 
          S = input.readLine();
      } 
      catch (Exception e) { 
          e.printStackTrace(); 
      } 
      return S; 
   }
   public static int Entier() {
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      int i = 0; 
      try  { 
          i = Integer.parseInt(input.readLine());
      } 
      catch (Exception e) { 
          System.out.println("     "+(char)4+" UNE VALEUR ENTIERE S.V.P [ FORMAT ENTIERE ]");
      } 
      return i; 
   }
   public static double Reel() {
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      double i=0.0; 
      try  { 
          i = Double.valueOf(input.readLine()).doubleValue();
      } 
      catch (Exception e) { 
          System.out.println("     "+(char)4+" UNE VALEUR Reel S.V.P [ FORMAT Reel - Double ]");
      } 
      return i; 
   }
   
   /*
   public static String chaine() throws Exception
    {
	    String tmp = "";
	    char C='\0';
		try 
		{
		  C=(char) System.in.read();
			while ( C!='\n' )
			{
				if (C != '\r')  tmp = tmp+C;
		    C=(char) System.in.read();
			}
		}
		catch (IOException e)
		{
			System.out.println("Erreur de frappe");
			System.exit(0);
		}
		return tmp;
    } 

	// Cette fonction permet de saisir au clavier une variable entiére de type byte
	public static byte entierByte() throws Exception 
	{
		byte x=0;
		try 
		{
			x = Byte.parseByte(chaine());
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Format numérique incorrect");
			System.exit(0);
		}	
		return x ;
	}

	// Cette fonction permet de saisir au clavier une variable entiére de type Short
	public static short entierShort() throws Exception
	{
		short x=0;
		try 
		{
			x=Short.parseShort(chaine());
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Format numérique incorrect");
			System.exit(0);
		}	
		return x ;
	 }

	// Cette fonction permet de saisir au clavier une variable entiére de type int
	public static int entierInt()  throws Exception
	{
		int x=0;
		try 
		{
			x=Integer.parseInt(chaine());
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Format numérique incorrect");
			System.exit(0);
		}	
		return x ;
	 }
	
	// Cette fonction permet de saisir au clavier une variable entiére de type long
	public static long entierLong() throws Exception
	{
		long x=0;
		try 
		{
			x=Integer.parseInt(chaine());
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Format numérique incorrect");
			System.exit(0);
		}	
		return x ;
	 }

	 // Cette fonction permet de saisir au clavier une variable  réelle  double
	public  static double reelDouble() throws Exception
	{
		double x=0.0;
		try 
		{
			x=Double.valueOf(chaine()).doubleValue();
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Format numérique incorrect");
			System.exit(0);
		}	
		return x ;
	 }
	 
	// Cette fonction permet de saisir au clavier une variable  réelle  float
	public  static float reelFloat() throws Exception
	{
		float x=0.0f;
		try 
		{
			x=Double.valueOf(chaine()).floatValue();
		}
		catch (NumberFormatException e) 
		{
			System.out.println("Format numérique incorrect");
			System.exit(0);
		}	
		return x ;
	}
	
	// Cette fonction permet de saisir au clavier une variable  de type char
	public  static char caractere()  throws Exception
	{
		String tmp=chaine();
		if (tmp.length()==0)
		{
			return '\n';
		}
		else 
		{
			return tmp.charAt(0);
		}
	}*/
}
