package com.cprigus.services.god.persistence.hbm;

/**
 *
 * @author User
 */
public enum TipoMovimientoEnum {
    REGISTRO_DE_USUARIO("1"),
    ACCESO_AL_SISTEMA("2"),
    RECUPERAR_TU_PASSWORD("3"),
    CONSULTAR_MI_PERFIL("4"),
    REGISTRO_DE_CORO("5"),
    CONSULTAR_HIMNARIO("6"),
    CONSULTAR_CORO("7"),
    SALIR_DEL_SISTEMA("8"),
    VALIDAR_USUARIO("9"),
    VALIDAR_EMAIL("10"),
    ACTUALIZAR_PERFIL("11"),
    ACTUALIZAR_CORO("13"),
    ELIMINAR_CORO("14"),
    ELIMINAR_USUARIO("15"),
    ENVIO_CORREO_CONTACTO("12"),
    ELIMINAR_CORO_PENDIENTE("16"),
    ACTIVAR_CORO_PENDIENTE("17"),
    ELIMINAR_MAILS_FALLIDOS("18");

    private String tipo;

    private TipoMovimientoEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
