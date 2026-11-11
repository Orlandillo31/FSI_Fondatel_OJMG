USE fondatel;

CREATE TABLE IF NOT EXISTS accesos_empleado (
    nip VARCHAR(30) COLLATE utf8_bin NOT NULL,
    empleados_codigo VARCHAR(20) NOT NULL,
    sal VARBINARY(16) NOT NULL,
    clave_hash VARBINARY(32) NOT NULL,
    PRIMARY KEY (nip),
    UNIQUE KEY uq_accesos_empleado (empleados_codigo),
    CONSTRAINT fk_acceso_empleado FOREIGN KEY (empleados_codigo)
        REFERENCES empleados (codigo) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
