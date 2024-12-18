# Comenzando
### Documentación de Referencia
Para más información, por favor considera las siguientes secciones:
* [Documentación oficial de Apache Maven](https://maven.apache.org/guides/index.html)
* [Guía de referencia del plugin de Spring Boot Maven](https://docs.spring.io/spring-boot/3.4.0/maven-plugin)
* [Crear una imagen OCI](https://docs.spring.io/spring-boot/3.4.0/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.0/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.0/reference/using/devtools.html)
### Guías
Las siguientes guías ilustran cómo usar algunas funciones de manera concreta:
* [Acceder a datos con JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Acceder a datos con MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Sobrescrituras del POM Padre en Maven
Debido al diseño de Maven, algunos elementos se heredan desde el archivo POM padre al archivo POM del proyecto.
Aunque la mayoría de las herencias son adecuadas, también se heredan elementos no deseados como `<license>` y `<developers>` del padre.
Para prevenir esto, el POM del proyecto contiene sobrescrituras vacías para estos elementos.
Si decides cambiar manualmente a un padre diferente y realmente deseas la herencia, necesitarás eliminar esas sobrescrituras.

# Importante:
## programas_yt-dlp
Descarga los archivos yt-dlp, ffmpeg, ffplay, ffprobe
Los encontrarás en el enlace de abajo
Crea un directorio y pega los archivos
Entra en variables de entorno y edita el PATH, introduce la ruta donde has creadoel direcctorio
Estos programas son importantes para la descarga de videos desde la web

### Más información
* https://www.rapidseedbox.com/es/blog/yt-dlp-complete-guide
