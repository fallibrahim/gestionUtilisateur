package com.composition.agregation.association;
import java.util.Scanner;
class salle
{
    int num;
  public salle(int n)
  {
      this.num = n;
  }
  public void afficher()
  {
      System.out.println(num);
  }
}

class cinema
{
   String nom;
   public salle [] tabSalle  = new salle[2];
   
   public cinema(String n)
   {
       this.nom = n;
       
        for(int i = 0; i < 2; i++)
        tabSalle[i] = new salle(i);
   }
  public void afficher()
  {
      System.out.println("le cinéma "+this.nom+" est composé les salles suivantes :");
        for(int i = 0; i < 2; i++)
        System.out.println("salle numéro "+tabSalle[i].num);
  }
}

class cinema2
{
    String nom;
    public salle [] tabSalle  = new salle[2];
    public cinema2(String n, salle T[])
    {
        this.nom = n;
        for(int i = 0; i < 2; i++)
        tabSalle[i] = T[i];
    }
      public void afficher()
  {
      System.out.println("le cinéma "+this.nom+" est composé les salles suivantes :");
      for(int i = 0; i < 2; i++)
      System.out.println("salle numéro "+tabSalle[i].num);
  }
}
class Film
{
  String titre; 
  public Film(String t)
  {
      this.titre = t;
  }
  public  void afficher ()
  {
      System.out.println("le film s'appelle : "+this.titre);
  }
}

class Projection
{
    public int heure;
	public salle Tsalle[] = new salle[2];
	public Film Tfilm[] = new Film[2];
	
	public Projection (int h, salle Ts[], Film Tf[])
	{
	   this.heure = h;
	   for (int i = 0; i < 2; i++)
	   {
	       this.Tsalle[i] = Ts[i];
	       this.Tfilm[i] = Tf[i];
	   }
	}
}
public class Main {

	public static void main(String[] args) {
	  salle s = new salle(1);
	  s.afficher();
	  cinema c = new cinema("unité 3");
	  c.afficher();
	  salle T[] = new salle[2];
	  for(int i = 0; i < 2; i++)
	  T[i] = new salle(i);
	  cinema2 c2 = new cinema2("MEDINA", T);
	  c2.afficher();
     Film f = new Film("VIKING");
     f.afficher();
       salle Ts[] = new salle[2];
	  for(int i = 0; i < 2; i++)
	  Ts[i] = new salle(i);
	
	    Film Tf[] = new Film[2];
	    for (int i=0; i<2;i++)
	    {
	      String t;
	      Scanner sc = new Scanner(System.in);
	      t = sc.nextLine();
	      Tf[i] = new Film(t);
	      
	    }
	      Projection p = new Projection(14,Ts,Tf);
	      System.out.println("on va projeter "+p.Tfilm[0].titre+" dans la salle "+p.Tsalle[1].num);
	}

}
