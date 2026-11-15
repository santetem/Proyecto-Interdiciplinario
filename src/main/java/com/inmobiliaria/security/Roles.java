package com.inmobiliaria.security;

/** Nombres de los roles EXACTAMENTE como están en la tabla roles de la base (se compara sin distinguir mayúsculas). */
public final class Roles {
    public static final String CLIENTE = "Cliente";
    public static final String PROPIETARIO = "Propietario";
    public static final String AGENTE = "Agente";
    public static final String ADMINISTRADOR = "Administrador";
    public static final String GERENTE = "Gerente";
    public static final String PERSONAL_FRANQUICIA = "PersonalFranquicia";

    private Roles() { }
}
