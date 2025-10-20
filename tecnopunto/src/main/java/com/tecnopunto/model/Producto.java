package com.tecnopunto.model; 

 
import jakarta.persistence.*; 
import java.math.BigDecimal; 
import java.time.LocalDate; 

 
@Entity 
@Table(name = "productos") // Nombre de la tabla en la base de datos 
public class Producto { 
 

 @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY) // PK auto incremental 
 private Long id; 

 
 @Column(length = 100, nullable = false) 
 private String nombre; 
 

 @Lob 
 private String descripcion; 

 
 @Column(precision = 10, scale = 2, nullable = false) 
 private BigDecimal precio; 

 
 @Column(name = "cantidad_stock", nullable = false) 
 private Integer cantidadStock; 

 
 @Column(name = "fecha_creacion") 
 private LocalDate fechaCreacion; 

 
 @Column(name = "fecha_ultima_modificacion") 
 private LocalDate fechaUltimaModificacion; 

 

 // Getters y Setters 

 public Long getId() { return id; } 
 public void setId(Long id) { this.id = id; } 
 

 public String getNombre() { return nombre; } 
 public void setNombre(String nombre) { this.nombre = nombre; } 
 

 public String getDescripcion() { return descripcion; } 
 public void setDescripcion(String descripcion) { this.descripcion = descripcion; } 
 

 public BigDecimal getPrecio() { return precio; } 
 public void setPrecio(BigDecimal precio) { this.precio = precio; } 

 
 public Integer getCantidadStock() { return cantidadStock; } 
 public void setCantidadStock(Integer cantidadStock) { this.cantidadStock = cantidadStock; } 

 
 public LocalDate getFechaCreacion() { return fechaCreacion; } 
 public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; } 

 
 public LocalDate getFechaUltimaModificacion() { return fechaUltimaModificacion; } 
 public void setFechaUltimaModificacion(LocalDate fechaUltimaModificacion) { this.fechaUltimaModificacion = fechaUltimaModificacion; } 

} 