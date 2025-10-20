package com.tecnopunto.controller;// declaración del paquete 

 
import com.tecnopunto.model.Producto; 
import com.tecnopunto.service.ProductoService; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.PageRequest; 
import org.springframework.data.domain.Sort; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*; 

 
// Controlador para manejar las operaciones relacionadas con Producto 
// Utiliza la anotación @Controller para indicar que es un controlador de Spring MVC 
// La anotación @RequestMapping define la ruta base para las solicitudes de este controlador 
// En este caso, todas las rutas comenzarán con "/productos" 
@Controller 
@RequestMapping("/productos") 
public class ProductoController { 

    
    @Autowired // Inyección de dependencia del servicio ProductoService 
    // El servicio maneja la lógica de negocio relacionada con los productos 
    // Permite realizar operaciones como listar, guardar, buscar y eliminar productos 
    // La anotación @Autowired permite que Spring inyecte una instancia del servicio en este controlador 
    private ProductoService productoService; 

    
    // Listado paginado 
    @GetMapping 
    public String listarProductos(Model model, 
                                @RequestParam(defaultValue = "0") int page) { 
        Page<Producto> paginaProductos = productoService.listarProductos( 
            PageRequest.of(page, 10, Sort.by("fechaUltimaModificacion").descending()) 
        ); 
    
        model.addAttribute("productos", paginaProductos); 
        model.addAttribute("currentPage", page); 
        model.addAttribute("totalPages", paginaProductos.getTotalPages()); 
        model.addAttribute("totalElements", paginaProductos.getTotalElements()); 
        model.addAttribute("hasNext", paginaProductos.hasNext()); 
        model.addAttribute("hasPrevious", paginaProductos.hasPrevious()); 
    
        return "productos/lista"; 
    } 

    
    // Formulario nuevo producto 
    // Muestra el formulario para crear un nuevo producto 

    // Utiliza la anotación @GetMapping para manejar solicitudes GET a la ruta "/nuevo" 
    // El método recibe un objeto Model para agregar atributos que serán utilizados en la vista 
    // Se crea un nuevo objeto Producto y se agrega al modelo con la clave "producto" 
    // La vista "productos/formulario" se renderiza para mostrar el formulario de creación 
    @GetMapping("/nuevo") 
    public String mostrarFormulario(Model model) { 
        model.addAttribute("producto", new Producto()); 
        return "productos/formulario"; 
    } 

    
    // Guardar producto 
    // Maneja la solicitud POST para guardar un producto 
    // Utiliza la anotación @PostMapping para manejar solicitudes POST a la ruta "/guardar" 
    // El método recibe un objeto Producto a través de @ModelAttribute, que se vincula 
    // con los datos del formulario enviado desde la vista 
    // El servicio ProductoService se utiliza para guardar el producto en la base de datos 
    // Después de guardar, se redirige a la lista de productos para mostrar el producto 
    // recién creado o actualizado 
    @PostMapping("/guardar") 
    public String guardarProducto(@ModelAttribute Producto producto) { 
        productoService.guardar(producto); 
        return "redirect:/productos"; 
    } 

    
    // Editar producto 
    // Muestra el formulario para editar un producto existente 
    // Utiliza la anotación @GetMapping para manejar solicitudes GET a la ruta "/editar/{id}" 
    // El método recibe el ID del producto a través de @PathVariable y un objeto Model 
    // El servicio ProductoService se utiliza para buscar el producto por su ID 
    // Si el producto se encuentra, se agrega al modelo con la clave "producto" 
    // La vista "productos/formulario" se renderiza para mostrar el formulario de edición 
    // Si el producto no se encuentra, la vista mostrará un formulario vacío para crear un nuevo producto 
    @GetMapping("/editar/{id}") 
    public String editarProducto(@PathVariable Long id, Model model) { 
        productoService.buscarPorId(id).ifPresent(p -> model.addAttribute("producto", p)); 
        return "productos/formulario"; 
    } 

    
    // Eliminar producto (cambiado a GET para que funcione con el enlace del modal) 
    // Maneja la solicitud para eliminar un producto por su ID 
    // Utiliza la anotación @GetMapping para manejar solicitudes GET a la ruta "/eliminar/{id}" 
    // El método recibe el ID del producto a través de @PathVariable 
    // El servicio ProductoService se utiliza para eliminar el producto basado en su ID 
    // Después de eliminar, se redirige a la lista de productos para mostrar el estado 
    // actualizado sin el producto eliminado 
    

    @GetMapping("/eliminar/{id}") 
    public String eliminarProducto(@PathVariable Long id) { 
        productoService.eliminar(id); 
        return "redirect:/productos"; 
    } 
} 
