<?php

    class Connection_db {
        private static PDO $conexion; //conexión almacenada

        private function __construct($RUTA_XML) {
            if (file_exists($RUTA_XML)) {
                $conf = simplexml_load_file($RUTA_XML);
            } else {
                throw new Exception("[!] No se ha podido abrir el archivo de configuración de la bd");
            }
            $datos = $conf->xpath("//DB_type")[0] 
                . ":dbname=" . $conf->xpath("//DB_name")[0]
                . ";host=" . $conf->xpath("//DB_host")[0];
            $user = $conf->xpath("//USER_admin")[0];
            $pass = $conf->xpath("//USER_password")[0];

            self::$conexion = (new PDO($datos, $user, $pass));
        }

        /**
         * @param string $RUTA_XML Ruta al fichero de configuración (configuracion_db.xml) desde el fichero en el que llamas al método 
         * @return PDO Devuelve la conexión creada usando una conexión existente o nueva en el caso de que sea la primera llamada al método
         */
        public static function get_conexion(string $RUTA_XML): PDO {
            if (isset(self::$conexion)) {
                return self::$conexion;
            } else {
                $conexion = new Connection_db($RUTA_XML);
                return self::$conexion;
            }
        }

    }