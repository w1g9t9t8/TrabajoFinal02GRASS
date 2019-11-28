package edu.pe.udaff.ist.trabajofinal02.Entidades;



public class Usuario {
    private int IdUsuario,DNI,Celular;
    private String Usuario,Contraseña,Correo,Nombres,Apellidos,Direccion;
    private String FechaNacimiento;
    private Double Dinero;



    public Usuario(){

    }


    public Usuario(int DNI, int celular, String usuario, String contraseña, String correo, String nombres, String apellidos, String direccion, String fechaNacimiento, Double dinero) {

        this.DNI = DNI;
        Celular = celular;
        Usuario = usuario;
        Contraseña = contraseña;
        Correo = correo;
        Nombres = nombres;
        Apellidos = apellidos;
        Direccion = direccion;
        FechaNacimiento = fechaNacimiento;
        Dinero= dinero;
    }
    public  boolean isNull(){
        if( Usuario.equals("") && Contraseña.equals("") && Correo.equals("") && Nombres.equals("")&& Apellidos.equals("") && Direccion.equals("") && FechaNacimiento.equals("") && DNI==0 && Celular==0  ){

            return false;
        } else {
            return true;
        }
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "IdUsuario=" + IdUsuario +
                ", Usuario='" + Usuario + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Nombres='" + Nombres + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", DNI=" + DNI +
                ", fechaNacimiento=" + FechaNacimiento +
                ", Celular=" + Celular +
                ", Direccion='" + Direccion + '\'' +
                ", Dinero='" +Dinero+'\''+
                '}';
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setCelular(int celular) {
        Celular = celular;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public int getDNI() {
        return DNI;
    }

    public int getCelular() {
        return Celular;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getNombres() {
        return Nombres;
    }
    public void setDinero(Double dinero) {
        Dinero = dinero;
    }

    public Double getDinero() {
        return Dinero;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }
    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

}
