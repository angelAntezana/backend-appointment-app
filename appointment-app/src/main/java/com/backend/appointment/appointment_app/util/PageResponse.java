package com.backend.appointment.appointment_app.util;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * Clase genérica que encapsula los datos de una respuesta paginada.
 *
 * <p>Proporciona información sobre el contenido de la página, el número total de páginas,
 * elementos y detalles relacionados con la paginación.</p>
 *
 * @param <T> Tipo de contenido de la página.
 *
 * @author Ángel
 * @version 1.0-SNAPSHOT
 */
import io.swagger.v3.oas.annotations.media.Schema;

public record PageResponse<T>(
        /**
         * Page content.
         */
        @Schema(description = "Page content")
        List<T> content,

        /**
         * Total number of pages available.
         */
        @Schema(description = "Total number of pages available")
        int totalPages,

        /**
         * Número total de elementos en todas las páginas.
         */
        @Schema(description = "Número total de elementos en todas las páginas")
        long totalElements,

        /**
         * Tamaño de la página (número de elementos por página).
         */
        @Schema(description = "Tamaño de la página")
        int pageSize,

        /**
         * Número actual de la página (comienza desde 0).
         */
        @Schema(description = "Número actual de la página")
        int pageNumber,

        /**
         * Número total de elementos en la página actual.
         */
        @Schema(description = "Número total de elementos en la página actual")
        int totalPageElements,

        /**
         * Indica si la página está vacía.
         */
        @Schema(description = "Indica si la página está vacía")
        boolean empty,

        /**
         * Indica si es la primera página.
         */
        @Schema(description = "Indica si es la primera página")
        boolean first,

        /**
         * Indica si es la última página.
         */
        @Schema(description = "Indica si es la última página")
        boolean last,

        /**
         * Campo por el cual se ordenaron los elementos.
         */
        @Schema(description = "Campo por el cual se ordenaron los elementos")
        List<String> sortBy,

        /**
         * Dirección del ordenamiento (ascendente o descendente).
         */
        @Schema(description = "Dirección del ordenamiento (ascendente o descendente)")
        List<String> direction
) {
    /**
     * Construye una instancia de {@code PageResponse} a partir de una página de datos.
     *
     * @param <T> Tipo del contenido de la página.
     * @param page Objeto de tipo {@code Page} que contiene los datos paginados.
     * @param sortBy Campo por el cual se ordenaron los elementos.
     * @param direction Dirección del ordenamiento (ascendente o descendente).
     * @return Una instancia de {@code PageResponse} con los datos proporcionados.
     */
    @Schema(description = "Crea un objeto PageResponse a partir de una instancia de Page")
    public static <T> PageResponse<T> of(Page<T> page, List<String> sortBy, List<String> direction) {
        return new PageResponse<>(
                page.getContent(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.getNumberOfElements(),
                page.isEmpty(),
                page.isFirst(),
                page.isLast(),
                sortBy,
                direction
        );
    }
}
