/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author gediez
 */
public abstract class Mover {
    static final int[] DERECHA = {0, 1};
    static final int[] IZQUIERDA = {0, -1};
    static final int[] ARRIBA = {-1, 0};
    static final int[] ABAJO = {1, 0};
    
    //mueve una posicion a una posicio siguiente
    public static int[] mover(int[] pi, int[] incremento){
        int[] ret = {(pi[0]+incremento[0]), (pi[1]+ incremento[1])};
        return ret;
    }
}
