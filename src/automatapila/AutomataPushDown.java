
package automatapila;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Consideraciones a tener en cuenta:
   Los pesos de cada arista deben ser string, esto porque al momento
   de verificar una cadena asumimos esta como string.
*/
public class AutomataPushDown {
    //***********ATRIBUTOS*************
    private cGrafo grafo;
    private cPila pila;
    private Object estadoInicial;
    private List<Object> estadosFinales;
    //***********CONSTRUCTORES*************
    public AutomataPushDown(){
        pila =new cPila();
        grafo=new cGrafo();
        estadoInicial=new Object();
        estadosFinales=new ArrayList<Object>();
        pila.push("Z");
    }
    // ************MÉTODOS GET Y SET************

    public Object getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Object estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Object getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstadosFinales(List<Object> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    //***********MÉTODOS DE PROCESO*************
     
    // -- Agregar estado
    public void agregarEstado(Object estado){
        // -- Añadir vértice al grafo
        grafo.agregarVertice(estado);
    }
    // -- Agregar transición
    public void agregarTransicion(Object estado1,Object peso,Object pop,Object push,Object estado2){
        // -- Añadir arista al grafo
        grafo.agregarArista(estado1,peso,pop,push,estado2);
    }
    // -- Realizar push si hay mas de un caracter
    public void pushing(Object push){
        char [] caracteres=push.toString().toCharArray();
        String token="";
        for (int i = 0; i < caracteres.length; i++) {
            token=Character.toString(caracteres[i]);
            pila.push(token);
        } 
    }
    // -- Reiniciar pila para proximos procesos
    public void reiniciarPila(){
        while(!pila.estaVacio()){
            pila.pop();
        }
        pila.push("Z");
    }
    // -- Verificar transición
    public boolean verificarTransicion(Object pop,Object push){
        // -- flag estaBien
        boolean estaBien=true;  
        if(!pop.equals("lambda") && !push.equals("lambda") ){
            pushing(push);
            if(pop.equals(pila.Cima())){
                pila.pop();
            }
            else{
                estaBien=false;
            }
        }
        else if (!pop.equals("lambda") ) {
            if(pop.equals(pila.Cima())){
                pila.pop();
            }
            else{
                estaBien=false;
            }
        }
        else if (!push.equals("lambda") ){
            pushing(push);
        }           
        return estaBien;
    }
    // -- Proceso en pila
    public boolean procesoPila(Object estado){
        if(estado!=null){
            // -- Recuperar información del estado 
            Nodo aux=(Nodo)estado;
            // -- Verificar transición
            return verificarTransicion(aux.pop,aux.push);
        }
        else{
            return false;
        } 
    }
    // -- Verificar cadena
    public boolean cadenaEsAceptada(Object cadena){
        // -- Definir un estado 
        Object estado=this.estadoInicial;
        // -- flag
        boolean flagAceptado=true;
        // -- Recuperar array de caracteres
        char[] caracteres=cadena.toString().toCharArray();
        // -- Procesar caracteres
        for (int i = 0; i < caracteres.length && flagAceptado; i++) {
            // -- Movernos al siguiente estado
            estado=grafo.verticeSgte(estado, Character.toString(caracteres[i]));
            // -- Realizar procedimiento si el estado siguiente existe
            flagAceptado=procesoPila(estado);
            // -- Convertir estado para poder moverse nuevamente  
            if(flagAceptado)
                estado=((Nodo)estado).vertice;
        }                
        // -- Realizar última verificación        
        if(flagAceptado){
            estado=grafo.verticeSgte(estado,"lambda");
            flagAceptado=procesoPila(estado);          
        }
        // -- Reiniciar pila
        reiniciarPila();
        return flagAceptado;
    }
    
    //***********************************************************
    //************************MÉTODOS PARA LA TAREA**************
    // -- Realizar push si hay mas de un caracter
    public void mostrarPushing(Object push){
        char [] caracteres=push.toString().toCharArray();
        String token="";
        for (int i = 0; i < caracteres.length; i++) {
            token=Character.toString(caracteres[i]);
            pila.push(token);
            System.out.println("push "+token.toString());
        } 
    }
    public boolean mostrarTransicion(Object pop,Object push){
        // -- flag estaBien
        boolean estaBien=true;  
        if(!pop.equals("lambda") && !push.equals("lambda") ){
            mostrarPushing(push);
            if(pop.equals(pila.Cima())){
                pila.pop();
                System.out.println("pop "+pop.toString());
            }
            else{
                System.out.println("pop "+pop.toString()+" No es posible");
                estaBien=false;
            }
        }
        else if (!pop.equals("lambda") ) {
            if(pop.equals(pila.Cima())){
                pila.pop();
                System.out.println("pop "+pop.toString());
            }
            else{
                System.out.println("pop "+pop.toString()+" No es posible");
                estaBien=false;
            }
        }
        else if (!push.equals("lambda") ){
            mostrarPushing(push);
        }           
        return estaBien;
    }
    public boolean mostrarProcesoPila(Object estado){
        if(estado!=null){
            // -- Recuperar información del estado 
            Nodo aux=(Nodo)estado;
            // -- Verificar transición
            return mostrarTransicion(aux.pop,aux.push);
        }
        else{
            return false;
        } 
    }
    public void mostrarPushPop(Object cadena){
        // -- Definir un estado 
        Object estado=this.estadoInicial;
        // -- flag
        boolean flagAceptado=true;
        // -- Recuperar array de caracteres
        char[] caracteres=cadena.toString().toCharArray();
        // -- Procesar caracteres
        for (int i = 0; i < caracteres.length && flagAceptado; i++) {
            // -- Movernos al siguiente estado
            estado=grafo.verticeSgte(estado, Character.toString(caracteres[i]));
            // -- Realizar procedimiento si el estado siguiente existe
            flagAceptado=mostrarProcesoPila(estado);
            // -- Convertir estado para poder moverse nuevamente  
            if(flagAceptado)
                estado=((Nodo)estado).vertice;
        }                
        // -- Realizar última verificación        
        if(flagAceptado){
            estado=grafo.verticeSgte(estado,"lambda");
            flagAceptado=mostrarProcesoPila(estado);          
        }        
        if(flagAceptado){
            System.out.println("Cadena aceptada");
        }
        else{
            System.out.println("Cadena no aceptada");
        }
        // -- Reinicar pila
        reiniciarPila();
    }
}
