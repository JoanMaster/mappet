package com.tecnopunto.controller; // declaración del paquete 

 
import org.springframework.stereotype.Controller; // importación de la anotación Controller 
import org.springframework.web.bind.annotation.GetMapping;// importación de la anotación GetMapping 
 

// Controlador para manejar la página de inicio 
// Utiliza la anotación @Controller para indicar que es un controlador de Spring MVC 
// La anotación @GetMapping define la ruta para las solicitudes GET 
@Controller 
public class HomeController { 

    
    @GetMapping("/") 
    public String home() { 
        return "home"; // busca home.html en templates 
    } 
} 
 
