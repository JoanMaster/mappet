package com.tecnopunto.service; 

 
import com.tecnopunto.model.Producto; 
import com.tecnopunto.repository.ProductoRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.stereotype.Service; 
import java.time.LocalDate; 
import java.util.Optional; 

 
// Servicio para manejar operaciones relacionadas con Producto 
@Service 
public class ProductoService { 

 
    @Autowired // Inyección de dependencia del repositorio ProductoRepository 
    private ProductoRepository productoRepository; // Repositorio para realizar operaciones CRUD 

    
    // Métodos del servicio para manejar productos 
    public Page<Producto> listarProductos(Pageable pageable) { // Método para listar productos con paginación 
        // Utiliza el repositorio para obtener una página de productos 
        // El objeto Pageable permite la paginación y ordenamiento de los resultados 
        // Este método devuelve una página de productos según los parámetros de paginación proporcionados 
        return productoRepository.findAll(pageable); 
    } 

    
    // Método para guardar un producto 
    // Si el producto no tiene ID, se establece la fecha de creación 
    // Se actualiza la fecha de última modificación antes de guardar 
    // El producto se guarda en el repositorio 
    public void guardar(Producto producto) { 
        if (producto.getId() == null) { 
            producto.setFechaCreacion(LocalDate.now()); 
        } 
        producto.setFechaUltimaModificacion(LocalDate.now()); 
        productoRepository.save(producto); 
    } 

    
    // Método para buscar un producto por su ID 
    // Utiliza el repositorio para encontrar un producto por su ID 
    // Devuelve un Optional que puede contener el producto encontrado o estar vacío si no se encuentra 
    public Optional<Producto> buscarPorId(Long id) { 
        return productoRepository.findById(id); 
    } 

    
    // Método para eliminar un producto por su ID 
    // Utiliza el repositorio para eliminar un producto basado en su ID 
    // Si el producto con el ID especificado no existe, no se realiza ninguna acción 
    // Este método no devuelve ningún valor, ya que la eliminación es una operación void 
    public void eliminar(Long id) { 
        productoRepository.deleteById(id); 
    } 
} 
 
