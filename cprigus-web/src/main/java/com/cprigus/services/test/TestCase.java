package com.cprigus.services.test;

/**
 *
 * @author User
 */
public class TestCase {
    
    
    
    public static void main (String args[]){
        //fortest();
        
        /*int valor = 0;
        for(int i=1; i<5; i++){
            for(int j=1; j<5; j++){
                valor +=(i*j)+(i-j);
            }
        }
        System.err.println(valor);*/
        int[] serie = {0,0,0,3,4,100,105,1000,901};
        obtenerSerie(serie);
    }
    
    public static void fortest(){
    for(int i=0; i<20; methodFor(i++)){
 }
}
    
    public static void methodFor(int i){
         System.err.println(" -- Valor del Case: ");
        switch(i){
            case 1:
                System.err.println(" -- Case 1, sin break");
            case 2:
                System.err.println(" -- Case 2, sin break");
            case 3:
                System.err.println(" -- Case 3 con break");
                break;
            case 4:
                System.err.println(" -- Case 4 sin break");
            default:
                System.err.println(" -- No Aplica: "+i);
        }
    }
    
    /*Ejemplo de encontrar la serie mayor y la suma*/
    public static void obtenerSerie(int[] serie){
        int j=0;
        int sumaSerie =0;
        int[] sumaTemporal = new int[serie.length];
        boolean haveSerie = false;
        
        nameForSuma: for(int i=0; i<serie.length; i++){
            if(serie.length-1 == i)
                break nameForSuma;
            if(serie[i] == serie[i+1]-1){
                haveSerie = true;
                sumaSerie = sumaSerie + serie[i];
            }
            else{
                if(haveSerie)
                    sumaTemporal[j] = sumaSerie + serie[i];
                j++;
                sumaSerie = 0;
                haveSerie = false;
            }
        }
        System.err.println(" -- La suma de la seria mayor es: "+SumaActual(sumaTemporal));
    }
    
    public static int SumaActual(int[] sumaTemporal){
        int sumaTotal=0;
        for(int i=0; i<sumaTemporal.length;i++)
            sumaTotal = sumaTotal + sumaTemporal[i];
        return sumaTotal;
    }
}
