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
public class projekt_mgr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        element_przydzialu[] tab=
        { //new element_przydzialu(n>0, p>0, k>0),
            //pierwsz 100 lekcji
            //new element_przydzialu(,,),
            //kl1
          new element_przydzialu(1, 1, 1),
          new element_przydzialu(1, 1, 1),
          new element_przydzialu(1, 1, 1),
          new element_przydzialu(1, 1, 1),
          new element_przydzialu(2,2,1),
          new element_przydzialu(2,2,1),
          new element_przydzialu(2,2,1),
          new element_przydzialu(2,2,1),
          new element_przydzialu(2,2,1),
          new element_przydzialu(3,3,1),
          new element_przydzialu(3,3,1),
          new element_przydzialu(3,3,1),
          new element_przydzialu(3,3,1),
          new element_przydzialu(4,4,1),
          new element_przydzialu(4,4,1),
          new element_przydzialu(5,5,1),
          new element_przydzialu(5,5,1),
          new element_przydzialu(6,6,1),
          new element_przydzialu(7,7,1),
          new element_przydzialu(8,8,1),
          new element_przydzialu(9,9,1),
          new element_przydzialu(9,9,1),
          new element_przydzialu(9,9,1),
          new element_przydzialu(9,9,1),
          new element_przydzialu(10,10,1),
          new element_przydzialu(10,10,1),
          new element_przydzialu(10,10,1),
          new element_przydzialu(10,10,1),
          new element_przydzialu(11,11,1),
          new element_przydzialu(11,12,1),
          new element_przydzialu(11,13,1),
          new element_przydzialu(12,14,1),
          new element_przydzialu(12,14,1),
          //kl2
          new element_przydzialu(1, 1, 2),
          new element_przydzialu(1, 1, 2),
          new element_przydzialu(1, 1, 2),
          new element_przydzialu(1, 1, 2),
          new element_przydzialu(1, 1, 2),
          new element_przydzialu(2,2,2),
          new element_przydzialu(2,2,2),
          new element_przydzialu(2,2,2),
          new element_przydzialu(2,2,2),
          new element_przydzialu(2,2,2),
          new element_przydzialu(3,3,2),
          new element_przydzialu(3,3,2),
          new element_przydzialu(3,3,2),
          new element_przydzialu(4,4,2),
          new element_przydzialu(5,5,2),
          new element_przydzialu(5,5,2),
          new element_przydzialu(6,6,2),
          new element_przydzialu(7,7,2),
          new element_przydzialu(8,8,2),
          new element_przydzialu(9,9,2),
          new element_przydzialu(9,9,2),
          new element_przydzialu(9,9,2),
          new element_przydzialu(9,9,2),
          new element_przydzialu(9,9,2),
          new element_przydzialu(10,10,2),
          new element_przydzialu(10,10,2),
          new element_przydzialu(10,10,2),
          new element_przydzialu(10,10,2),
          new element_przydzialu(10,10,2),
          new element_przydzialu(11,11,2),
          new element_przydzialu(11,12,2),
          new element_przydzialu(11,13,2),
          new element_przydzialu(12,14,1),
          new element_przydzialu(12,14,1),
          //kl3
          new element_przydzialu(1, 1, 3),
          new element_przydzialu(1, 1, 3),
          new element_przydzialu(1, 1, 3),
          new element_przydzialu(1, 1, 3),
          new element_przydzialu(1, 1, 3),
          new element_przydzialu(2,2,3),
          new element_przydzialu(2,2,3),
          new element_przydzialu(2,2,3),
          new element_przydzialu(2,2,3),
          new element_przydzialu(2,2,3),
          new element_przydzialu(3,3,3),
          new element_przydzialu(3,3,3),
          new element_przydzialu(3,3,3),
          new element_przydzialu(3,3,3),
          new element_przydzialu(4,4,3),
          new element_przydzialu(5,5,3),
          new element_przydzialu(5,5,3),
          new element_przydzialu(6,6,3),
          new element_przydzialu(7,7,3),
          new element_przydzialu(8,8,3),
          new element_przydzialu(9,9,3),
          new element_przydzialu(9,9,3),
          new element_przydzialu(9,9,3),
          new element_przydzialu(9,9,3),
          new element_przydzialu(10,10,3),
          new element_przydzialu(10,10,3),
          new element_przydzialu(10,10,3),
          new element_przydzialu(10,10,3),
          new element_przydzialu(10,10,3),
          new element_przydzialu(13,15,3),
          new element_przydzialu(13,15,3),
          new element_przydzialu(13,15,3),
          new element_przydzialu(13,15,3),
          
          //kl1'
           new element_przydzialu(1, 1, 4),
          new element_przydzialu(1, 1, 4),
          new element_przydzialu(1, 1, 4),
          new element_przydzialu(1, 1, 4),
          new element_przydzialu(2,2,4),
          new element_przydzialu(2,2,4),
          new element_przydzialu(2,2,4),
          new element_przydzialu(2,2,4),
          new element_przydzialu(2,2,4),
          new element_przydzialu(3,3,4),
          new element_przydzialu(3,3,4),
          new element_przydzialu(3,3,4),
          new element_przydzialu(3,3,4),
          new element_przydzialu(4,4,4),
          new element_przydzialu(4,4,4),
          new element_przydzialu(5,5,4),
          new element_przydzialu(5,5,4),
          new element_przydzialu(6,6,4),
          new element_przydzialu(7,7,4),
          new element_przydzialu(8,8,4),
          new element_przydzialu(9,9,4),
          new element_przydzialu(9,9,4),
          new element_przydzialu(9,9,4),
          new element_przydzialu(9,9,4),
          new element_przydzialu(10,10,4),
          new element_przydzialu(10,10,4),
          new element_przydzialu(10,10,4),
          new element_przydzialu(10,10,4),
          new element_przydzialu(11,11,4),
          new element_przydzialu(11,12,4),
          new element_przydzialu(11,13,4),
          new element_przydzialu(12,14,4),
          new element_przydzialu(12,14,4),
          //kl2'
          new element_przydzialu(14, 1, 5),
          new element_przydzialu(14, 1, 5),
          new element_przydzialu(14, 1, 5),
          new element_przydzialu(14, 1, 5),
          new element_przydzialu(14, 1, 5),
          new element_przydzialu(15,2,5),
          new element_przydzialu(15,2,5),
          new element_przydzialu(15,2,5),
          new element_przydzialu(15,2,5),
          new element_przydzialu(15,2,5),
          new element_przydzialu(3,3,5),
          new element_przydzialu(3,3,5),
          new element_przydzialu(3,3,5),
          new element_przydzialu(4,4,5),
          new element_przydzialu(5,5,5),
          new element_przydzialu(5,5,5),
          new element_przydzialu(6,6,5),
          new element_przydzialu(7,7,5),
          new element_przydzialu(8,8,5),
          new element_przydzialu(16,9,5),
          new element_przydzialu(16,9,5),
          new element_przydzialu(16,9,5),
          new element_przydzialu(16,9,5),
          new element_przydzialu(16,9,5),
          new element_przydzialu(17,10,5),
          new element_przydzialu(17,10,5),
          new element_przydzialu(17,10,5),
          new element_przydzialu(17,10,5),
          new element_przydzialu(17,10,5),
          new element_przydzialu(11,11,5),
          new element_przydzialu(11,12,5),
          new element_przydzialu(11,13,5),
          new element_przydzialu(12,14,5),
          new element_przydzialu(12,14,5),
          //kl3'
          new element_przydzialu(14, 1, 6),
          new element_przydzialu(14, 1, 6),
          new element_przydzialu(14, 1, 6),
          new element_przydzialu(14, 1, 6),
          new element_przydzialu(14, 1, 6),
          new element_przydzialu(15,2,6),
          new element_przydzialu(15,2,6),
          new element_przydzialu(15,2,6),
          new element_przydzialu(15,2,6),
          new element_przydzialu(15,2,6),
          new element_przydzialu(18,3,6),
          new element_przydzialu(18,3,6),
          new element_przydzialu(18,3,6),
          new element_przydzialu(18,3,6),
          new element_przydzialu(4,4,6),
          new element_przydzialu(5,5,6),
          new element_przydzialu(5,5,6),
          new element_przydzialu(6,6,6),
          new element_przydzialu(7,7,6),
          new element_przydzialu(8,8,6),
          new element_przydzialu(16,9,6),
          new element_przydzialu(16,9,6),
          new element_przydzialu(16,9,6),
          new element_przydzialu(16,9,6),
          new element_przydzialu(17,10,6),
          new element_przydzialu(17,10,6),
          new element_przydzialu(17,10,6),
          new element_przydzialu(17,10,6),
          new element_przydzialu(17,10,6),
          new element_przydzialu(13,15,6),
          new element_przydzialu(13,15,6),
          new element_przydzialu(13,15,6),
          new element_przydzialu(13,15,6),
          //lk1''
           new element_przydzialu(14, 1, 7),
          new element_przydzialu(14, 1, 7),
          new element_przydzialu(14, 1, 7),
          new element_przydzialu(14, 1, 7),
          new element_przydzialu(15,2,7),
          new element_przydzialu(15,2,7),
          new element_przydzialu(15,2,7),
          new element_przydzialu(15,2,7),
          new element_przydzialu(15,2,7),
          new element_przydzialu(18,3,7),
          new element_przydzialu(18,3,7),
          new element_przydzialu(18,3,7),
          new element_przydzialu(18,3,7),
          new element_przydzialu(4,4,7),
          new element_przydzialu(4,4,7),
          new element_przydzialu(5,5,7),
          new element_przydzialu(5,5,7),
          new element_przydzialu(6,6,7),
          new element_przydzialu(7,7,7),
          new element_przydzialu(8,8,7),
          new element_przydzialu(16,9,7),
          new element_przydzialu(16,9,7),
          new element_przydzialu(16,9,7),
          new element_przydzialu(16,9,7),
          new element_przydzialu(17,10,7),
          new element_przydzialu(17,10,7),
          new element_przydzialu(17,10,7),
          new element_przydzialu(17,10,7),
          new element_przydzialu(11,11,7),
          new element_przydzialu(11,12,7),
          new element_przydzialu(11,13,7),
          new element_przydzialu(12,14,7),
          new element_przydzialu(12,14,7),
          //kl2''
          new element_przydzialu(14, 1, 8),
          new element_przydzialu(14, 1, 8),
          new element_przydzialu(14, 1, 8),
          new element_przydzialu(14, 1, 8),
          new element_przydzialu(14, 1, 8),
          new element_przydzialu(15,2,8),
          new element_przydzialu(15,2,8),
          new element_przydzialu(15,2,8),
          new element_przydzialu(15,2,8),
          new element_przydzialu(15,2,8),
          new element_przydzialu(18,3,8),
          new element_przydzialu(18,3,8),
          new element_przydzialu(18,3,8),
          new element_przydzialu(4,4,8),
          new element_przydzialu(5,5,8),
          new element_przydzialu(5,5,8),
          new element_przydzialu(6,6,8),
          new element_przydzialu(7,7,8),
          new element_przydzialu(8,8,8),
          new element_przydzialu(16,9,8),
          new element_przydzialu(16,9,8),
          new element_przydzialu(16,9,8),
          new element_przydzialu(16,9,8),
          new element_przydzialu(16,9,8),
          new element_przydzialu(17,10,8),
          new element_przydzialu(17,10,8),
          new element_przydzialu(17,10,8),
          new element_przydzialu(17,10,8),
          new element_przydzialu(17,10,8),
          new element_przydzialu(11,11,8),
          new element_przydzialu(11,12,8),
          new element_przydzialu(11,13,8),
          new element_przydzialu(12,14,8),
          new element_przydzialu(12,14,8),
          //kl3''
          new element_przydzialu(19, 1, 9),
          new element_przydzialu(19, 1, 9),
          new element_przydzialu(19, 1, 9),
          new element_przydzialu(19, 1, 9),
          new element_przydzialu(19, 1, 9),
          new element_przydzialu(20,2,9),
          new element_przydzialu(20,2,9),
          new element_przydzialu(20,2,9),
          new element_przydzialu(20,2,9),
          new element_przydzialu(20,2,9),
          new element_przydzialu(18,3,9),
          new element_przydzialu(18,3,9),
          new element_przydzialu(18,3,9),
          new element_przydzialu(18,3,9),
          new element_przydzialu(4,4,9),
          new element_przydzialu(5,5,9),
          new element_przydzialu(5,5,9),
          new element_przydzialu(6,6,9),
          new element_przydzialu(7,7,9),
          new element_przydzialu(8,8,9),
          new element_przydzialu(21,9,9),
          new element_przydzialu(21,9,9),
          new element_przydzialu(21,9,9),
          new element_przydzialu(21,9,9),
          new element_przydzialu(22,10,9),
          new element_przydzialu(22,10,9),
          new element_przydzialu(22,10,9),
          new element_przydzialu(22,10,9),
          new element_przydzialu(22,10,9),
          new element_przydzialu(13,15,9),
          new element_przydzialu(13,15,9),
          new element_przydzialu(13,15,9),
          new element_przydzialu(13,15,9),
        };
        przydzial p=new przydzial(tab);
        //plan pl=new plan(p,40,5,3);
        //pl.generuj_plan();
        //pl.generuj_planD();
        //pl.wypisz();
        
        //dla 100l
//        Random r= new Random();
//        boolean[][] dn= new boolean[14][40];
//        for(int i=0;i<14;i++)
//        {
//            for(int j=0;j<40;j++)
//            {
//                dn[i][j]=true;
//            }
//        }
//        for(int i=0;i<14*8;i++)
//        {
//            int w=r.nextInt();
//            w=Math.abs(w);
//            System.out.println(w+" "+(w%14)+" "+(w%40));
//            dn[w%14][w%40]=false;
//        }
//        boolean[][] ds= new boolean[3][40];
        
       
       //plan pl2=new plan(p,40,5,3);
       //pl2.ustaw_DN(dn);
       //pl2.generuj_plan();
       //pl2.generuj_planD();
       
        //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_zachlanna(p,40,5,3, 1.1, 1.01, 1.001, dn, ds);
        // plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_MC(10000, p,40,5,3, 1.1, 1.01, 1.001,dn,ds);
        //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_genetyczna(1000,10, p,40,5,3, 1.1, 1.01, 1.001,1,dn,ds);
        //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_genetyczna(1000,10, p,40,5,3, 1.1, 1.01, 1.001,2,dn,ds);
       //pl2.wypisz();
        
        
        //dla 200l
//        boolean[][] dn= new boolean[19][40];
//        boolean[][] ds= new boolean[6][40];
//        Random r= new Random();
//        for(int i=0;i<19;i++)
//        {
//            for(int j=0;j<40;j++)
//            {
//                dn[i][j]=true;
//            }
//        }
//        for(int i=0;i<19*16;i++)
//        {
//            int w=r.nextInt();
//            w=Math.abs(w);
//            System.out.println(w+" "+(w%14)+" "+(w%40));
//            dn[w%14][w%40]=false;
//        }
       
       
       //plan pl2=new plan(p,40,8,6);
       //pl2.generuj_planD();
       //pl2.generuj_plan();
       //pl2.generuj_plan_MC();
       
       
      //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_genetyczna(1000,10, p,40,8,6, 1.1, 1.01, 1.001,1,dn,ds);
      //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_genetyczna(1000,10, p,40,8,6, 1.1, 1.01, 1.001,2,dn,ds);
      //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_MC(10000, p,40,8,6, 1.1, 1.01, 1.001,dn,ds);
      // plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_zachlanna(p,40,8,6, 1.1, 1.01, 1.001,dn,ds);
       //pl2.wypisz();
        
        //dla 300l
        boolean[][] dn= new boolean[23][40];
        boolean[][] ds= new boolean[6][40];
        Random r= new Random();
        for(int i=0;i<23;i++)
        {
            for(int j=0;j<40;j++)
            {
                dn[i][j]=true;
            }
        }
        for(int i=0;i<23*16;i++)
        {
            int w=r.nextInt();
            w=Math.abs(w);
            System.out.println(w+" "+(w%14)+" "+(w%40));
            dn[w%14][w%40]=false;
        }
       
       //plan pl2=new plan(p,40,8,9);
       //pl2.generuj_planD();
       //pl2.generuj_plan();
       //pl2.generuj_plan_MC();
       
       
      //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_genetyczna(1000,10, p,40,8,9, 1.1, 1.01, 1.001,1,dn,ds);
      plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_genetyczna(1000,10, p,40,8,9, 1.1, 1.01, 1.001,2,dn,ds);
      //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_MC(10000, p,40,8,9, 1.1, 1.01, 1.001,dn,ds);
       //plan pl2=Algorytmy_optymalizacyjne.Optymalizacja_zachlanna(p,40,8,9, 1.1, 1.01, 1.001,dn,ds);
       pl2.wypisz();
        
  }
}
