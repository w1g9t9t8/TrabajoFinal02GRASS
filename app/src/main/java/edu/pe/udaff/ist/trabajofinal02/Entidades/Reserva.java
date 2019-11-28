package edu.pe.udaff.ist.trabajofinal02.Entidades;

public class Reserva {
    int Idreserva,Idgrass,Idusuario ,HoraInicio,HoraFin,Horas,Reservado;
    String Nomgrass ,Nomuser,FechaReserv;
    Double Costo;
    public Reserva(){

    }

    public int getIdreserva() {
        return Idreserva;
    }

    public void setIdreserva(int idreserva) {
        Idreserva = idreserva;
    }

    public int getReservado() {
        return Reservado;
    }

    public void setReservado(int reservado) {
        Reservado = reservado;
    }

    public Reserva(int idreserva, int idgrass, int idusuario, int horaInicio, int horaFin, int horas, String nomgrass, String nomuser, String fechaReserv, Double costo, int reservado) {
        Idreserva = idreserva;
        Idgrass = idgrass;
        Idusuario = idusuario;
        HoraInicio = horaInicio;
        HoraFin = horaFin;
        Horas = horas;
        Nomgrass = nomgrass;
        Nomuser = nomuser;
        FechaReserv = fechaReserv;
        Costo = costo;
        Reservado=reservado;
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

    public int getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(int horaInicio) {
        HoraInicio = horaInicio;
    }

    public int getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(int horaFin) {
        HoraFin = horaFin;
    }

    public int getHoras() {
        return Horas;
    }

    public void setHoras(int horas) {
        Horas = horas;
    }

    public String getNomgrass() {
        return Nomgrass;
    }

    public void setNomgrass(String nomgrass) {
        Nomgrass = nomgrass;
    }

    public String getNomuser() {
        return Nomuser;
    }

    public void setNomuser(String nomuser) {
        Nomuser = nomuser;
    }

    public String getFechaReserv() {
        return FechaReserv;
    }

    public void setFechaReserv(String fechaReserv) {
        FechaReserv = fechaReserv;
    }

    public Double getCosto() {
        return Costo;
    }

    public void setCosto(Double costo) {
        Costo = costo;
    }
}
