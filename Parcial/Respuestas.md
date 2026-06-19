¿Por qué la estructura nueva de búsqueda es más rápida que la búsqueda lineal anterior?

Porque ahora los vehículos se guardan en una estructura que permite encontrarlos directamente usando la patente como clave. Antes había que recorrer toda la lista hasta encontrar el vehículo buscado, mientras que ahora la búsqueda es mucho más rápida y no depende de la cantidad de vehículos almacenados.

¿Cómo el algoritmo de deduplicación de alertas GPS evita el problema de los bucles anidados en términos de rendimiento?

Se utilizó una estructura que permite saber rápidamente si un reporte GPS ya fue agregado o no. De esta forma cada reporte se procesa una sola vez y no es necesario compararlo con todos los demás, evitando los bucles anidados y mejorando el rendimiento cuando la cantidad de reportes es grande.

¿Cómo resolvieron a nivel código el problema de ordenamiento natural sin romper la posibilidad de ordenar por tarifas de forma concurrente en memoria?

Se definió un ordenamiento natural para los vehículos según el porcentaje de batería utilizando "Comparable". Además, para ordenar por tarifa se implementó un "Comparator" independiente. De esta manera se pueden usar distintos criterios de ordenamiento sin modificar la clase principal ni afectar otros ordenamientos que se necesiten realizar.




