# ReservaFácil - Aplicación Híbrida

Esta es una aplicación móvil híbrida desarrollada con PhoneGap/Cordova que permite a los usuarios realizar reservas en diferentes tipos de negocios locales.

## Características

- Listado de negocios disponibles
- Sistema de reservas en tiempo real
- Gestión de reservas del usuario
- Interfaz de usuario moderna y responsiva
- Almacenamiento local de datos
- Diseño adaptativo para diferentes dispositivos

## Tecnologías Utilizadas

- HTML5
- CSS3 (con Bootstrap 5)
- JavaScript (ES6+)
- PhoneGap/Cordova
- Ionicons para iconos
- LocalStorage para persistencia de datos

## Requisitos Previos

Para desarrollar y ejecutar esta aplicación, necesitarás:

- Node.js (versión 14 o superior)
- npm (incluido con Node.js)
- PhoneGap CLI (`npm install -g phonegap`)
- Android Studio (para desarrollo en Android)
- Xcode (para desarrollo en iOS, solo en macOS)

## Instalación

1. Clona este repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd app-hybrida
   ```

2. Instala las dependencias:
   ```bash
   npm install
   ```

3. Añade la plataforma Android:
   ```bash
   phonegap platform add android
   ```

4. Para iOS (solo en macOS):
   ```bash
   phonegap platform add ios
   ```

## Desarrollo

Para iniciar el servidor de desarrollo:

```bash
phonegap serve
```

Esto iniciará un servidor local en `http://localhost:3000` donde podrás probar la aplicación en el navegador.

## Construcción

Para construir la aplicación:

### Android
```bash
phonegap build android
```

El archivo APK se generará en:
`platforms/android/app/build/outputs/apk/debug/app-debug.apk`

### iOS (solo en macOS)
```bash
phonegap build ios
```

## Estructura del Proyecto

```
app-hybrida/
├── config.xml          # Configuración de PhoneGap
├── www/               # Código fuente de la aplicación
│   ├── index.html     # Página principal
│   ├── css/          # Estilos CSS
│   │   └── styles.css
│   ├── js/           # JavaScript
│   │   └── app.js
│   └── res/          # Recursos (iconos, splash screens)
├── platforms/         # Plataformas compiladas
└── plugins/          # Plugins de PhoneGap
```

## Características de la Aplicación

### Página Principal
- Listado de negocios disponibles
- Tarjetas con información detallada
- Sistema de valoración visual
- Botón de reserva rápida

### Sistema de Reservas
- Formulario de reserva intuitivo
- Validación de campos
- Confirmación de reserva
- Gestión de estados (pendiente, confirmada, cancelada)

### Gestión de Reservas
- Listado de reservas del usuario
- Cancelación de reservas
- Visualización del estado
- Historial de reservas

## Contribución

1. Haz un Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## Contacto

Equipo ReservaFácil - dev@reservafacil.com

## Agradecimientos

- Bootstrap por su framework CSS
- Ionicons por los iconos
- PhoneGap/Cordova por la plataforma de desarrollo híbrido 