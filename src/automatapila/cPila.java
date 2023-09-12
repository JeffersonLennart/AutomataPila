
package automatapila;


public class cPila {
    //**********ATRIBUTOS************
    private Object elemento;
    private cPila subPila;
    //**********COSTRUCTORES************
    public cPila(){
        this.elemento = null;
        this.subPila = null;
    }
    public cPila(Object elemento, cPila subPila) {
        this.elemento = elemento;
        this.subPila = subPila;
    }
    //**********MÉTODOS************
    public boolean estaVacio(){
        return this.elemento==null && this.subPila==null;
    }
    public Object Cima(){
        return this.elemento;
    }
    public void push(Object elemento){
        this.subPila=new cPila(this.elemento,this.subPila);
        this.elemento=elemento;
    }
    public void pop(){
        if(!estaVacio()){
            this.elemento=this.subPila.elemento;
            this.subPila=this.subPila.subPila;
        }
    }
   
}
