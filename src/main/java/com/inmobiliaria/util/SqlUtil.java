package com.inmobiliaria.util;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Arma literales SQL seguros para los stored procedures genéricos
 * (sp_General_Insertar / sp_General_Actualizar), que reciben las columnas y
 * valores como TEXTO. Antes los Services concatenaban los datos del usuario
 * directamente ("'" + direccion + "'"), lo que permitía SQL Injection.
 * Ahora TODO valor pasa por estos métodos, que escapan comillas y barras.
 */
public final class SqlUtil {

    private SqlUtil() { }

    /** Texto entre comillas simples y escapado. null -> NULL. */
    public static String texto(String valor) {
        if (valor == null) {
            return "NULL";
        }
        String limpio = valor.replace("\0", "")
                             .replace("\\", "\\\\")
                             .replace("'", "''");
        return "'" + limpio + "'";
    }

    /** Número (Integer, BigDecimal, Double...). null -> NULL. Rechaza NaN/Infinity. */
    public static String numero(Number valor) {
        if (valor == null) {
            return "NULL";
        }
        try {
            return new BigDecimal(valor.toString()).toPlainString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Número inválido: " + valor);
        }
    }

    /** Fecha ISO (yyyy-MM-dd) entre comillas. null -> NULL. */
    public static String fecha(LocalDate valor) {
        return valor == null ? "NULL" : "'" + valor + "'";
    }
}
