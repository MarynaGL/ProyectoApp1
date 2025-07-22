              package com.example.proyectoapp1

              data class Products(
                  val id:Int,
                  val name: String,
                  val description: String,
                  val price: Double,
                  val currency: String,
                  val in_stock: Boolean
              )

              data class ProductResponse(
                  val products: List<Products>
              )