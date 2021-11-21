/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

/**
 *
 * @author daviddaza
 */
public class ProductoCar {
 
    private Integer pdtProductoid;
    private String pdtNombre;
    private String pdtDescripcion;
    private Double pdtValorventa;
    private Integer pdtTotal;
    private String pdtImagen;
    private Integer cantidad;
    

    public ProductoCar() {    
    
    }

    public Integer getPdtProductoid() {
        return pdtProductoid;
    }

    public void setPdtProductoid(Integer pdtProductoid) {
        this.pdtProductoid = pdtProductoid;
    }

    public String getPdtNombre() {
        return pdtNombre;
    }

    public void setPdtNombre(String pdtNombre) {
        this.pdtNombre = pdtNombre;
    }

    public String getPdtDescripcion() {
        return pdtDescripcion;
    }

    public void setPdtDescripcion(String pdtDescripcion) {
        this.pdtDescripcion = pdtDescripcion;
    }

    public Double getPdtValorventa() {
        return pdtValorventa;
    }

    public void setPdtValorventa(Double pdtValorventa) {
        this.pdtValorventa = pdtValorventa;
    }

    public Integer getPdtTotal() {
        return pdtTotal;
    }

    public void setPdtTotal(Integer pdtTotal) {
        this.pdtTotal = pdtTotal;
    }

    public String getPdtImagen() {
        return pdtImagen;
    }

    public void setPdtImagen(String pdtImagen) {
        this.pdtImagen = pdtImagen;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    
}
