package edu.pe.udaff.ist.trabajofinal02.Entidades;

import java.io.Serializable;

public class Grass implements Serializable {
    int Idgrass,Idusuario ,Celular;
    String Nomgrass ,Descripcion,Direccion,Tamaño,Estado;
    Double Precio;
    public Grass(){

    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Grass(int idgrass, int idusuario, String nomgrass, String descripcion, Double precio, String direccion, String tamaño, int celular ,String estado) {
        this.Idgrass=idgrass;
        Idusuario=idusuario;
        Nomgrass=nomgrass;
        Descripcion=descripcion;
        Precio=precio;
        Direccion=direccion;
        Tamaño=tamaño;
        Celular=celular;
        Estado=estado;
    }
    public  boolean isNull(){
        if(  Nomgrass.equals("") || Descripcion.equals("") || Precio==0 || Direccion.equals("")|| Tamaño.equals("") || Celular==0 ){
            return false;
        } else {
            return true;
        }
    }
    @Override
    public String toString() {
        return "Grass{" +
                "Idgrass=" + Idgrass +
                ", Idusuario='" + Idusuario + '\'' +
                ", Nomgrass='" + Nomgrass + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Precio=" + Precio +
                ", Direccion='" + Direccion + '\'' +
                ", Tamaño='" + Tamaño + '\'' +
                ", Celular='" +Celular+'\''+
                ", Estado='" + Estado + '\'' +
                '}';
    }

    public int getIdgrass() {
        return Idgrass;
    }

    public void setIdgrass(int idgrass) {
        Idgrass = idgrass;
    }

    public int getIdusuario() {
        return Idusuario;
    }

    public void setIdusuario(int idusuario) {
        Idusuario = idusuario;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int celular) {
        Celular = celular;
    }

    public String getNomgrass() {
        return Nomgrass;
    }

    public void setNomgrass(String nomgrass) {
        Nomgrass = nomgrass;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTamaño() {
        return Tamaño;
    }

    public void setTamaño(String tamaño) {
        Tamaño = tamaño;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }
}
