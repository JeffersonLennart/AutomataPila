
package automatapila;

import java.util.Arrays;


public class NewMain {

    public static void verificarPalindromo(AutomataPushDown A,String cadena){
        StringBuilder newCadena=new StringBuilder(cadena);
        int n=cadena.length();
        if (n%2==0) {
            newCadena.insert(n/2, "S");
            A.mostrarPushPop(newCadena);
        }
        else{
            newCadena.replace(n/2, n/2+1, "S");
            A.mostrarPushPop(newCadena);             
        }
    }
    
    public static void main(String[] args) {
         // Definir Autómata
         AutomataPushDown A= new AutomataPushDown();
         
         // Agregar estados
         A.agregarEstado("q1");
         A.agregarEstado("q2");
         A.agregarEstado("q3");
         
         // Estado inicial y finales
         A.setEstadoInicial("q1");
         A.setEstadosFinales(Arrays.asList("q3"));
         
         // Agregar transiciones  //aabSbaa   aabbbaa
         A.agregarTransicion("q1", "a", "lambda", "a", "q1");
         A.agregarTransicion("q1", "b", "lambda", "b", "q1");
         
         A.agregarTransicion("q1", "S", "lambda", "lambda", "q2");
         
         A.agregarTransicion("q2", "a", "a", "lambda", "q2");
         A.agregarTransicion("q2", "b", "b", "lambda", "q2");
         
         A.agregarTransicion("q2", "lambda", "Z", "lambda", "q3");
         
         // Verificar cadenas
         verificarPalindromo(A,"abaaba");
         System.out.println("");
         verificarPalindromo(A,"aaa");
         System.out.println("");
         verificarPalindromo(A,"baaab");
         System.out.println("");
         verificarPalindromo(A,"abbaabaabba");         
    }
    
}
