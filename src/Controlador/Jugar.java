/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Juego.Estado;
import Juego.Puzzle;
import Vista.Principal;
import Vista.TableroPuzzle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gediez
 */
public class Jugar implements ActionListener{
    Principal startup;
    Puzzle juego;
    TableroPuzzle tablero;    
    Estado inicial, objetivo;
    
    public Jugar(){
        startup = new Principal();
        juego = new Puzzle();
        tablero = startup.getTableroPuzzle1();
        Integer[][] ini = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        Integer[][] obj = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        inicial = new Estado(ini);
        objetivo = new Estado(obj);
        
        startup.setVisible(true);
        tablero.setInicial(inicial);
        tablero.pintarEstado();
        startup.getResolver().addActionListener(this);
        startup.getMovimiento().addActionListener(this);
    }
    
    public static void main(String[] args){
        new Jugar();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == startup.getResolver()){
            tablero.getInicial().buscaEspacio();
            juego.setEstadoInicial(tablero.getInicial());
            juego.setEstadoObjetivo(objetivo);
            juego.setProfundidad(Integer.parseInt(startup.getProfundidad().getText()));
            
            if(juego.solucion()){
                startup.getMsj().setText(" Se hallo una solucion");
                startup.getMovimiento().setEnabled(true);
            }
            else{
                startup.getMsj().setText("Aun no se ha hallado una solucion, intenta con una mayor profundidad");
            }
        }
        
        if(ae.getSource() == startup.getMovimiento()){
            //Iterator<Estado> itera = juego.getPilaDeEstados().iterator();            
            if(!juego.getPilaDeEstados().empty()){
                tablero.setInicial(juego.getMovimiento());
                tablero.pintarEstado();
            }
            else{
                startup.getMovimiento().enable(false);
                tablero.setContarMovimientos(0);
            }
        }
    }   
}
