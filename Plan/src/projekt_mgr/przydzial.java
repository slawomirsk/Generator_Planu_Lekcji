/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mgr;

import sun.security.util.Length;

/**
 *
 * @author slawek
 */
public class przydzial {
    int pi;
    int miejsce_zapisu;
    element_przydzialu[] elementy_przydzialu;
    public przydzial(int pi)
    {
        this.pi=pi;
        miejsce_zapisu=0;
        elementy_przydzialu=new element_przydzialu[pi];
    }
    public przydzial(element_przydzialu[] elementy_przydzialu)
    {
        this.elementy_przydzialu=elementy_przydzialu;
        this.pi=elementy_przydzialu.length;
        miejsce_zapisu=pi;
    }
    
    public void dodaj(element_przydzialu el)
    {
        if(miejsce_zapisu<pi)
        {
            
            elementy_przydzialu[miejsce_zapisu]=el;
            miejsce_zapisu++;
        }
        else
        {
            System.out.println("Błąd: W przydziale nie ma miejsca na zapisanie lekcji.");
        }
    }
    
    public element_przydzialu pobierz_element()
    {
        if(miejsce_zapisu>0)
        {
            miejsce_zapisu--;
            element_przydzialu el=elementy_przydzialu[miejsce_zapisu];
            elementy_przydzialu[miejsce_zapisu]=new element_przydzialu(0, 0, 0);
            return el;
        }
        else
        {
            return new element_przydzialu(0, 0, 0);
        }
    }
    
    public element_przydzialu pobierz_element(int wsk)
    {
        if((miejsce_zapisu>0)&(wsk<miejsce_zapisu))
        {
           
            element_przydzialu el=elementy_przydzialu[wsk];
            miejsce_zapisu--;
            for(int i=wsk;wsk<miejsce_zapisu;i++)
            {
                elementy_przydzialu[i]=elementy_przydzialu[i+1];// może powodować błędy
            }
            elementy_przydzialu[miejsce_zapisu]=new element_przydzialu(0, 0, 0);
            return el;
        }
        else
        {
            return new element_przydzialu(0, 0, 0);
        }
    }
    
    
    public element_przydzialu czytaj_element(int n)
    {
        if((0<=n)&(n<elementy_przydzialu.length))
        {
            return elementy_przydzialu[n];
        }
        else
        {
            return new element_przydzialu(0, 0, 0);
        }
    }
    public void sortujnpk()//sortuje rosnąco
    {
       przydzial.sortujnpk(this.elementy_przydzialu);
    }
    private static element_przydzialu[] sortujnpk(element_przydzialu[] tab)
    {
        if(tab.length==1)
        {
            return tab;
        }
       else
        {
            //podzial
            element_przydzialu[] lewa=new element_przydzialu[tab.length/2];
            element_przydzialu[] prawa=new element_przydzialu[tab.length-tab.length/2];
            for(int i=0;i<tab.length/2;i++)
            {
                lewa[i]=tab[i];
            }
            for(int i=tab.length/2;i<tab.length;i++)
            {
                prawa[i-tab.length/2]=tab[i];
            }
        //rekursja
            lewa=przydzial.sortujnpk(lewa);
            prawa=przydzial.sortujnpk(prawa);
        //scalanie
            int l=0;
            int p=0;
            while (l+p<tab.length)
            {
                if(p==prawa.length)//przepisz lewy
                {
                    while(l+p<tab.length)
                    {
                        tab[l+p]=lewa[l];
                        l++;
                    }
                }
                else
                {
                    if(l==lewa.length)//przepisz prawy
                    {
                        while(l+p<tab.length)
                        {
                            tab[l+p]=prawa[p];
                            p++;
                        }
                    }
                    else
                    {   //prawy jest  większy
                        boolean czy_zabrac_lewy=false;
                        int N=(int)Math.signum(prawa[p].nauczyciel-lewa[l].nauczyciel);
                        int P=(int)Math.signum(prawa[p].przedmiot-lewa[l].przedmiot);
                        int K=(int)Math.signum(prawa[p].klasa-lewa[l].klasa);
                        switch (N)
                        {
                        case -1:
                            czy_zabrac_lewy=true;
                            break;
                        case 0:
                            switch (P)
                            {
                            case -1:
                                czy_zabrac_lewy=true;
                                break;
                            case 0:
                                if(K>=0)
                                    czy_zabrac_lewy=false;
                                else
                                    czy_zabrac_lewy=true;
                                break;

                            case 1:
                                czy_zabrac_lewy=false;
                                break;
                             }
                            break;

                        case 1:
                            czy_zabrac_lewy=false;
                            break;
                         }   
                        if(czy_zabrac_lewy==true)
                        {
                            tab[l+p]=prawa[p];
                            p++;
                        }
                        //lewy jest większy
                        else
                        {
                           tab[l+p]=lewa[l];
                           l++; 
                        }
                    }
                }
            }
            return tab;
        }
    }
    public int liczba_nauczycieli()
    {
    int n=0;
    for(element_przydzialu el:elementy_przydzialu)
    {
        if(el.nauczyciel>n)
            n=el.nauczyciel;
    }
    return n;
    }
    public int liczba_klas()
    {
    int k=0;
    for(element_przydzialu el:elementy_przydzialu)
    {
        if(el.klasa>k)
            k=el.klasa;
    }
    return k;
    }
    public int liczba_przedmiotow()
    {
    int p=0;
    for(element_przydzialu el:elementy_przydzialu)
    {
        if(el.przedmiot>p)
            p=el.przedmiot;
    }
    return p;
    }
    public int liczba_lekcji_w_przydziale()
    {
        return this.miejsce_zapisu;
    }
    public int liczba_lekcji_nauczyciela(int n)
    {
        int wynik=0;
        for(element_przydzialu el: elementy_przydzialu)
        {
            if(el.nauczyciel==n)
            {
                wynik++;
            }
        }
        return wynik;
    }
    public int liczba_lekcji_klasy(int kl)
    {
        int wynik=0;
        for(element_przydzialu el: elementy_przydzialu)
        {
            if(el.klasa==(kl+1))
            {
                wynik++;
            }
        }
        return wynik;
    }
    
}
