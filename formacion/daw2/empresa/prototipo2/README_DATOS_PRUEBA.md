# Instrucciones para importar datos de prueba

Este archivo contiene las instrucciones para importar los datos de prueba en la aplicación Manos a la Olla.

## 1. Importar la base de datos

1. Abre phpMyAdmin o tu gestor de base de datos preferido
2. Selecciona la base de datos `manosalaolla`
3. Importa el archivo `datos_prueba.sql`

## 2. Imágenes necesarias

### Imágenes de usuarios
Necesitas las siguientes imágenes en el directorio `public/img/users/`:

1. `admin.jpg` - Foto de perfil del administrador
2. `maria.jpg` - Foto de perfil de María García
3. `default.jpg` - Imagen por defecto para usuarios sin foto

### Imágenes de recetas
Necesitas las siguientes imágenes en el directorio `public/img/recipes/`:

1. `paella.jpg` - Imagen de la paella valenciana
2. `gazpacho.jpg` - Imagen del gazpacho andaluz
3. `tortilla.jpg` - Imagen de la tortilla de patatas
4. `crema-catalana.jpg` - Imagen de la crema catalana
5. `pan-casero.jpg` - Imagen del pan casero
6. `default-recipe.jpg` - Imagen por defecto para recetas sin foto

## 3. Credenciales de prueba

### Usuario Administrador
- Email: admin@manosalaolla.com
- Contraseña: password

### Usuario Normal
- Email: maria@example.com
- Contraseña: password

## 4. Notas importantes

1. Las contraseñas están hasheadas en la base de datos usando bcrypt
2. Las imágenes deben estar en formato JPG
3. Las imágenes deben tener un tamaño razonable (se recomienda máximo 1MB por imagen)
4. Los nombres de archivo son sensibles a mayúsculas/minúsculas

## 5. Enlaces para descargar las imágenes

Para las imágenes de las recetas, puedes usar fotos gratuitas de:
- [Unsplash](https://unsplash.com/es)
- [Pexels](https://www.pexels.com/es-es/)
- [Pixabay](https://pixabay.com/es/)

Asegúrate de renombrar las imágenes según los nombres especificados anteriormente. 