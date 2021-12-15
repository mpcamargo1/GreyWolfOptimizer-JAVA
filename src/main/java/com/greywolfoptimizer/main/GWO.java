/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.greywolfoptimizer.main;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author marcospaulo
 */
public class GWO {
      static Random random = new Random(System.currentTimeMillis());
      private static int AGENT = 5;
      private static int DIMENSION = 2;
      private static int MAX_IT = 1000;

      private static int alpha;
      private static int beta;
      private static int delta;

      private static double[][] wolves = new double[AGENT][DIMENSION];
      private static double[] alphaPos = new double[DIMENSION];
      private static double[] betaPos = new double[DIMENSION];
      private static double[] deltaPos = new double[DIMENSION];   
      
      private static final DecimalFormat df = new DecimalFormat("0.00");  

      public static double[] optimize(){

         initialize();
         for(int i=0;i<MAX_IT;i++){
              update_wolves();
              update(i);
            }
         System.out.println(df.format(alphaPos[0]) + " " + df.format(alphaPos[1]));
         return alphaPos;
      }

      private static void initialize(){
         for(int i=0;i<AGENT;i++){
             wolves[i][0] =  get_random(0,10);
             wolves[i][1] =  get_random(0,10);   
         }
 
         alpha = elitism(Double.MAX_VALUE);
         beta = elitism(fitness(wolves[alpha]));
         delta = elitism(fitness(wolves[beta]));

         alphaPos = Arrays.copyOfRange(wolves[alpha],0, DIMENSION);
         betaPos  =  Arrays.copyOfRange(wolves[beta],0,DIMENSION); 
         deltaPos = Arrays.copyOfRange(wolves[delta],0,DIMENSION); 
  

      }

      private static int elitism(double found_maximum){
        double maximum = fitness(wolves[0]);
        int i,index_maximum=0;
            for(i=1;i<AGENT;i++){
              if(fitness(wolves[i]) > maximum && fitness(wolves[i]) < found_maximum){
                  index_maximum=i;
                  maximum=fitness(wolves[i]);
                }
            }
           return index_maximum;
      }

      private static double get_random(double min,double max){
        return random.nextDouble() * (max - min) + min; 
      }

      private static void update_wolves(){
           double result;
           int aux_index_alpha;
           int aux_index_beta;
      
             for(int i=0;i<AGENT;i++){
                result = fitness(wolves[i]);
                   if(result > fitness(alphaPos)){
                        aux_index_alpha = alpha;
                        aux_index_beta = beta;

                        alphaPos = Arrays.copyOfRange(wolves[i],0, DIMENSION);
                        betaPos = Arrays.copyOfRange(wolves[aux_index_alpha],0, DIMENSION);
                        deltaPos = Arrays.copyOfRange(wolves[aux_index_beta],0, DIMENSION);

                        alpha=i;
                        beta=aux_index_alpha;
                        delta=aux_index_beta;

                     }
                     else if((result > fitness(betaPos)) && (result < fitness(alphaPos))){
                        aux_index_beta = beta;

                        betaPos = Arrays.copyOfRange(wolves[i],0, DIMENSION);
                        deltaPos = Arrays.copyOfRange(wolves[aux_index_beta],0, DIMENSION);
 
                        beta = i;
                        delta=aux_index_beta;                                              
                     }
                     else if((result > fitness(deltaPos)) && (result < fitness(betaPos))){
                        delta = i;
                        deltaPos = Arrays.copyOfRange(wolves[i],0, DIMENSION);
                       
                     }

              

             }


      }

      private static void update(int iteration){
         double a = update_a(iteration);
         //System.out.println(a);
         //new java.util.Scanner(System.in).nextLine();
         for(int i=0;i<AGENT;i++){
           for(int j=0;j<DIMENSION;j++){

               double r1 = random.nextDouble();
               double r2 = random.nextDouble();
               double a1 = (2*a*r1) - a;
               double c1 = 2*r2;
               double dAlpha = ((c1*alphaPos[j])- (wolves[i][j]));
               double x1 = (alphaPos[j] - (a1*dAlpha));

               r1 = random.nextDouble();
               r2 = random.nextDouble();
               double a2 = (2*a*r1)-a;
               double c2 = 2*r2;
               double dBeta = ((c2*betaPos[j]) - (wolves[i][j]));
               double x2 = (betaPos[j] - (a2*dBeta));

               r1 = random.nextDouble();
               r2 = random.nextDouble();
               double a3 = (2*a*r1)-a;
               double c3 = 2*r2;
               double dDelta = ((c3*deltaPos[j]) - (wolves[i][j]));
               double x3 = (deltaPos[j] - (a3*dDelta));

               wolves[i][j] = (x1+x2+x3)/3;
               
            }         
         }
      }

      private static double update_a(double iteration){
           return 2 - (iteration* (2/((double) MAX_IT - 1)));
      }

      private static double fitness(double[] x){
         if(function(x) >= 0)
              return 1/(1+function(x));       
         else
              return 1 + (-1*(function(x)));
      }
 
       private static double function(double[] x){
             return Math.pow(x[0] + 10, 2) + Math.pow(x[1] + 10, 2) + Math.exp(-Math.pow(x[0], 2) - Math.pow(x[1], 2)); // Brent  Function
            //return  100*Math.pow(x[1] - Math.pow(x[0],3),2) + Math.pow(1 - x[0],2);		    // Leon Function
       }
      

      
}
