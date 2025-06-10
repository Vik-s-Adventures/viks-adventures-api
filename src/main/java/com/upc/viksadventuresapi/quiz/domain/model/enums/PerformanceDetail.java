package com.upc.viksadventuresapi.quiz.domain.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PerformanceDetail {
    C1D01(1, "Expresa su comprensión sobre las fracciones como parte-todo involucrando cantidades discretas desde su representación simbólica hacia su representación gráfica."),
    C1D02(2, "Expresa su comprensión sobre la fracción como operador involucrando cantidades continuas, a partir de su representación simbólica."),
    C1D03(3, "Expresa su comprensión sobre las fracciones como medida involucrando cantidades continuas, desde su representación gráfica hacia su representación simbólica."),
    C1D04(4, "Emplea diversas estrategias para establecer equivalencias entre unidades de masa."),
    C1D05(5, "Establece relaciones entre los datos y condiciones de situaciones vinculadas al reparto de cantidades. Las transforma a expresiones (numéricas, gráficas o simbólicas) involucrando los criterios de divisibilidad de números naturales y las resuelve."),
    C1D06(6, "Establece relaciones entre los datos y condiciones de situaciones vinculadas a las acciones de igualar cantidades. Las transforma a expresiones (numéricas, gráficas o simbólicas) que involucran el uso de fracciones y las resuelve."),
    C1D07(7, "Establece relaciones entre datos y condiciones de situaciones en las que se tiene que determinar la parte de un total. Las transforma a expresiones (numéricas, gráficas o simbólicas) que le permitirán interpretar el valor del porcentaje como el valor relativo de una cantidad y las resuelve."),
    C1D08(8, "Establece relaciones entre los datos y condiciones de situaciones vinculadas a una combinación de acciones de repetir y separar cantidades. Las transforma a expresiones numéricas (numéricas, gráficas o simbólicas) que involucra el uso de números decimales y las resuelve."),
    C1D09(9, "Argumenta la validez de una afirmación vinculada al descuento porcentual de una cantidad en situaciones de su entorno y la sustenta a través de ejemplos."),
    C1D10(10, "Emplea diversas estrategias vinculadas al canje para establecer nuevas equivalencias entre cantidades."),
    C2D01(11, "Evalúa valores numéricos que cumplen las condiciones de una desigualdad entre cantidades en situaciones diversas dado un soporte gráfico."),
    C2D02(12, "Interpreta el significado de los elementos que componen una función afín a partir de un modelo gráfico."),
    C2D03(13, "Modela situaciones vinculadas a una función afín, a través de representaciones algebraicas, a partir de una tabla dada."),
    C2D04(14, "Emplea diversas estrategias para determinar valores intermedios de un patrón de repetición vinculado a la rotación de una figura."),
    C2D05(15, "Interpreta información proporcionada por una gráfica que representa una relación de dependencia lineal entre dos magnitudes asociadas a situaciones de su entorno."),
    C2D06(16, "Evalúa la validez de afirmaciones vinculadas a situaciones en las que se establecen relaciones de proporcionalidad entre magnitudes."),
    C3D01(17, "Establece relaciones entre los datos y condiciones de situaciones vinculadas a una regularidad entre dos magnitudes. Las transforma en expresiones (numéricas o gráficas) que involucran la interpretación de patrones numéricos crecientes (progresiones aritméticas) utilizando números naturales y las resuelve."),
    C3D02(18, "Argumenta la validez de una afirmación vinculada a situaciones que involucran la comprensión de una relación de proporcionalidad directa entre dos magnitudes a partir de una tabla de valores."),
    C3D03(19, "Establece relaciones entre los datos y condiciones de situaciones vinculadas a las características y atributos medibles de objetos reales o imaginarios. Las asocia con las propiedades básicas de triángulos y las resuelve."),
    C3D04(20, "Evalúa la validez de afirmaciones que involucran la relación entre los elementos de un prisma triangular en situaciones de su entorno."),
    C3D05(21, "Identifica triángulos de acuerdo a su clasificación (por medida de sus lados o de sus ángulos) dado un soporte gráfico."),
    C3D06(22, "Interpreta las características de la rotación de una figura en un plano sin cuadrículas dado un soporte gráfico."),
    C3D07(23, "Establece relaciones entre las características de una forma tridimensional y sus tres diferentes vistas (frontal, lateral y superior)."),
    C3D08(24, "Justifica afirmaciones vinculadas a la relación entre el área y el perímetro de un rectángulo presentado en un plano con cuadrículas, utilizando algunos ejemplos."),
    C4D01(25, "Interpreta información de una situación que ha sido representada en un gráfico estadístico de barras dobles."),
    C4D02(26, "Expresa con diversas representaciones su comprensión sobre lo que es un suceso seguro, posible o imposible en una situación aleatoria."),
    C4D03(27, "Representa en un gráfico circular información que ha sido organizada y presentada en una tabla de frecuencias."),
    C4D04(28, "Establece relaciones entre los datos y condiciones de situaciones vinculadas al análisis del comportamiento de un conjunto de datos estadísticos no agrupados. Las asocia a la interpretación de su media aritmética y las resuelve.");

    private final int value;
    private final String description;

    PerformanceDetail(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static PerformanceDetail fromValue(int value) {
        return Arrays.stream(values())
                .filter(d -> d.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor de PerformanceDetail inválido: " + value));
    }
}