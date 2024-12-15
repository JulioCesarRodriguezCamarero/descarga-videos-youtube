package org.grisu.captura_videos.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único de cada video

    private String titulo; // Título del video

    @Column(nullable = false)
    private String urlOrigen; // URL o ruta del video original

    @Column
    private String urlDestino; // URL o ruta del video final procesado (puede ser nulo si aún no se genera)

    @Lob
    private String descripcion; // Descripción larga del video

    private Double duracion; // Duración del video en minutos o segundos

    private String idiomaOriginal; // Idioma original del video

    private String idiomaTraduccion; // Idioma traducido, si aplica

    private BigDecimal capacidad; // Tamaño del archivo del video en MB, como metadato
}