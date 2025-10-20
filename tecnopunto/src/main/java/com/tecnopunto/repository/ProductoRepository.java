package com.tecnopunto.repository; // declaración del paquete 
 

import org.springframework.data.jpa.repository.JpaRepository; // importación de JpaRepository 
import com.tecnopunto.model.Producto; // importación del modelo Producto 
 

// Interfaz ProductoRepository que extiende JpaRepository para operaciones CRUD 
public interface ProductoRepository extends JpaRepository <Producto, Long> { 
} 