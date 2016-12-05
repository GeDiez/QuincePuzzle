/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author gediez
 */
public class Puzzle {
    private Stack<Estado> Respuesta;
    private Estado inicial;
    private Estado objetivo;
    private int prof = 0;
    private int profmax = 0;
    
    private Calendar Calendario;
    
    public Puzzle(){
        Respuesta = new Stack();
    }
    
    public void setEstadoInicial(Estado ini){
        inicial = ini;
    }
    
    public void setEstadoObjetivo(Estado obj){
        objetivo = obj;
    }
    
    public void setProfundidad(int p){
        profmax = p;
    }

    public int getProf() {
        return prof;
    }

    public int getProfmax() {
        return profmax;
    }
    
    public Estado getMovimiento(){
        return Respuesta.pop();
    }
    
    public boolean solucion(){
        return resolver(inicial, 0);
    }
    
    public Stack<Estado> getPilaDeEstados(){
        return Respuesta;
    }
    
    public boolean resolver(Estado i, int proveniente){
        if(prof ==1){
            Calendario = Calendar.getInstance();
            System.out.println(prof+" Hora: "+Calendario.get(Calendar.HOUR_OF_DAY) +":"+Calendario.get(Calendar.MINUTE)+":"+Calendario.get(Calendar.SECOND));
        }        
        if(i == null)return false;        
        prof=prof+1;
        if(i.esIgualA(objetivo)){
            //System.out.println(i.toString());
            Respuesta.push(i);
            return true;
        }
        if(prof > profmax){
            prof = prof-1;
            return false;
        }
        if(proveniente != 3)
        if(resolver(i.MoverEspacio(Mover.DERECHA), 1) == true){
            Respuesta.push(i);
            return true;
        }
        if(proveniente != 4)
        if(resolver(i.MoverEspacio(Mover.ABAJO), 2) == true){
            Respuesta.push(i);
            return true;
        }
        if(proveniente != 1)
        if(resolver(i.MoverEspacio(Mover.IZQUIERDA), 3) == true){
            Respuesta.push(i);
            return true;
        }
        if(proveniente != 2)
        if(resolver(i.MoverEspacio(Mover.ARRIBA), 4) == true){
            Respuesta.push(i);
            return true;
        }
        
        prof = prof-1;
        return false;
    }
    
    public static void main(String [] a){
        //Integer[][] ini = {{0,14,15,13},{3,1,5,12},{10,4,6,2},{11,9,7,8}};
        Integer[][] ini = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{0,13,14,15}};
        Integer[][] obj = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        
        Puzzle juego = new Puzzle();        
        juego.setEstadoInicial(new Estado(ini));
        juego.setEstadoObjetivo(new Estado(obj));
        juego.setProfundidad(4);
        
        if(juego.solucion()){
            System.out.println("Se hallo una solucion");
            Iterator<Estado> itera = juego.getPilaDeEstados().iterator();
            while(itera.hasNext()){
                System.out.println(itera.next().toString());
            }
            System.out.println("Numero de movimientos: "+juego.getProf());
            System.out.println("Se intento con una profundidad maxima de: "+juego.getProfmax());
        }
        else
            System.out.println("Aun no se ha hallado una solucion, intenta con una mayor profundidad");
        
    }
}
