/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author fs
 */
public class Codificacion implements Comparable<Codificacion> {
    
    private Integer simbolo;
    private Double probabilidad;
    private int longitud;
    private String codigo;
    
    
    public Codificacion (Integer simbolo, Double probabilidad, int longitud, String codigo){
        this.simbolo=simbolo;
        this.probabilidad=probabilidad;
        this.longitud = longitud;
        this.codigo = codigo;
    }
    
    public Integer getSimbolo(){
        return simbolo;
    }
    
    public Double getProbabilidad(){
        return probabilidad;
    }
    
    public int getLongitud(){
        return longitud;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public void setSimbolo(Integer simbolo){
        this.simbolo = simbolo;
    }
    
    public void setProbabilidad(Double probabilidad){
        this.probabilidad = probabilidad;
    }
    
   public void setLongitud(int longitud){
       this.longitud = longitud;
   }
   public void setSimbolo(String codigo){
       this.codigo = codigo;
   }
   
   public int compareTo(Codificacion t1) {
        if (longitud > t1.getLongitud()) {
            return 1;
        } else if (longitud < t1.getLongitud()) {
            return -1;
        } else {
            return 0;
        }
    }
}
