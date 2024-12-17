package org.grisu.captura_videos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único de cada video
    @Column(nullable = false, unique = true)
    private String titulo; // Título del video

    @Column(nullable = false)
    private String urlOrigen; // URL o ruta del video original

    @Column
    private String urlDestino; // URL o ruta del video final procesado (puede ser nulo si aún no se genera)

    @Lob
    private String descripcion; // Descripción larga del video

    private String duracion; // Duración del video en minutos o segundos

    private String idiomaOriginal; // Idioma original del video

    private String idiomaTraduccion; // Idioma traducido, si aplica

    private String capacidad; // Tamaño del archivo del video en MB, como metadato

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUrlOrigen(String urlOrigen) {
        this.urlOrigen = urlOrigen;
    }

    public void setUrlDestino(String urlDestino) {
        this.urlDestino = urlDestino;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public void setIdiomaTraduccion(String idiomaTraduccion) {
        this.idiomaTraduccion = idiomaTraduccion;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
}