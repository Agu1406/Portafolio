# Guía de Conventional Commits

Esta guía es algo personal que llevo mejorando en cada repositorio ajeno o propio donde trabajo, creo que es importante documentar y
commitear de forma coherente al trabajo hecho y por eso, siguiendo
las mejores practicas que me han enseñado en mis practicas, en los
estudios y mis compañeros y amigos he dado con este documento con
ejemplos practicos y explicaciones sencilllas.

## Estructura Básica

Aquí muestro la estructura básica, se entienda mejor con los ejemplos
posteiores si no se conoce mucho del temá:

tipo(alcance): descripción corta

[descripción larga opcional]

[pie opcional con referencias]

El pie no solo es opcional, por ejemplo, cuando se trabaja con
metodologías agiles se puede usar para "cerrar" incidencias o
tickets.

## Tipos Principales

- feat: Nuevas características o funcionalidades
- fix: Corrección de errores
- docs: Cambios en documentación
- style: Cambios de formato (espacios, indentación, etc.)
- refactor: Refactorización de código
- test: Añadir o modificar tests
- chore: Tareas de mantenimiento
- perf: Mejoras de rendimiento
- ci: Cambios en integración continua
- build: Cambios en sistema de build


## Ejemplos Prácticos

### 1. Feature Nueva

feat(auth): implementar login con Google

- Añadir botón de login con Google
- Configurar OAuth2
- Añadir manejo de tokens
- Crear página de perfil de usuario

Closes #45

### 2. Corrección de Bug

fix(formulario): corregir validación de email

El formulario aceptaba emails sin '@'.
Añadida expresión regular para validación correcta.

Fixes #123

### 3. Documentación

docs(readme): actualizar instrucciones de instalación

- Añadir requisitos previos
- Actualizar comandos de instalación
- Incluir troubleshooting común

### 4. Refactorización

refactor(structure): convertir estructura completa a kebab-case

- Renombrar 1,247 archivos y carpetas a convención kebab-case
- Refactorizar estructura completa de DAW1 y DAW2
- Convertir nombres con espacios y camelCase a formato estándar
- Actualizar referencias internas en archivos de código
- Corregir .gitignore con nuevas rutas refactorizadas

Mejora legibilidad, compatibilidad entre sistemas operativos
y adherencia a estándares web modernos.

### 5. Tareas de Mantenimiento

chore(deps): actualizar dependencias del proyecto

- Actualizar Spring Boot de 2.7.0 a 2.7.1
- Actualizar Bootstrap de 5.1.3 a 5.2.0
- Actualizar jQuery de 3.6.0 a 3.6.1
- Limpiar dependencias no utilizadas

Resuelve vulnerabilidades de seguridad menores y mejora
la estabilidad del proyecto.

## Buenas Prácticas

1. Primera Línea

   - Usar verbos en imperativo
   - Máximo 50 caracteres
   - No terminar con punto

2. Descripción Detallada

   - Separar del título con línea en blanco
   - Explicar el "qué" y el "por qué"
   - Usar viñetas para mejor legibilidad

3. Referencias

   - Usar "Closes", "Fixes" o "Resolves" para cerrar issues
   - Referenciar otros issues con #número
   - Mencionar breaking changes

## Recordatorio Final

- Ser específico y claro
- Pensar en quien leerá el commit
- Mantener consistencia en el estilo
- Separar cambios grandes en commits más pequeños
