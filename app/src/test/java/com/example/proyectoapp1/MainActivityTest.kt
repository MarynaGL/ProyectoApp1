package com.example.proyectoapp1

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class MainActivityTest {
    private val activity=MainActivity()

    @Test
    fun `Dado un listado de productos, validar que la informacion spuede mostrar or la pantalla`(){

        val productos = listOf(
            Products(
                id = 1,
                name = "Product A",
                description = "This is a simple product.",
                price = 10.99,
                currency = "USD",
                in_stock = false
            ),
            Products(
                id = 2,
                name = "Product B",
                description = "The latest Samsung phone",
                price = 899.99,
                currency = "USD",
                in_stock = true
            )

        )
        val resultado = activity.obtenerListadoProductos (productos)

        assertEquals(
                """
                ID: 1, Nombre: Product A,ID: 2, Nombre: Product B
                """.trimIndent(),

                resultado
        )
    }

    @Test
    fun `Cuando el listado esté vacio, validar que no se pueda mostrar informacion en pantalla`(){
        val productos = emptyList<Products>()
        val resultado =activity.obtenerListadoProductos(productos)

        assertEquals("No hay productos disponibles", resultado)
    }
}