package com.example.proyectoapp1

import android.os.Bundle
import android.util.Log
import android.util.Log.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoapp1.ProductService
import com.example.proyectoapp1.Products
import com.google.android.gms.analytics.ecommerce.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        obtenerProductos()
    }

    fun obtenerProductos(){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.jsonkeeper.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ProductService::class.java)

        val call= service.getProducts()

        call.enqueue(object: Callback<ProductResponse>{

            override fun onResponse(call: Call<ProductResponse>,response: Response<ProductResponse>)
            {
                if (response.isSuccessful){
                    val products = response.body()?.products
                    products?.forEach{product ->
                        d("MainActivity", "Product: ${product.name} precio: ${product.price}")
                    }
                }else e("MainActivity", "API Error: ${response.code()}")
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                e("MainActivity", "Error: ${t.message}")
            }

        })
    }



fun obtenerListadoProductos (productos: List<Products>): String {
    return if (productos.isNotEmpty()){
        productos.joinToString (separator = ",") { producto ->
            "ID: ${producto.id}, Nombre: ${producto.name}"
        }
    }else {"No hay productos disponibles"
    }
}

//fun setProductService(productService: ProductService){
    //this.productService=productService
//}
}


