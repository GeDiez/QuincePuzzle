package Juego;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gediez
 */
public class Estado {
    private Integer[][] estado = new Integer[4][4];
    private int[] espacio = {0, 0};
    
    public Estado(Integer[][] e){
        estado = e;
        buscaEspacio();
    }
    
    public int[] getEspacio(){
        return espacio;
    }
    
    public Integer getPosicion(int x, int y){
        return estado[x][y];
    }
    
    public Integer getPosicion(int[] p){
        return estado[p[0]][p[1]];
    }
    
    public void setPosicion(int valor, int[] pos){
        estado[pos[0]][pos[1]] = valor;
    }
    
    public void setEspacio(int[] e){
        espacio = e;
    }
    //Mueve una ficha en todas direcciones hasta encontrar un cero hara el cambio de lo contrario regresa false y no hace cambios al estado(Matriz)
    public boolean moverFicha(int[] ficha){
        if(this.moverFicha(ficha, Mover.ARRIBA)) return true;
        if(this.moverFicha(ficha, Mover.ABAJO)) return true;
        if(this.moverFicha(ficha, Mover.DERECHA)) return true;
        if(this.moverFicha(ficha, Mover.IZQUIERDA)) return true;
        return false;
    }
    //Mover una ficha a la direcion dada, sí en esta posicion existe un cero hara el cambio, de lo contrario no la mueve y retornara false
    public boolean moverFicha(int[] ficha, int[] direccion){
        int[] nuevaPos = Mover.mover(ficha, direccion);
        if(     nuevaPos[0] == -1 || 
                nuevaPos[0] == 4 || 
                nuevaPos[1] == -1 || 
                nuevaPos[1] == 4){
                return false;            
        }
        else if(this.getPosicion(nuevaPos) == 0){
                this.setPosicion(this.getPosicion(ficha), nuevaPos);
                this.setPosicion(0, ficha);
                return true;
            }
        return false;
    }
        
    public boolean esIgualA(Estado es){
        for(int i=0; i<4 ;i++){
            for(int j=0; j<4; j++){
                if(getPosicion(i, j) != es.getPosicion(i, j)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public Integer[][] getArreglo(){
        Integer[][] retArr = new Integer[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                retArr[i][j] = estado[i][j];
            }
        }
        return retArr;
    }
    
    public void buscaEspacio(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(estado[i][j] == 0){
                    espacio[0] = i;
                    espacio[1] = j;
                    return;
                }
            }
        }
    }
    
    public Estado MoverEspacio(int[] a){
        //Verificamos si el espacio puede avanzar a la siguiente posicion
        int[] nuevaPos = {espacio[0]+a[0], espacio[1]+a[1]};
        if(nuevaPos[0] == -1 || nuevaPos[0] == 4 || nuevaPos[1] == -1 || nuevaPos[1] == 4){
            return null;
        }
        Estado retes = new Estado(getArreglo());
        retes.setPosicion(retes.getPosicion(nuevaPos), retes.getEspacio());
        retes.setPosicion(0, nuevaPos);
        retes.setEspacio(nuevaPos);
        return retes;
    }
    
    @Override
    public String toString(){
        String retStr = "";
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                retStr = retStr+"   "+estado[i][j];
            }
            retStr = retStr+" \n";
        }        
        return retStr;
    }
    
}
