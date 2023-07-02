/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author slawek
 */
public class plan {
    public przydzial Pi;// zmień na private
    int c;
    int cd;//czasy w ciągu jednego dnia;
    int s;
    int n;
    int k;
    int p;
    boolean[][] DN;//n na c
    boolean[][] DS;// s na c
    boolean[][] DK;// k na c
    lekcja[] lekcje;
    int zapis_lekcji;
    public plan(przydzial p, int c,int cd, int  s)
    {
        this.cd=cd;
        this.zapis_lekcji=0;
        Pi=new przydzial(p.pi);
                for(int j=0;j<p.pi;j++)
                {
                    Pi.dodaj(new element_przydzialu(p.elementy_przydzialu[j].nauczyciel, p.elementy_przydzialu[j].przedmiot, p.elementy_przydzialu[j].klasa));
                }
        
        this.Pi=Pi;
        this.s=s+1;//dodanie pozornej sali
        this.c=c;
        this.n=Pi.liczba_nauczycieli()+1;//dodanie pozornego nauczyciela
        //System.out.println("mam n="+this.n);
        this.p=Pi.liczba_przedmiotow()+1;//dodanie pozornego przedmiotu
        this.k=Pi.liczba_klas();           
        this.DN=new boolean[this.n][this.c];
        for(int i=0;i<this.n;i++)
        {
            for(int j=0;j<this.c;j++)
            {
                DN[i][j]=true;
            }
        }
        this.DS=new boolean[this.s][this.c];
        for(int i=0;i<this.s;i++)
        {
            for(int j=0;j<this.c;j++)
            {
                DS[i][j]=true;
            }
        }
        this.DK=new boolean[this.k][this.c];
        for(int i=0;i<this.k;i++)
        {
            for(int j=0;j<this.c;j++)
            {
                DK[i][j]=true;
            }
        }
        lekcje=new lekcja[Pi.pi];
    }
    public boolean warunki_istnienia()
    {
        //System.out.println(warunekI()+" "+warunekII()+" "+warunekIII());
        return warunekI()&&warunekII()&&warunekIII();
    }
    public boolean warunekI()//
    {
       int sum=0;
        for(int j=0;j<c;j++)
        {
           sum=sum+Math.min(k,
                    Math.min(this.Liczba_Wolnych_Nauczycieli_w_Czasie(j),
                             this.Liczba_Wolnych_Sal_w_Czasie(j)));
        }
        if(sum>=this.Pi.liczba_lekcji_w_przydziale())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean warunekII()//liczba lekcji nauczyciela<=godziny dyspozycyjności nauczyciela
    {
        boolean wynik=true;
        int t=1;
        while(t<n)
        {
            if(this.Pi.liczba_lekcji_nauczyciela(t)<=
               this.Liczba_Wolnych_Godzin_Nauczyciela(t))
            {
                t++;
            }
            else
            {
                t=n;
                wynik=false;
            }
        }
        return wynik;
    }
    public boolean warunekIII()//liczba lekcji wdanej klasy <= c
    {
        boolean wynik=true;
        int kl=0;
        while(kl<k)
        {
            if(this.Pi.liczba_lekcji_klasy(kl)<=this.c)
            {
                kl++;
            }
            else
            {
                kl=k;
                wynik=false;
            }
        }
        return wynik;
    }
    
    public boolean warunekTrywialny()
    {
        int  licznik=0;
        for(int i=1;i<DS.length;i++)
        {
            for(int j=0;j<DS[0].length;j++)
            {
                if(DS[i][j]==true)
                {
                    licznik++;
                }
            }
        }
        if(licznik<=this.Pi.miejsce_zapisu-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int Liczba_Wolnych_Godzin_Nauczyciela(int n)
    {
        int wynik=0;
        for(int i=0;i<c;i++)
        {
            if(DN[n][i]==true)
            {
                wynik++;
            }
        }
        return wynik;
    }
    public int Liczba_Wolnych_Nauczycieli_w_Czasie(int c)
    {
        int wynik=0;
        for(int i=1;i<n;i++)
        {
            if(DN[i][c]==true)
            {
                wynik++;
            }
        }
        
        return wynik;
    }
    public int Liczba_Wolnych_Sal_w_Czasie(int c)
    {
        int wynik=0;
        for(int i=1;i<s;i++)
        {
            if(DS[i][c]==true)
            {
                wynik++;
            }
        }
        return wynik;
    }
    public void ustaw_DN(boolean[][] dn)
    {
        if((dn.length==n)&&(dn[0].length==c))
                {
                    for(int i=0;i<n;i++)
                    {
                        for(int j=0;j<c;j++)
                        {
                            if(dn[i][j]==true)
                            DN[i][j]=true;
                            else
                            DN[i][j]=false;  
                        }
                    }
                }
    }
    
    public void ustaw_DS(boolean[][] ds)
    {
        if((ds.length==s)&&(ds[0].length==c))
                {
                    for(int i=0;i<s;i++)
                    {
                        for(int j=0;j<c;j++)
                        {
                            if(ds[i][j]==true)
                            DS[i][j]=true;
                            else
                            DS[i][j]=false;
                        }
                    }
                }
    }
    public void generuj_plan_rekurencyjniej()
    {
        //System.out.println("dzialamn z "+this.Pi.liczba_lekcji_w_przydziale()+" lekcjami");
        element_przydzialu el=this.Pi.pobierz_element();
        List<element_przydzialu> wyprobowane_elementy=new ArrayList<element_przydzialu>();
        if((el.nauczyciel==0)&&(el.przedmiot==0))
        {
            //wypisz plan poniewąz skończył się przydział
        }
        else
        {
            //tworzenie planu
            int i=0;
            while (i<this.Pi.pi)
            if(this.dodaj_lekcje(el)==true)
            {
                for(element_przydzialu wyp_el:wyprobowane_elementy)
                {
                    this.Pi.dodaj(el);
                }
                if(this.warunki_istnienia()==true)
                {
                    this.generuj_plan_rekurencyjniej();
                    i=this.Pi.pi;
                }
                else
                {
                    //System.out.println("Nie można utworzyć planu");
                    i=this.Pi.pi;
                }
            }
            else
            {
                element_przydzialu el2= new element_przydzialu(el.nauczyciel, el.przedmiot, el.klasa);
                wyprobowane_elementy.add(el2);
                el=this.Pi.pobierz_element();
                i++;
            }
            
            
        }
        
    }
    public boolean generuj_plan_rekurencyjniej_MC()
    {
        //System.out.println("dzialamn z "+this.Pi.liczba_lekcji_w_przydziale()+" lekcjami");
        element_przydzialu el=this.Pi.pobierz_element();
        List<element_przydzialu> wyprobowane_elementy=new ArrayList<element_przydzialu>();
        if((el.nauczyciel==0)&&(el.przedmiot==0))
        {
            return true;
        }
        else
        {
            //tworzenie planu
            int i=0;
            while (i<this.Pi.pi)
            if(this.dodaj_lekcje_MC(el)==true)
            {
                for(element_przydzialu wyp_el:wyprobowane_elementy)
                {
                    this.Pi.dodaj(el);
                }
                if(this.warunki_istnienia()==true)
                {
                    
                    i=this.Pi.pi;
                     return this.generuj_plan_rekurencyjniej_MC();
                }
                else
                {
                   // System.out.println("Nie można utworzyć planu");
                    i=this.Pi.pi;
                    return false;
                }
            }
            else
            {
                //System.out.println("nie dodałem mc");
                element_przydzialu el2= new element_przydzialu(el.nauczyciel, el.przedmiot, el.klasa);
                wyprobowane_elementy.add(el2);
                el=this.Pi.pobierz_element();
                i++;
                return false;
            }
            
            
        }
     return false;   
    }
    public void generuj_plan()
    {
        if(this.warunki_istnienia()==true)
        {
            this.generuj_plan_rekurencyjniej();
        }
        else
        {
            System.out.println("Nie można wygenerować planu");
        }
            
    }
    public boolean generuj_plan_MC()
    {
        if(this.warunki_istnienia()==true)
        {
            return this.generuj_plan_rekurencyjniej_MC();
        }
        else
        {
            System.out.println("Nie można wygenerować planu");
            return false;
        }
            
    }
    public boolean dodaj_lekcje(element_przydzialu el) //true gdy można dodac element false gdy nie
    {
        if((el.nauczyciel==0)&(el.przedmiot==0))
        {
            System.out.println("Nie dodaję pustych lekcji");
            return false;
        }
        else
        {
            int i=1; // dla 0 dostaniemy pozorną salę lub pozornego nauczyciela
            int j=0;
            while (i<s)
            {
                //System.out.println("dzialam dla sali "  + i+" sal mam "+ DS.length);
                j=0;
                while (j<c)
                {
                    if((DN[el.nauczyciel][j]==true)&(DK[el.klasa-1][j]==true)&(DS[i][j]==true))
                    {
                        lekcje[zapis_lekcji]=new lekcja(el, j, i);
                        zapis_lekcji++;
                        DN[el.nauczyciel][j]=false;
                        DS[i][j]=false;
                        DK[el.klasa-1][j]=false;
                        //System.out.println("n "+el.nauczyciel+" k "+el.klasa+" p "+
                         //       el.przedmiot+" c "+j+ " s "+ i);
                        return true;
                    }
                j++;
                }
                i++;
            }
            System.out.println("Nie można dopisać lekcji");
            return false;
        }
    }
    public boolean dodaj_lekcje_MC(element_przydzialu el) //true gdy można dodac element false gdy nie
    {
        if((el.nauczyciel==0)&(el.przedmiot==0))
        {
            System.out.println("Nie dodaję pustych lekcji ");
            return false;
        }
        else
        {
            List<Integer> ListaCzasow = new ArrayList<Integer>();
            for(int i=0;i<c;i++)
            {
                if((DN[el.nauczyciel][i]==true)&&(DK[el.klasa-1][i]==true))
                {
                    ListaCzasow.add(i);
                }
            }
            Random r= new Random();
            while(ListaCzasow.isEmpty()==false)
            {
                int j=ListaCzasow.get(r.nextInt(ListaCzasow.size()));
                ListaCzasow.remove(ListaCzasow.indexOf(j));
                List<Integer> ListaSal = new ArrayList<Integer>();
                for(int i=1;i<s;i++)
                {
                    if(DS[i][j]=true)
                    {
                        ListaSal.add(i);
                    }
                }
                
                if(ListaSal.isEmpty()==false)
                {
                    int l=ListaSal.get(r.nextInt(ListaSal.size()));
                    lekcje[zapis_lekcji]=new lekcja(new element_przydzialu(el.nauczyciel, el.przedmiot, el.klasa), j, l);
                    zapis_lekcji++;
                    DN[el.nauczyciel][j]=false;
                    DS[l][j]=false;
                    DK[el.klasa-1][j]=false;
                    //System.out.println("n "+el.nauczyciel+" k "+el.klasa+" p "+
                     //           el.przedmiot+" c "+j+ " s "+ l);
                   // System.out.println("sukces");
                    return true;
                    
                }
                else
                {
                    return false;
                }
                
            }
        }
        return false;
    }
    public int LiczbaWolnychLekcji(int n)//liczb lekcji do przydzielenia
    {
        int wynik=0;
        element_przydzialu el;
        for(int i=0;i<this.Pi.miejsce_zapisu;i++)
        {
            el=this.Pi.czytaj_element(i);
            if(el.nauczyciel==n)
                wynik++;
        }
        return wynik;
    }
    public int LiczbaWolnychLekcjiKlasy(int k)//liczb lekcji do przydzielenia
    {
        int wynik=0;
        element_przydzialu el;
        for(int i=0;i<this.Pi.miejsce_zapisu;i++)
        {
            el=this.Pi.czytaj_element(i);
            if(el.klasa==k-1)
                wynik++;
        }
        return wynik;
    }
    public int LiczbaWolnychCzasow(int n)
    {
        int wynik =0;
        for(int i=0;i<c;i++)
        {
            if(DN[n][i]==true)
                wynik++;
        }
        return wynik;
    }
    public int LiczbaWolnychCzasowKlasy(int q)
    {
        int wynik =0;
        
        for(int i=0;i<c;i++)
        {
            if(DK[q-1][i]==true)
                wynik++;
        }
        return wynik;
    }
    
    public void generuj_plan_rekurencyjniejD()
    {
        if(this.Pi.miejsce_zapisu>0)
            {
                this.sortuj_wedlug_dyspozycji();
            }
        element_przydzialu el=this.Pi.pobierz_element();
        List<element_przydzialu> wyprobowane_elementy=new ArrayList<element_przydzialu>();
        if((el.nauczyciel==0)&&(el.przedmiot==0))
        {
            //wypisz plan poniewąz skończył się przydział
        }
        else
        {
            //tworzenie planu
            
            int i=0;
            while (i<this.Pi.pi)
            if(this.dodaj_lekcje(el)==true)
            {
                for(element_przydzialu wyp_el:wyprobowane_elementy)
                {
                    this.Pi.dodaj(el);
                }
                if(this.warunki_istnienia()==true)
                {
                    this.generuj_plan_rekurencyjniejD();
                    i=this.Pi.pi;
                }
                else
                {
                    System.out.println("Nie można utworzyć planu");
                    i=this.Pi.pi;
                }
            }
            else
            {
                element_przydzialu el2= new element_przydzialu(el.nauczyciel, el.przedmiot, el.klasa);
                wyprobowane_elementy.add(el2);
                el=this.Pi.pobierz_element();
                i++;
            }
            
            
        }
        
    }
    public void generuj_plan_rekurencyjniejZ()
    {
        //uszeregowanie według dyspozycyjności
       int[] LiczbaWolnychLekcji=new int[this.n];
       int[] LiczbaWolnychCzasow=new int[this.n];
       double[][] dyspozycyjnosc=new double[this.n][2];
       for(int i=1;i<this.n;i++)
       {
           LiczbaWolnychLekcji[i]=this.LiczbaWolnychLekcji(i);
           LiczbaWolnychCzasow[i]=this.LiczbaWolnychCzasow(i);
           dyspozycyjnosc[i][0]=i;
       }
       for(int i=1;i<dyspozycyjnosc.length;i++)
       {
           dyspozycyjnosc[i][1]=(1.0*LiczbaWolnychLekcji[i])/LiczbaWolnychCzasow[i];
       }
       double d=0;
       for(int i=1;i<dyspozycyjnosc.length-1;i++)
       {
           for(int j=1;j<dyspozycyjnosc.length-1-i;j++)
           {
               if(dyspozycyjnosc[j][1]<dyspozycyjnosc[j+1][1])
               {
                  d=dyspozycyjnosc[j][0];
                  dyspozycyjnosc[j][0]=dyspozycyjnosc[i][0];
                  dyspozycyjnosc[i][0]=d;
                  d=dyspozycyjnosc[j][1];
                  dyspozycyjnosc[j][1]=dyspozycyjnosc[i][1];
                  dyspozycyjnosc[i][1]=d;
               }
           }   
       }
       //szukanie klas dla każdego nauczyciela
      for(int i=1;i<this.n;i++)
      {
          //wyszukaj lekcje i-tego nauczyciela
          element_przydzialu[] lekcje_nauczyciela=new element_przydzialu[LiczbaWolnychLekcji(i)];
          double[] wsp_klasy=new double[LiczbaWolnychLekcji(i)];
          int j=0;
          for(int l=0;l<this.Pi.pi;l++)
          {
              if(this.Pi.elementy_przydzialu[l].nauczyciel==i)
              {
                  lekcje_nauczyciela[j]=new element_przydzialu(this.Pi.elementy_przydzialu[l].nauczyciel, this.Pi.elementy_przydzialu[l].przedmiot, this.Pi.elementy_przydzialu[l].klasa);
                  wsp_klasy[j]=(1.0*LiczbaWolnychLekcjiKlasy(this.Pi.elementy_przydzialu[l].klasa))/LiczbaWolnychCzasowKlasy(this.Pi.elementy_przydzialu[l].klasa);
                  j++;
              }
          }
          //posortuj lekcje wzgledem wsp klas
          double wk;
          element_przydzialu el=new element_przydzialu(0, 0, 0);
          for(int k=0;k<lekcje_nauczyciela.length-1;k++)
          {
           for(int l=0;l<lekcje_nauczyciela.length-1-k;l++)
           {
               if(wsp_klasy[l]<wsp_klasy[l+1])
               {
                   wk=wsp_klasy[l];
                   wsp_klasy[l]=wsp_klasy[l+1];
                   wsp_klasy[l+1]=wk;
                   el=lekcje_nauczyciela[l+1];
                   lekcje_nauczyciela[l+1]=lekcje_nauczyciela[l];
                   lekcje_nauczyciela[l+1]=el;
               }
            }   
           }
          for(int l=0;l<lekcje_nauczyciela.length;l++)
          {
           //dla kadej lekcji szukaj czasy dn=t i dk=t
              List<Integer> czasy=new ArrayList<Integer>();
              for(int sc=0;sc<c;sc++)
              {
                  if((DN[i][sc]==true)&&(DK[lekcje_nauczyciela[l].klasa-1][sc]==true))
                  {
                      czasy.add(sc);
                  }
                  
              }
              if(czasy.size()==0)
              {
              System.out.println("Błąd: nie można utworzyć planu: brak czasu dla lekcji:");
              break;
              }
              else
              {
               //szukaj max wsp czasu
                  double w=wsp_czsu(czasy.get(0),i,lekcje_nauczyciela[l].klasa);
                  double w2;
                  int dodawany_czas=czasy.get(0);
                  for(int m=1;m<czasy.size();m++)
                  {
                      //System.out.println(m+" "+czasy.size() );
                      w2=wsp_czsu(czasy.get(m),i,lekcje_nauczyciela[l].klasa);
                      if(w2>w)
                      {
                          dodawany_czas=czasy.get(m);
                      }
                  }
               //szukanie sal
                  List<Integer> sale=new ArrayList<Integer>();
                  for(int ss=0;ss<s;ss++)
                  {
                      if(DS[ss][dodawany_czas]==true)
                      {
                          sale.add(ss);
                      }

                  }
                  if(sale.size()==0)
                  {
                      System.out.println("Błąd: nie można utworzyć planu: brak sali dla lekcji");
                      break;
                  }
                  else
                  {
                      
                  //wyszukanie sali z max wsp sali 
                      double ws=wsp_sali(sale.get(0),i,dodawany_czas);
                      double ws2;
                      int dodawana_sala=sale.get(0);
                      for(int m=1;m<sale.size();m++)
                      {
                          ws2=wsp_sali(sale.get(m),i,dodawany_czas);
                          if(ws2>ws)
                          {
                              dodawana_sala=sale.get(m);
                          }
                      }
               //przydziel lekcje
                      lekcje[zapis_lekcji]=new lekcja(new element_przydzialu(lekcje_nauczyciela[l].nauczyciel,lekcje_nauczyciela[l].przedmiot,lekcje_nauczyciela[l].klasa),dodawany_czas,dodawana_sala);
                        zapis_lekcji++;
                        DN[i][dodawany_czas]=false;
                        DS[dodawana_sala][dodawany_czas]=false;
                        DK[lekcje_nauczyciela[l].klasa-1][dodawany_czas]=false;
                        
                  }
              }
          }
      }
       
       
    }
    public void generuj_planD()
    {
        if(this.warunki_istnienia()==true)
        {
            this.generuj_plan_rekurencyjniejD();
        }
        else
        {
            System.out.println("Nie można wygenerować planu");
        }
            
    }
    public void sortuj_wedlug_dyspozycji()
    {
       
       int[] LiczbaWolnychLekcji=new int[this.n];
       int[] LiczbaWolnychCzasow=new int[this.n];
       double[] dyspozycyjnosc=new double[this.Pi.miejsce_zapisu-1];
       for(int i=0;i<this.n;i++)
       {
           LiczbaWolnychLekcji[i]=this.LiczbaWolnychLekcji(i);
           LiczbaWolnychCzasow[i]=this.LiczbaWolnychCzasow(i);
       }
       System.out.println("l="+this.Pi.elementy_przydzialu.length);
       for(int i=0;i<dyspozycyjnosc.length;i++)
       {
           
           dyspozycyjnosc[i]=(1.0*LiczbaWolnychLekcji[this.Pi.elementy_przydzialu[i].nauczyciel])/LiczbaWolnychCzasow[this.Pi.elementy_przydzialu[i].nauczyciel];
       }
       //sortowanie bąbelki
       element_przydzialu el;
       double d;
       for(int i=0;i<dyspozycyjnosc.length-1;i++)
       {
           for(int j=0;j<dyspozycyjnosc.length-1-i;j++)
           {
               if(dyspozycyjnosc[j]>dyspozycyjnosc[j+1])
               {
                   el=Pi.elementy_przydzialu[j];
                   d=dyspozycyjnosc[j];
                   Pi.elementy_przydzialu[j]=Pi.elementy_przydzialu[j+1];
                   dyspozycyjnosc[j]=dyspozycyjnosc[j+1];
                   Pi.elementy_przydzialu[j+1]=el;
                   dyspozycyjnosc[j+1]=d;
               }
           }
               
       }
       
       
    }
    public double funkcja_celu(double u,double o, double z)
    {
        return u*this.fs()+o*this.fon()+z*this.fz();
    }
    private double fs()//liczba sal*koszt
    {
        boolean[][] S=new boolean[s][c/cd];
        double wynik=0;
        for(int i=0;i<zapis_lekcji;i++)
        {
            lekcja l=lekcje[i];
            //System.out.println("dla sali "+l.sala+" s="+s);
            S[l.sala][l.czas/cd]=true;
        }
        for(int i=0;i<s;i++)
        {
            for(int j=0;j<c/cd;j++)
            {
                if(S[i][j]==true)
                {
                    wynik++;
                }
            }
        }
        
    return wynik;
    }
    private double fon()
    {   
        double wynik=0;
        for(int i=1;i<n;i++)
        {
            wynik=wynik+this.liczba_okienek_nauczyciela(i);
        }
        return wynik;
    }
     private double fok()
    {   
        double wynik=0;
        for(int i=1;i<k;i++)
        {
            wynik=wynik+this.liczba_okienek_klasy(i);
        }
        return wynik;
    }
    private double fz()
    {
        double wynik=0;
        for(int i=1;i<n;i++)
        {
            wynik=wynik+this.liczba_zmian_nauczyciela(i);
        }
        return wynik;
    }
    
    private int liczba_okienek_nauczyciela(int n)
    {
        int wynik=0;
        boolean[][] tabela_lekcji=new boolean[c/cd][cd];
        boolean czy_mialem_lekcje;
        //System.out.println("mam lekcji podczas błędu="+this.lekcje.length);
        for(lekcja l:lekcje)
        {
            if(l.el.nauczyciel==n)
            {
                tabela_lekcji[l.czas/cd][l.czas%cd]=true;
            }
        }
        //liczenie okienek
        for(int i=0;i<c/cd;i++)
        {
            czy_mialem_lekcje=false;
            int j=0;
            while(j<cd)
            {
                if(tabela_lekcji[i][j]=true)
                {
                    czy_mialem_lekcje=true;
                    j++;
                }
                else
                {
                    if(czy_mialem_lekcje==true)
                    {
                        int licznik=1;
                        while((j+1<cd)&(tabela_lekcji[i][j+1]==false))
                        {
                            licznik++;
                            j++;
                        }
                        if(j+1<cd)
                        {
                            wynik=wynik+licznik;
                        }
                    }
                    j++;
                }
            }
        }
        
        return wynik;
    }
    private int liczba_okienek_klasy(int k)
    {
        int wynik=0;
        boolean[][] tabela_lekcji=new boolean[c/cd][cd];
        boolean czy_mialem_lekcje;
        for(lekcja l:lekcje)
        {
            if(l.el.klasa==k)
            {
                tabela_lekcji[l.czas/cd][l.czas%cd]=true;
            }
        }
        //liczenie okienek
        for(int i=0;i<c/cd;i++)
        {
            czy_mialem_lekcje=false;
            int j=0;
            while(j<cd)
            {
                if(tabela_lekcji[i][j]=true)
                {
                    czy_mialem_lekcje=true;
                    j++;
                }
                else
                {
                    if(czy_mialem_lekcje==true)
                    {
                        int licznik=1;
                        while((j+1<cd)&(tabela_lekcji[i][j+1]==false))
                        {
                            licznik++;
                            j++;
                        }
                        if(j+1<cd)
                        {
                            wynik=wynik+licznik;
                        }
                    }
                    j++;
                }
            }
        }
        
        return wynik;
    }
    
    private int liczba_zmian_nauczyciela(int n)
    {
        int wynik=0;
        int[][] tabela_sal=new int[c/cd][cd];
        for(lekcja l:lekcje)
        {
            if(l.el.nauczyciel==n)
            {
                tabela_sal[l.czas/cd][l.czas%cd]=l.sala;
            }
        }
        for(int i=0;i<c/cd;i++)
        {
            for(int j=0;j+1<cd;j++)
            {
                if((tabela_sal[i][j]!=0)
                  &(tabela_sal[i][j+1]!=0)
                  &(tabela_sal[i][j]!=tabela_sal[i][j]))
                {
                    wynik++;
                }
            }
        }
        return wynik;
    }

    private double wsp_czsu(int cz, int n, int kl) {
        
        //składowa nauczyciela
        double skl_n=0;
        double skl_k=0;
        for(int i=0;i<zapis_lekcji;i++)
        {
            //skl nauczyciela
            if((lekcje[i].czas!=cz)&&(lekcje[i].czas/cd==cz/cd)&&(lekcje[i].el.nauczyciel==n))
            {
                if(skl_n==0)
                {
                    skl_n=0.5;
                }
                skl_n=skl_n+Math.pow(0.5, Math.abs(lekcje[i].czas-cz));
            }
            //skl klasy
            if((lekcje[i].czas!=cz)&&(lekcje[i].czas/cd==cz/cd)&&(lekcje[i].el.klasa==kl))
            {
                if(skl_k==0)
                {
                    skl_k=0.5;
                }
                skl_k=skl_k+Math.pow(0.5, Math.abs(lekcje[i].czas-cz));
            }
        }
        
        return skl_n+2*skl_k;
    }

    private double wsp_sali(int sala, int nauczyciel, int dodawany_czas) {
        double wsp=0;
        for(int i=0;i<zapis_lekcji;i++)
        {
            if((lekcje[i].czas!=dodawany_czas)&&(lekcje[i].sala==sala))
            {
                wsp=0.5;
                if(lekcje[i].czas/cd==dodawany_czas/cd)
                {
                    wsp=1;
                }
            }
        }
        for(int i=0;i<zapis_lekcji;i++)
        {
            if((lekcje[i].czas!=dodawany_czas)&&(lekcje[i].sala==sala)&&(lekcje[i].czas/cd==dodawany_czas/cd))
            {
                wsp=wsp+Math.pow(0.5, Math.abs(lekcje[i].czas-dodawany_czas));
            }
        }
        return wsp;
    }

    public plan krzyzowanieI(plan B,boolean[][] dn, boolean[][] ds) {
        boolean[][] lokalna_dn=new boolean[dn.length][dn[0].length];
        for(int i=0;i<dn.length;i++)
        {
            for(int j=0;j<dn[0].length;j++)
            {
                lokalna_dn[i][j]=dn[i][j];
            }
        }
        boolean[][] lokalna_ds=new boolean[ds.length][ds[0].length];
        for(int i=0;i<ds.length;i++)
        {
            for(int j=0;j<ds[0].length;j++)
            {
                lokalna_ds[i][j]=dn[i][j];
            }
        }
        //krzyżowanie
        element_przydzialu el=new element_przydzialu(0, 0, 0);
        lekcja lekcja_z_B=new lekcja(el, 0, 0);
        przydzial nowy_Pi=new przydzial(Pi.elementy_przydzialu.length);
        for(int i=0;i<this.lekcje.length;i++)
        {
            nowy_Pi.dodaj(new element_przydzialu(this.lekcje[i].el.nauczyciel,
                                                 this.lekcje[i].el.przedmiot,
                                                 this.lekcje[i].el.klasa));
        }
       // System.out.println("nowy pi ma"+nowy_Pi.elementy_przydzialu.length+" nauczycieli"+nowy_Pi.liczba_nauczycieli());
        plan wynik=new plan(nowy_Pi, this.c, this.cd, this.s-1);
        List<element_przydzialu> konflikty=new ArrayList<element_przydzialu>();
        for(int i=0; i<lekcje.length;i++)
        {
            int j=0;
            boolean warunek=((B.lekcje[j].el.nauczyciel==this.lekcje[i].el.nauczyciel)
                              &&(B.lekcje[j].el.klasa==this.lekcje[i].el.klasa)
                              &&(B.lekcje[j].el.przedmiot==this.lekcje[i].el.przedmiot));
            while(!warunek)
            {
                j++;
                warunek=((B.lekcje[j].el.nauczyciel==this.lekcje[i].el.nauczyciel)
                              &&(B.lekcje[j].el.klasa==this.lekcje[i].el.klasa)
                              &&(B.lekcje[j].el.przedmiot==this.lekcje[i].el.przedmiot));
            }
            lekcja_z_B.el.nauczyciel=B.lekcje[j].el.nauczyciel;
            lekcja_z_B.el.klasa=B.lekcje[j].el.klasa;
            lekcja_z_B.el.przedmiot=B.lekcje[j].el.przedmiot;
            lekcja_z_B.czas=B.lekcje[j].czas;
            lekcja_z_B.sala=B.lekcje[j].sala;
            //dodaj lub napraw
//            System.out.println("dzialam z "+ (lekcja_z_B.el.nauczyciel-1)+ " "+wynik.DN.length);
//            System.out.println("DN "+wynik.DN.length+" "+wynik.DN[0].length);
//            System.out.println(lekcja_z_B.el.nauczyciel-1+" "+(this.lekcje[i].czas-1));
//            System.out.println("DK "+wynik.DK.length+" "+wynik.DK[0].length);
//            System.out.println(lekcja_z_B.el.klasa+" "+(this.lekcje[i].czas-1));
//            System.out.println("DS "+wynik.DS.length+" "+wynik.DS[0].length);
//            System.out.println(lekcja_z_B.sala+" "+(this.lekcje[i].czas-1));
//            System.out.println(wynik.DN[lekcja_z_B.el.nauczyciel-1][this.lekcje[i].czas]);
//            System.out.println(wynik.DK[lekcja_z_B.el.klasa-1][this.lekcje[i].czas]);
//            System.out.println(wynik.DS[lekcja_z_B.sala][this.lekcje[i].czas]);       
            if((wynik.DN[lekcja_z_B.el.nauczyciel-1][this.lekcje[i].czas]==false)&&
               (wynik.DK[lekcja_z_B.el.klasa-1][this.lekcje[i].czas]==false)&&
               (wynik.DS[lekcja_z_B.sala][this.lekcje[i].czas]==false))
            {
                lekcja_z_B.czas=this.lekcje[i].czas;
                //wynik.lekcje[i]=lekcja_z_B;
                wynik.lekcje[i]=new lekcja(new element_przydzialu(lekcja_z_B.el.nauczyciel, lekcja_z_B.el.przedmiot, lekcja_z_B.el.klasa),lekcja_z_B.czas, lekcja_z_B.sala);
                    
                wynik.DN[lekcja_z_B.el.nauczyciel-1][this.lekcje[i].czas]=true;
                wynik.DK[lekcja_z_B.el.klasa-1][this.lekcje[i].czas]=true;
                wynik.DS[lekcja_z_B.sala][this.lekcje[i].czas]=true;
            }
            else
            {
                konflikty.add(new element_przydzialu(lekcja_z_B.el.nauczyciel, lekcja_z_B.el.przedmiot, lekcja_z_B.el.klasa));
            }
        }
        for(element_przydzialu kon: konflikty)
            {   boolean w;
                
                w=wynik.dodaj_lekcje_MC(kon);
                //if(w==false)
                  //w=wynik.dodaj_lekcje(kon); 
                
            }
            
        
        return wynik;
    }

    public  plan krzyzowanieII(plan B,boolean[][] dn, boolean[][] ds) {
        boolean[][] lokalna_dn=new boolean[dn.length][dn[0].length];
        for(int i=0;i<dn.length;i++)
        {
            for(int j=0;j<dn[0].length;j++)
            {
                lokalna_dn[i][j]=dn[i][j];
            }
        }
        boolean[][] lokalna_ds=new boolean[ds.length][ds[0].length];
        for(int i=0;i<ds.length;i++)
        {
            for(int j=0;j<ds[0].length;j++)
            {
                lokalna_ds[i][j]=dn[i][j];
            }
        }
        //krzyżowanie
        element_przydzialu el=new element_przydzialu(0, 0, 0);
        element_przydzialu el1=new element_przydzialu(0, 0, 0);
        lekcja lekcja_z_A=new lekcja(el1, 0, 0);
        lekcja lekcja_z_B=new lekcja(el, 0, 0);
        przydzial nowy_Pi=new przydzial(this.lekcje.length);
        
        for(int i=0;i<this.lekcje.length;i++)
        {
            //System.out.println("dodałem "+this.lekcje[i].el.nauczyciel+" "+
           //                                    this.lekcje[i].el.przedmiot+" "+
           //                                  this.lekcje[i].el.klasa);
            nowy_Pi.dodaj(new element_przydzialu(this.lekcje[i].el.nauczyciel,
                                                 this.lekcje[i].el.przedmiot,
                                                 this.lekcje[i].el.klasa));
        }
        //System.out.println("nowy pi  ma " + nowy_Pi.elementy_przydzialu.length+" lekcji");
        plan wynik=new plan(nowy_Pi, this.c, this.cd, this.s-1);
        //System.out.println("przydział ma" + wynik.lekcje.length+" lekcji");
        List<element_przydzialu> konflikty=new ArrayList<element_przydzialu>();
        Random r=new Random();
        for(int i=0; i<lekcje.length;i++)
        {
            int j=0;
            while(!((B.lekcje[j].el.nauczyciel==this.lekcje[i].el.nauczyciel)
                 &&(B.lekcje[j].el.klasa==this.lekcje[i].el.klasa)
                 &&(B.lekcje[j].el.przedmiot==this.lekcje[i].el.przedmiot)))
            {
                j++;
            }
            lekcja_z_A.el.nauczyciel=this.lekcje[i].el.nauczyciel;
            lekcja_z_A.el.klasa=this.lekcje[i].el.klasa;
            lekcja_z_A.el.przedmiot=this.lekcje[i].el.przedmiot;
            lekcja_z_A.czas=this.lekcje[i].czas;
            lekcja_z_A.sala=this.lekcje[i].sala;
            lekcja_z_B.el.nauczyciel=B.lekcje[j].el.nauczyciel;
            lekcja_z_B.el.klasa=B.lekcje[j].el.klasa;
            lekcja_z_B.el.przedmiot=B.lekcje[j].el.przedmiot;
            lekcja_z_B.czas=B.lekcje[j].czas;
            lekcja_z_B.sala=B.lekcje[j].sala;
            //dodaj lub napraw
            int moneta=r.nextInt();
            if(moneta%2==0)
            {
                if((wynik.DN[lekcja_z_A.el.nauczyciel-1][lekcja_z_A.czas]==false)&&
                   (wynik.DK[lekcja_z_A.el.klasa-1][lekcja_z_A.czas]==false)&&
                   (wynik.DS[lekcja_z_A.sala][lekcja_z_A.czas]==false))
                {
                    wynik.lekcje[i]=new lekcja(new element_przydzialu(lekcja_z_A.el.nauczyciel, lekcja_z_A.el.przedmiot, lekcja_z_A.el.klasa),lekcja_z_A.czas, lekcja_z_A.sala);
                    //wynik.lekcje[i].el=new element_przydzialu(lekcja_z_A.el.nauczyciel, lekcja_z_A.el.przedmiot, lekcja_z_A.el.klasa);
                    wynik.DN[lekcja_z_A.el.nauczyciel-1][lekcja_z_A.czas]=true;
                    wynik.DK[lekcja_z_A.el.klasa-1][lekcja_z_A.czas]=true;
                    wynik.DS[lekcja_z_A.sala][lekcja_z_A.czas]=true;
                }
                else
                {
                    if((wynik.DN[lekcja_z_B.el.nauczyciel-1][lekcja_z_B.czas]==false)&&
                        (wynik.DK[lekcja_z_B.el.klasa-1][lekcja_z_B.czas]==false)&&
                        (wynik.DS[lekcja_z_B.sala][lekcja_z_B.czas]==false))
                    {
                        wynik.lekcje[i]=new lekcja(new element_przydzialu(lekcja_z_B.el.nauczyciel, lekcja_z_B.el.przedmiot, lekcja_z_B.el.klasa),lekcja_z_B.czas, lekcja_z_B.sala);
                        //wynik.lekcje[i]=lekcja_z_B;
                        //wynik.lekcje[i].el=new element_przydzialu(lekcja_z_B.el.nauczyciel, lekcja_z_B.el.przedmiot, lekcja_z_B.el.klasa);
                        wynik.DN[lekcja_z_B.el.nauczyciel-1][lekcja_z_B.czas]=true;
                        wynik.DK[lekcja_z_B.el.klasa-1][lekcja_z_B.czas]=true;
                        wynik.DS[lekcja_z_B.sala][lekcja_z_B.czas]=true;
                    }
                    else
                    {
                        konflikty.add(new element_przydzialu(lekcja_z_B.el.nauczyciel, lekcja_z_B.el.przedmiot, lekcja_z_B.el.klasa));
                    }
                }
            }
            else
            {
                if((wynik.DN[lekcja_z_B.el.nauczyciel-1][lekcja_z_B.czas]==false)&&
                   (wynik.DK[lekcja_z_B.el.klasa-1][lekcja_z_B.czas]==false)&&
                   (wynik.DS[lekcja_z_B.sala][lekcja_z_B.czas]==false))
                {
                    //wynik.lekcje[i]=lekcja_z_B;
                    wynik.lekcje[i]=new lekcja(new element_przydzialu(lekcja_z_B.el.nauczyciel, lekcja_z_B.el.przedmiot, lekcja_z_B.el.klasa),lekcja_z_B.czas, lekcja_z_B.sala);
                    wynik.DN[lekcja_z_B.el.nauczyciel-1][lekcja_z_B.czas]=true;
                    wynik.DK[lekcja_z_B.el.klasa-1][lekcja_z_B.czas]=true;
                    wynik.DS[lekcja_z_B.sala][lekcja_z_B.czas]=true;
                }
                else
                {
                    if((wynik.DN[lekcja_z_A.el.nauczyciel-1][lekcja_z_A.czas]==false)&&
                        (wynik.DK[lekcja_z_A.el.klasa-1][lekcja_z_A.czas]==false)&&
                        (wynik.DS[lekcja_z_A.sala][lekcja_z_A.czas]==false))
                    {
                        //wynik.lekcje[i]=lekcja_z_A;
                        wynik.lekcje[i]=new lekcja(new element_przydzialu(lekcja_z_A.el.nauczyciel, lekcja_z_A.el.przedmiot, lekcja_z_A.el.klasa),lekcja_z_A.czas, lekcja_z_A.sala);
                        wynik.DN[lekcja_z_A.el.nauczyciel-1][lekcja_z_A.czas]=true;
                        wynik.DK[lekcja_z_A.el.klasa-1][lekcja_z_A.czas]=true;
                        wynik.DS[lekcja_z_A.sala][lekcja_z_A.czas]=true;
                    }
                    else
                    {
                        konflikty.add(new element_przydzialu(lekcja_z_A.el.nauczyciel, lekcja_z_A.el.przedmiot, lekcja_z_A.el.klasa));
                    }
                }
                
            }
         //System.out.println("Skończyłem dla "+i);   
        }
        for(element_przydzialu kon: konflikty)
            {
                wynik.dodaj_lekcje_MC(kon);
            }
        //System.out.println("===================="); 
        //for(lekcja l:wynik.lekcje)
        //{
         //   System.out.println(l.el.nauczyciel+" "+l.el.przedmiot+" "+l.el.klasa);
        //}
        return wynik;
    }

    void wypisz() {
        System.out.println("============================");
        System.out.println("p="+this.p+" n="+this.n+" s="+this.s+" lekcje leng="+this.lekcje.length);
         for(int i=0;i<this.lekcje.length;i++)
      {
          lekcja l=this.lekcje[i];
          System.out.println(i+" na="+l.el.nauczyciel+" pr="+l.el.przedmiot+" klasa="+l.el.klasa+" sala="+l.sala+" czas"+l.czas);
      }
         System.out.println("f celu="+this.funkcja_celu(1.1, 1.01, 1.001));
    }
    
    
    
}
