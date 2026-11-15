package com.inmobiliaria.security;

import java.lang.annotation.*;

/**
 * Marca un endpoint como protegido.
 *  - Sin esta anotación el endpoint es público.
 *  - @RequiereRol            -> basta con haber iniciado sesión.
 *  - @RequiereRol({Roles.ADMINISTRADOR}) -> además el usuario debe tener alguno de esos roles.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiereRol {
    String[] value() default {};
}
