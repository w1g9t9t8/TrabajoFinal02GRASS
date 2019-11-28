package edu.pe.udaff.ist.trabajofinal02.Utilidades;

public class Utilidades {

    //Constantes de campos de tabla USUARIO
    public static final String TABLA_USUARIO="usuario";
    public static final String USUARIO_idusuario="idusuario";
    public static final String USUARIO_usuario="usuario";
    public static final String USUARIO_contraseña="contraseña";
    public static final String USUARIO_correo="correo";
    public static final String USUARIO_nombre="nombre";
    public static final String USUARIO_apellido="apellido";
    public static final String USUARIO_dni="dni";
    public static final String USUARIO_fechanacimiento="fechanacimiento";
    public static final String USUARIO_celular="celular";
    public static final String USUARIO_direccion="direccion";
    public static final String USUARIO_dinero="dinero";
    public static  final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+"("+USUARIO_idusuario+" integer primary key autoincrement,"+USUARIO_usuario+" text UNIQUE ,"+USUARIO_contraseña+" text,"+USUARIO_correo+" text,"+USUARIO_nombre+" text,"+USUARIO_apellido+" text,"+USUARIO_dni+" int, "+USUARIO_fechanacimiento+" text, "+USUARIO_celular+" int, "+USUARIO_direccion+" text,"+USUARIO_dinero+" double)";


    //Constantes de campos de tabla GRASS
    public static final String TABLA_GRASS="grass";
    public static final String GRASS_idgrass="idgrass";
    public static final String GRASS_idusuario="idusuario";
    public static final String GRASS_nombreGrass="nombregrass";
    public static final String GRASS_descripcionGRASS="descripgrass";
    public static final String GRASS_precioGRASS="preciograss";
    public static final String GRASS_direccionGRASS="direcgrass";
    public static final String GRASS_celularGRASS="celulargrass";
    public static final String GRASS_tamañoGRASS="tamañograss";
    public static final String GRASS_estadoGRASS="estadograss";
    public static  final String CREAR_TABLA_GRASS="CREATE TABLE "+TABLA_GRASS+"("+GRASS_idgrass+" integer primary key autoincrement,"+GRASS_idusuario+" integer ,"+GRASS_nombreGrass+" text,"+GRASS_descripcionGRASS+" text,"+GRASS_precioGRASS+" double,"+GRASS_direccionGRASS+" text,"+GRASS_celularGRASS+" int, "+GRASS_tamañoGRASS+" text, "+GRASS_estadoGRASS+" text)";


    //Constantes de campos de tabla RESERVA
    public static final String TABLA_RESERVA="reserva";
    public static final String RESERVA_idreserva="idreserva";
    public static final String RESERVA_idGrass="idgrass";
    public static final String RESERVA_idUsuario="idusuario";
    public static final String RESERVA_nomGrass="nomgrass";
    public static final String RESERVA_nomUsuario="nomusuario";
    public static final String RESERVA_fecha="fecha";
    public static final String RESERVA_horaInicio="horainicio";
    public static final String RESERVA_horaFin="horafin";
    public static final String RESERVA_Costo="costo";
    public static final String RESERVA_horasReservadas="horasreser";
    public static final String RESERVA_reservadosss="reservados";
    public static  final String CREAR_TABLA_RESERVA="CREATE TABLE "+TABLA_RESERVA+"("+RESERVA_idreserva+" integer primary key autoincrement,"+RESERVA_idGrass+" integer ,"+RESERVA_idUsuario+" integer,"+RESERVA_nomGrass+" text,"+RESERVA_nomUsuario+" text,"+RESERVA_fecha+" text,"+RESERVA_horaInicio+" integer,"+RESERVA_horaFin+" integer,"+RESERVA_Costo+" double, "+RESERVA_horasReservadas+" integer,"+RESERVA_reservadosss+" integer)";

    //Constantes de campos de CANCELADOS
    public static final String TABLA_CANCELADOS="cancelados";
    public static final String CANCELADOS_idcancelados="idcancelados";
    public static final String CANCELADOS_idreserva="idreserva";
    public static final String CANCELADOS_motivo="motivo";
    public static final String CREAR_TABLA_CANCELADOS="CREATE TABLE "+TABLA_CANCELADOS+"("+CANCELADOS_idcancelados+" integer primary key autoincrement,"+CANCELADOS_idreserva+" integer, "+CANCELADOS_motivo+" text)";

}
