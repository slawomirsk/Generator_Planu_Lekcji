/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mgr;

import java.util.Random;

/**
 *
 * @author slawek
 */
public class Algorytmy_optymalizacyjne {
    public static plan Optymalizacja_MC(int ilosc_prob,przydzial p, int c,int cd, int  s, double u,double o, double z,boolean[][] dn, boolean[][] ds)
    {
       
        
        plan pl;
        boolean w1;
            do{
                przydzial pi1=new przydzial(p.pi);
                for(int j=0;j<p.pi;j++)
                {
                    pi1.dodaj(new element_przydzialu(p.elementy_przydzialu[j].nauczyciel, p.elementy_przydzialu[j].przedmiot, p.elementy_przydzialu[j].klasa));
                }
                pl= new plan(pi1, c, cd, s);
                pl.ustaw_DN(dn);
                pl.ustaw_DS(ds);
                w1=pl.generuj_plan_MC();
                //System.out.println(w);
            }while(w1==false);
        double f=pl.funkcja_celu(u,o,z);
        //System.out.println("f"+f);
        
        plan pl2;
        double f2;
        for(int i=0;i<ilosc_prob;i++)
            {
                boolean w;
                do{
                przydzial pi=new przydzial(p.pi);
                pi=new przydzial(p.pi);
                for(int j=0;j<p.pi;j++)
                {
                    pi.dodaj(new element_przydzialu(p.elementy_przydzialu[j].nauczyciel, p.elementy_przydzialu[j].przedmiot, p.elementy_przydzialu[j].klasa));
                }
                pl2=new plan(pi,c,cd,s);
                pl2.ustaw_DN(dn);
                pl2.ustaw_DS(ds);
                w=pl2.generuj_plan_MC();
                //System.out.println("wynik="+w);
                }while(w==false);
                f2=pl2.funkcja_celu(u,o,z);
                
                //System.out.println(f2);
                if(f2<f)
                {
                    pl=pl2;
                    f=f2;
                }
                
            }
        return pl;
    }

    public static plan Optymalizacja_zachlanna(przydzial p, int c,int cd, int  s, double u,double o, double z, boolean[][] dn, boolean[][] ds)
    {
        plan pl=new plan(p,c,cd,s);
        pl.ustaw_DN(dn);
        pl.ustaw_DS(ds);
        if(pl.warunki_istnienia()==true)
        {
            pl.generuj_plan_rekurencyjniejZ();
        }
        else
        {
            System.out.println("Nie można wygenerować planu");
        }
        return pl;
    }
    public static plan Optymalizacja_genetyczna(int liczebnosc_populacji, int liczba_prob,przydzial p, int c,int cd, int  s, double u,double o, double z, int krzyzowanie,boolean[][] dn, boolean[][] ds) //krzyzowanie=1 lub 2
    {
        plan wynik =new plan(p, c, cd, s);
        //inicjalizacja
        plan[] populacja=new plan[liczebnosc_populacji];
        double[] wartosc_f=new double[liczebnosc_populacji];
        double[] prawy_koniec_przedzialu=new double[liczebnosc_populacji];
        for(int i=0;i<liczebnosc_populacji;i++)
        {
            boolean w;
            do{
                przydzial pi=new przydzial(p.pi);
                for(int j=0;j<p.pi;j++)
                {
                    pi.dodaj(new element_przydzialu(p.elementy_przydzialu[j].nauczyciel, p.elementy_przydzialu[j].przedmiot, p.elementy_przydzialu[j].klasa));
                }
                populacja[i]= new plan(pi, c, cd, s);
                populacja[i].ustaw_DN(dn);
                populacja[i].ustaw_DS(ds);
                w=populacja[i].generuj_plan_MC();
                //System.out.println(w);
            }while(w==false);
            wartosc_f[i]=populacja[i].funkcja_celu(u, o, z);
        }
        for(int i=0;i<liczba_prob;i++)
        {  // System.out.println("-------------------");
            //System.out.println("proba numer "+i);
            for(int k=0;k<liczebnosc_populacji;k++)
            {
                //System.out.println("dla k="+k);
                wartosc_f[k]=populacja[k].funkcja_celu(u, o, z);
            }
            for(int k=0;k<liczebnosc_populacji-1;k++)
            {
                for(int l=0;l<liczebnosc_populacji-1-k;l++)
                    if(wartosc_f[l]>wartosc_f[l+1])
                    {
                        double f=wartosc_f[l];
                        wartosc_f[l]=wartosc_f[l+1];
                        wartosc_f[l+1]=f;
                        plan pl=populacja[l];
                        populacja[l]=populacja[l+1];
                        populacja[l+1]=populacja[l];
                    }
            }
            
//            elitaryzm
            plan[] nowa_populacja=new plan[liczebnosc_populacji];
            for(int k=0;k<5;k++)
            {
                nowa_populacja[k]=populacja[k];
            }
//            selekcja
            prawy_koniec_przedzialu[0]=wartosc_f[0];
            for(int k=1;k<liczebnosc_populacji;k++)
            {
                prawy_koniec_przedzialu[k]=prawy_koniec_przedzialu[k-1]+wartosc_f[k];
            }
            for(int k=5;k<liczebnosc_populacji;k++)
            {
                plan A;
                Random r=new Random();
                double x=r.nextDouble();
                x=x%wartosc_f[liczebnosc_populacji-1];
                int wskA=0;
                while(wartosc_f[wskA]<x)
                {
                    wskA++;
                }
                A=populacja[wskA];
                plan B;
                x=r.nextDouble();
                x=x%wartosc_f[liczebnosc_populacji-1];
                int wskB=0;
                while(wartosc_f[wskB]<x)
                {
                    wskB++;
                }
                B=populacja[wskB];
    //            krzyzowanie
                //System.out.println(krzyzowanie==1);
                if(krzyzowanie==1)
                {
                    nowa_populacja[k]=A.krzyzowanieI(B, dn,  ds);
                    try
                    {
                        int spr=nowa_populacja[k].lekcje[nowa_populacja[k].lekcje.length-1].el.nauczyciel;
                    }
                    catch(NullPointerException e)
                    {
                       boolean w;
                       do{
                            przydzial pi=new przydzial(p.pi);
                            for(int j=0;j<p.pi;j++)
                            {
                                pi.dodaj(new element_przydzialu(p.elementy_przydzialu[j].nauczyciel, p.elementy_przydzialu[j].przedmiot, p.elementy_przydzialu[j].klasa));
                            }
                            nowa_populacja[k]= new plan(pi, c, cd, s);
                            w=nowa_populacja[k].generuj_plan_MC();
                       }while(w==false);
                    }
                }
                if(krzyzowanie==2)
                {
                    nowa_populacja[k]=A.krzyzowanieII(B, dn, ds);
                    try
                    {
                        int spr=nowa_populacja[k].lekcje[nowa_populacja[k].lekcje.length-1].el.nauczyciel;
                    }
                    catch(NullPointerException e)
                    {
                       boolean w;
                       do{
                            przydzial pi=new przydzial(p.pi);
                            for(int j=0;j<p.pi;j++)
                            {
                                pi.dodaj(new element_przydzialu(p.elementy_przydzialu[j].nauczyciel, p.elementy_przydzialu[j].przedmiot, p.elementy_przydzialu[j].klasa));
                            }
                            nowa_populacja[k]= new plan(pi, c, cd, s);
                            w=nowa_populacja[k].generuj_plan_MC();
                       }while(w==false);
                    }
                    
                }
                    
            }
            populacja=nowa_populacja;      
        }
        //
       //ostaterczne sortowanie
        for(int k=0;k<liczebnosc_populacji;k++)
            {
                wartosc_f[k]=populacja[k].funkcja_celu(u, o, z);
            }
            for(int k=0;k<liczebnosc_populacji-1;k++)
            {
                for(int l=0;l<liczebnosc_populacji-1-k;l++)
                    if(wartosc_f[l]>wartosc_f[l+1])
                    {
                        double f=wartosc_f[l];
                        wartosc_f[l]=wartosc_f[l+1];
                        wartosc_f[l+1]=f;
                        plan pl=populacja[l];
                        populacja[l]=populacja[l+1];
                        populacja[l+1]=populacja[l];
                    }
            }
        return populacja[0];
    }
}
