package cl.duocventa.Perfulandia.MicroservicioVenta.controller;

import cl.duocventa.Perfulandia.MicroservicioVenta.model.Venta;
import cl.duocventa.Perfulandia.MicroservicioVenta.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaservice;

    @PostMapping
    public Venta crearVenta(@RequestBody Venta venta) {
        return ventaservice.registrarVenta(venta);
    }

    @PostMapping("/con-detalles")
    public ResponseEntity<Venta> crearVentaConDetalles(@RequestBody Venta ventaConDetalles) {
        try {
            Venta ventaCreada = ventaservice.registrarVentaConDetalles(ventaConDetalles);
            return ResponseEntity.status(HttpStatus.CREATED).body(ventaCreada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<Venta> listar() {
        List<Venta> ventas = ventaservice.obtenerTodas();
        System.out.println("Ventas encontradas: " + ventas.size());
        return ventas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVenta(@PathVariable String id) {
        return ventaservice.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        ventaservice.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}



