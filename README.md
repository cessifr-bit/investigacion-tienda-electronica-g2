# Universidad Tecnológica de El Salvador 
# Proyecto de Investigación - Ciclo 02-2026
**Asignatura: Progrmación III - Sección: 03**

# “Análisis del rendimiento de consultas y operaciones de gestión de inventarios en una aplicación para una tienda de productos electrónicos”

---
![Estado](https://img.shields.io/badge/Estado-Entrega%20Final-success)
![Versión](https://img.shields.io/badge/Versi%C3%B3n-v1.0-blue)
![Java](https://img.shields.io/badge/Java-25-orange)

##  Integrantes del Grupo
| Nombre Completo | Usuario de GitHub |
| :--- | :--- |
| Darlyn Julissa Cortez Herrera | [@DarlynCortez](https://github.com/DarlynCortez) |
| Cesia Esmeralda Funes Ruiz | [@cessifr-bit](https://github.com/cessifr-bit) |
| David Enrique Gil Martínez | [@davidmartinez311](https://github.com/davidmartinez311) |
| Adrián De Jesús Jacinto Vega | [@AdrianJJV](https://github.com/AdrianJJV) |
| Hazel Daniela Jiménez Martínez | [@HazelJM](https://github.com/HazelJM) |
| Abner Danilo Morales Fuentes | [@Danilo-4](https://github.com/Danilo-4) |
| Gerardo Emilio Recinos Miranda | [@g_rm0805](https://github.com/g_rm0805) |

---

## Descripción del proyecto
Este repositorio contiene la investigación y el desarrollo de una aplicación móvil de comercio electrónico enfocada en una tienda de hardware y productos electrónicos. El proyecto busca analizar empíricamente cómo el rendimiento de las operaciones de consulta y gestión de productos influye en el tiempo de respuesta del sistema y en la eficiencia del procesamiento de la información. 

La investigación aporta evidencia técnica (mediante métricas de tiempo y memoria) que permite optimizar la arquitectura de las aplicaciones móviles, mejorando la experiencia de los usuarios y facilitando la administración eficiente de inventarios (productos, precios, características) en escenarios de bases de datos con volúmenes de 100 a 200 registros.

---

## Objetivo general
**Analizar el rendimiento de las operaciones de consulta y gestión de productos en una aplicación móvil de comercio electrónico.**

---
##  Resumen de hallazgos
A través de las pruebas de rendimiento ejecutadas con *JMH Profiler* y la validación en el cliente móvil, se obtuvieron las siguientes conclusiones técnicas:
* **Acceso a Datos:** La implementación de consultas mediante JDBC (SQL Nativo) presentó una latencia significativamente menor en milisegundos por operación (ms/op) frente al uso de frameworks ORM tradicionales, logrando un control directo sobre la ejecución.
* **Consumo de Memoria:** Al utilizar volúmenes de 100 a 200 registros, la capa de persistencia en *Kotlin* optimizó el recolector de basura (GC) evitando saturaciones en la memoria del *Heap*.
* **Renderizado Móvil:** La adopción del paradigma declarativo *Jetpack Compose* junto con gráficos generados nativamente (Canvas) en la barra de navegación, eliminó el *overhead* de imágenes estáticas, manteniendo una tasa estable de 60 FPS al hacer *scroll* sobre el catálogo.
  
  ---
##  Tecnologías utilizadas
- **Lenguajes:** Kotlin / Java 25
- **Frontend móvil:** Android con Jetpack Compose
- **Backend:** Kotlin con Javalin (API REST)
- **Acceso a datos:** JDBC y patrón DAO
- **Base de datos:** MySQL
- **Benchmarking:** Java Microbenchmark Harness (JMH)
- **Entorno de desarrollo:** IntelliJ IDEA 2026
- **Control de versiones:** Git y GitHub

---

# GUÍA DE INSTALACIÓN, CONFIGURACIÓN Y EJECUCIÓN DEL PROYECTO TIENDA ELECTRÓNICA

## Introducción

Esta guía describe el procedimiento necesario para instalar, configurar y ejecutar el proyecto **Tienda_electronica**.

Se explican los pasos para preparar el entorno de desarrollo, clonar el repositorio desde GitHub, configurar las herramientas necesarias, iniciar los servicios del sistema y conectar la aplicación móvil con el servidor backend.

Además, se presentan las diferentes alternativas para ejecutar la aplicación Android, utilizando un emulador o un teléfono físico mediante una conexión USB o una dirección IP.

---

## 1. Requisitos previos

Antes de comenzar, es necesario contar con los siguientes programas y herramientas instalados:

- **IntelliJ IDEA:** entorno de desarrollo integrado para trabajar con el backend.
- **IntelliJ IDEA con soporte Android:** entorno para abrir y ejecutar la aplicación móvil.
- **Git:** herramienta para clonar y administrar el repositorio.
- **Microsoft OpenJDK 25:** versión de Java utilizada por el proyecto, compatible con Gradle 9.3.1.
- **Android Plugin:** complemento necesario para desarrollar aplicaciones Android en IntelliJ IDEA.
- **Android SDK:** herramientas necesarias para compilar y ejecutar la aplicación móvil.
- **Android Emulator:** herramienta para crear y utilizar dispositivos Android virtuales.
- **XAMPP:** herramienta para iniciar el servicio de MySQL.

## 2. Clonar el repositorio desde GitHub

Para obtener los archivos del proyecto, se debe clonar el repositorio desde GitHub.

1. Abrir IntelliJ IDEA.
2. Seleccionar la opción **Get from VCS**.
3. Ingresar la URL del repositorio de GitHub.
4. Seleccionar la ubicación donde se guardará el proyecto.
5. Hacer clic en **Clone**.
6. Esperar a que IntelliJ IDEA termine de descargar los archivos.

Una vez finalizado el proceso, se abrirá el proyecto Tienda_electronica.

## 3. Configurar y abrir los proyectos

El proyecto está dividido en dos partes principales: el backend y la aplicación Android.

### 3.1. Configuración del backend

1. Ubicar la carpeta `backend-app/Proyecto-backend`.
2. Abrir el proyecto en IntelliJ IDEA.
3. Esperar a que IntelliJ IDEA cargue las dependencias y configure el proyecto.
4. Verificar que el proyecto reconozca correctamente el JDK 25.

### 3.2. Configuración de la aplicación Android

1. Ubicar la carpeta `app-android` dentro del repositorio.
2. Buscar el archivo `settings.gradle.kts`.
3. Si aparece la opción **Link Gradle Project**, seleccionarla.
4. Esperar a que Gradle sincronice todos los archivos y dependencias del proyecto.

> **Importante:** Las carpetas `backend-app/Proyecto-backend` y `app-android` se deben abrir por separado, en distintas ventanas de sus respectivos entornos de desarrollo.

## 4. Configurar Java y Gradle

Para evitar errores de compatibilidad entre Java y Gradle, se debe configurar la versión correspondiente.

1. Abrir IntelliJ IDEA.
2. Entrar en **File → Settings**.
3. Dirigirse a **Build, Execution, Deployment → Build Tools → Gradle**.
4. Buscar la opción **Gradle JVM**.
5. Seleccionar **Microsoft OpenJDK 25**.
6. Presionar **Apply** y después **OK**.
7. Ejecutar **Reload All Gradle Projects** para sincronizar nuevamente el proyecto, si corresponde.

## 5. Iniciar y configurar la base de datos MySQL

Antes de ejecutar el servidor backend, es necesario iniciar y configurar la base de datos.

### 5.1. Iniciar MySQL

1. Abrir XAMPP.
2. Buscar el servicio MySQL.
3. Presionar el botón **Start**.
4. Verificar que el servicio aparezca como iniciado.
5. Ingresar al gestor de MySQL que se esté utilizando.
6. Comprobar que exista la base de datos `TiendaTecnologiaDB`.
7. Verificar que la base de datos tenga cargadas las tablas y los registros necesarios para el funcionamiento de la aplicación.

### 5.2. Configurar las credenciales de MySQL en ConexionDB

Debe verificar que las credenciales configuradas en el proyecto coincidan con las de su instalación local de MySQL.

1. Abrir el proyecto del backend en IntelliJ IDEA.
2. Buscar el archivo `ConexionDB`.
3. Abrir la clase para revisar los parámetros de conexión a MySQL.
4. Identificar las variables correspondientes al usuario y la contraseña.
5. Si MySQL tiene una contraseña configurada, colocar esa misma contraseña en la variable correspondiente del archivo `ConexionDB`.
6. Si el usuario de MySQL no tiene contraseña, dejar el valor de la contraseña vacío.
7. Verificar que el nombre de la base de datos coincida con `TiendaTecnologiaDB`.
8. Guardar los cambios.

**Ejemplo de configuración:**

```java
private static final String URL =
    "jdbc:mysql://localhost:3306/TiendaTecnologiaDB";

private static final String USER = "root";

private static final String PASSWORD = "tu_contraseña";
```

> **Importante:** El ejemplo es ilustrativo. Se deben conservar los nombres de las variables y la estructura que ya utiliza el proyecto.

La base de datos debe estar iniciada y disponible antes de levantar el servidor backend.

## 6. Ejecutar el servidor backend (Javalin)

Después de iniciar MySQL y configurar las credenciales, se debe levantar el servidor backend, que funciona como intermediario entre la aplicación Android y la base de datos.

1. Abrir IntelliJ IDEA.
2. Abrir el proyecto ubicado en `backend-app/Proyecto-backend`.
3. Buscar el archivo `ServidorApiKt`.
4. Ejecutarlo presionando el botón verde **Run**.
5. Esperar a que el servidor termine de iniciar.

Si el servidor se ejecuta correctamente, en la consola aparecerá el siguiente mensaje:

```text
API lista en http://localhost:8080/api/productos
```

Este mensaje indica que el servidor backend está disponible y preparado para recibir solicitudes de la aplicación Android.

> **Importante:** Mantener IntelliJ IDEA abierto y el servidor ejecutándose durante toda la prueba. No se debe detener el proceso mientras se utiliza la aplicación móvil.

## 7. Crear y configurar un emulador Android

Para ejecutar la aplicación móvil sin utilizar un teléfono físico, se debe crear un dispositivo virtual desde Android Studio.

1. Abrir el proyecto `app-android`.
2. Buscar la opción **Device Manager**.
3. Seleccionar **Create Virtual Device**.
4. Elegir la categoría **Phone**.
5. Seleccionar un dispositivo, por ejemplo, Pixel 6.
6. Elegir una imagen del sistema Android disponible. Si es necesario, descargarla.
7. Finalizar la configuración del dispositivo virtual.
8. En Device Manager, presionar el botón de reproducción para iniciar el emulador.
9. Esperar hasta que aparezca la pantalla principal de Android.

Una vez iniciado, el emulador funcionará como un teléfono Android virtual en el que se podrá instalar y probar la aplicación.

## 8. Configurar la conexión entre el teléfono Android y el servidor backend

Existen diferentes formas de conectar la aplicación Android con el servidor backend. Se puede utilizar un emulador Android o un teléfono físico.

### 8.1. Opción A: Conexión mediante emulador Android

Cuando se utiliza el emulador oficial de Android Studio, se debe configurar la dirección especial que permite comunicarse con la computadora.

**Procedimiento:**

1. Abrir el proyecto `app-android` 
2. Buscar el archivo `RetrofitClient.kt`.
3. Identificar la constante `BASE_URL`.
4. Configurar la dirección de la siguiente manera:

```kotlin
const val BASE_URL = "http://10.0.2.2:8080/api/"
```

5. Guardar los cambios.
6. Verificar que el servidor backend esté ejecutándose.

> **Importante:** La dirección `10.0.2.2` permite que el emulador Android acceda al servidor que se ejecuta en la computadora. No se debe utilizar `localhost` en esta configuración, porque dentro del emulador hace referencia al propio dispositivo virtual.

### 8.2. Opción B: Conexión mediante dirección IP (teléfono físico)

Esta alternativa permite conectar un teléfono Android al backend utilizando la dirección IP local de la computadora, sin necesidad de utilizar ADB Reverse.

**Paso 1. Conectar los dispositivos a la misma red**

1. Conectar la computadora a una red Wi-Fi.
2. Conectar el teléfono Android a la misma red Wi-Fi.
3. Verificar que ambos dispositivos puedan comunicarse dentro de la red local.

**Paso 2. Obtener la dirección IP de la computadora**

1. Abrir PowerShell o CMD en Windows.
2. Ejecutar el siguiente comando:

```powershell
ipconfig
```

3. Buscar el apartado correspondiente al adaptador Wi-Fi o Ethernet que se está utilizando.
4. Identificar el campo **Dirección IPv4**.
5. Copiar la dirección IP que aparece.

Por ejemplo:

```text
192.168.1.15
```

> La dirección anterior es solamente un ejemplo. Debe utilizar la dirección IP de su propia computadora.

**Paso 3. Configurar la dirección en RetrofitClient.kt**


1. Abrir el archivo `RetrofitClient.kt`.
2. Buscar la constante `BASE_URL`.
3. Sustituir la dirección anterior por la IP obtenida en el paso anterior.

**Ejemplo:**

```kotlin
const val BASE_URL = "http://192.168.1.15:8080/api/"
```

5. Guardar los cambios.

**Paso 4. Verificar la configuración del servidor**

Para que el teléfono pueda acceder al backend mediante la red local:

- El servidor Javalin debe aceptar conexiones desde otros dispositivos de la red.
- Se debe permitir el acceso al puerto `8080` en el Firewall de Windows para la red privada.
- Ambos dispositivos deben permanecer conectados a la misma red.

Una vez configurado, el teléfono podrá comunicarse directamente con el servidor utilizando la dirección IP de la computadora.

> **Importante:** Si la computadora cambia de red o de dirección IP, será necesario actualizar nuevamente la dirección configurada en `BASE_URL`.

### 8.3. Opción C: Conexión mediante localhost utilizando cable USB (ADB Reverse)

Esta alternativa permite conectar un teléfono físico mediante un cable USB y utilizar localhost para comunicarse con el servidor backend.

**Paso 1. Activar la depuración USB**

1. Abrir los ajustes del teléfono Android.
2. Buscar la sección **Acerca del teléfono**.
3. Presionar varias veces sobre **Número de compilación** hasta activar las opciones de desarrollador.
4. Regresar a los ajustes del teléfono.
5. Entrar en **Opciones de desarrollador**.
6. Activar **Depuración USB**.
7. Conectar el teléfono a la computadora mediante un cable USB.
8. Aceptar el permiso de depuración USB cuando aparezca en la pantalla del teléfono.

**Paso 2. Configurar el túnel USB**

1. Mantener el teléfono conectado mediante el cable USB.
2. Abrir PowerShell o la terminal de Windows.
3. Ejecutar el siguiente comando:

```powershell
$adb = (Get-ChildItem -Path C:\ -Filter adb.exe -Recurse -ErrorAction SilentlyContinue | Select-Object -First 1).FullName; & $adb reverse tcp:8080 tcp:8080
```

4. Presionar Enter.
5. Verificar que la terminal muestre el puerto 8080 como resultado.

**Paso 3. Configurar la dirección en RetrofitClient.kt**

1. Abrir el proyecto `app-android`.
2. Buscar el archivo `RetrofitClient.kt`.
3. Configurar la constante `BASE_URL` de la siguiente manera:

```kotlin
const val BASE_URL = "http://localhost:8080/api/"
```

4. Guardar los cambios.

Con esta configuración, las solicitudes realizadas desde el teléfono hacia `localhost:8080` serán redirigidas al servidor backend que se está ejecutando en la computadora.

> **Importante:** Si se desconecta el cable USB, será necesario volver a conectar el teléfono y ejecutar nuevamente el comando ADB Reverse.

## 9. Ejecutar la aplicación Android

Una vez que la base de datos esté iniciada, el backend se encuentre ejecutándose y se haya configurado el tipo de conexión elegido, se puede proceder a ejecutar la aplicación móvil.

1. Abrir el proyecto `app-android` 
2. Esperar a que termine la sincronización de Gradle.
3. Seleccionar el dispositivo de ejecución:
   - Emulador Android.
   - Teléfono físico conectado mediante USB.
4. Verificar que esté seleccionada la configuración **Android App**.
5. Comprobar que esté seleccionado el módulo `app`.
6. Presionar el botón verde **Run**.
7. Esperar a que Gradle compile el proyecto.
8. Esperar a que la aplicación se instale y se abra automáticamente en el dispositivo seleccionado.

Cuando finalice el proceso, la aplicación estará lista para utilizarse y realizar solicitudes al backend, que a su vez consultará la información almacenada en MySQL.

## 10. Orden correcto para ejecutar el proyecto

Para garantizar el funcionamiento correcto de todos los componentes, se debe respetar el siguiente orden:

1. Iniciar XAMPP y activar el servicio MySQL.
2. Verificar que exista la base de datos `TiendaTecnologiaDB`.
3. Revisar las credenciales de MySQL en el archivo `ConexionDB`.
4. Abrir IntelliJ IDEA y ejecutar el servidor backend mediante `ServidorApiKt`.
5. Confirmar que aparezca el mensaje de inicio de la API en el puerto 8080.
6. Abrir el proyecto `app-android`.
7. Seleccionar el método de conexión que se utilizará: emulador, IP o localhost mediante ADB Reverse.
8. Configurar la dirección `BASE_URL` correspondiente al método elegido.
9. Encender el emulador o conectar el teléfono físico.
10. Ejecutar la aplicación presionando **Run**.

> **Nota:** Durante las pruebas, se deben mantener activos MySQL, el servidor backend y el dispositivo Android utilizado.

## 11. Solución de problemas comunes

### Error de conexión con MySQL

- Comprobar que MySQL esté iniciado en XAMPP.
- Verificar que exista la base de datos `TiendaTecnologiaDB`.
- Revisar las credenciales configuradas en `ConexionDB`.
- Comprobar que la contraseña corresponda al usuario configurado en MySQL.

### El servidor backend no inicia

- Verificar que MySQL esté funcionando.
- Comprobar que las dependencias del proyecto estén instaladas.
- Revisar los errores mostrados en la consola de IntelliJ IDEA.
- Confirmar que el puerto 8080 no esté siendo utilizado por otro proceso.

### El emulador Android no inicia

- Verificar que tenga instalado Android Emulator.
- Comprobar que exista un dispositivo virtual configurado.
- Verificar que la imagen del sistema Android esté descargada.
- Reiniciar el emulador si presenta problemas durante el inicio.

### Error de conexión mediante emulador

- Verificar que la dirección `BASE_URL` utilice `10.0.2.2`.
- Comprobar que el servidor backend continúe ejecutándose.
- Verificar que el puerto 8080 esté disponible.
- Comprobar que la aplicación tenga permiso para utilizar Internet.

### Error de conexión mediante dirección IP

- Comprobar que la computadora y el teléfono estén conectados a la misma red.
- Verificar que la dirección IP configurada sea la correcta.
- Confirmar que Javalin acepte conexiones externas.
- Revisar que el Firewall de Windows permita conexiones al puerto 8080.

### Error de conexión mediante localhost y USB

- Comprobar que el teléfono esté conectado.
- Verificar que la depuración USB esté activada.
- Ejecutar nuevamente el comando ADB Reverse.
- Confirmar que el servidor backend esté ejecutándose.

### La aplicación Android no se ejecuta

- Verificar que esté seleccionada la configuración **Android App**.
- Comprobar que el módulo seleccionado sea `app`.
- Confirmar que exista un dispositivo conectado o un emulador encendido.
- Esperar a que Gradle termine de compilar el proyecto.

## 12. Resultado esperado

Al completar todos los pasos, el proyecto **Tienda_electronica** estará funcionando con sus tres componentes principales:

- **Base de datos MySQL**
- **Servidor backend Javalin**
- **Aplicación Android**

Los tres componentes trabajarán de manera integrada, permitiendo que la aplicación móvil realice solicitudes al servidor backend y que este consulte la información almacenada en la base de datos MySQL.

---
 ## Estructura de carpetas

```
investigacion-tienda-electronica-g2/
│
├── app-android/
│   └── Aplicación móvil Android
│
├── backend-app/
│   └── Proyecto-backend/
│       └── Servidor API REST
│
├── Proyecto-maven/
│
├── docs/
│   ├── Diagrama/
│   ├── informe_final.pdf
│   └── Documentación_Técnica.pdf
│
├── .gitignore
├── CHANGELOG.md
├── LICENSE
└── README.md
```

---
##  Enlace a los documentos en `docs/`
Toda la documentación técnica exigida para el proyecto se encuentra en el directorio `/docs`. Puedes acceder directamente a través de los siguientes enlaces:

**[Informe Final](docs/informe-final.md)**: Documento principal y reporte de la investigación.

**[Informe Final](docs/Informe_final.pdf)**: Documento principal en formato pdf.

**[Documentación Técnica](docs/Documentación_Técnica.pdf)**: Guía de despliegue y ADR Consolidados.

**[Diagramas del Sistema](docs/Diagrama)**: Carpeta que contiene la arquitectura visual.

---
##  Licencia

**Licencia MIT**

Copyright (c) 2026 Equipo de Investigación - Universidad Tecnológica de El Salvador

Se concede permiso, de forma gratuita, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), para utilizar el Software sin restricción, incluyendo sin limitación los derechos de usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender copias del Software, y para permitir a las personas a las que se les proporcione el Software a hacer lo mismo, sujeto a las siguientes condiciones:

El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.

EL SOFTWARE SE PROPORCIONA "EN SU ESTADO ACTUAL", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR Y NO INFRACCIÓN. EN NINGÚN CASO LOS AUTORES O TITULARES DEL COPYRIGHT SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑOS U OTRAS RESPONSABILIDADES, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O CUALQUIER OTRO MOTIVO, QUE SURJA DE, O EN CONEXIÓN CON EL SOFTWARE O EL USO U OTROS TRATOS EN EL SOFTWARE.

