package cl.duocventa.Perfulandia.MicroservicioVenta.services;

import cl.duocventa.Perfulandia.MicroservicioVenta.model.DetalleVenta;
import cl.duocventa.Perfulandia.MicroservicioVenta.model.Venta;
import cl.duocventa.Perfulandia.MicroservicioVenta.repository.DetalleVentaRepository;
import cl.duocventa.Perfulandia.MicroservicioVenta.repository.ProductoRepository;
import cl.duocventa.Perfulandia.MicroservicioVenta.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService   {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    // Registrar una nueva venta
    public Venta registrarVenta(Venta venta) {
        venta.setFecha(LocalDateTime.now());

        // Si no se define el estado, se asume "PAGADA"
        if (venta.getEstado() == null || venta.getEstado().isBlank()) {
            venta.setEstado("PAGADA");
        }
        return ventaRepository.save(venta);
    }

    // Obtener todas las ventas
    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    // Obtener una venta por su ID
    public Optional<Venta> obtenerPorId(String idVenta) {
        return ventaRepository.findById(idVenta);
    }

    // Eliminar una venta
    public void eliminarVenta(String idVenta) {
        ventaRepository.deleteById(idVenta);
    }

    // Anular una venta (cambia el estado, no elimina)
    public Venta anularVenta(String idVenta) {
        Optional<Venta> optional = ventaRepository.findById(idVenta);
        if (optional.isPresent()) {
            Venta venta = optional.get();
            venta.setEstado("ANULADA");
            return ventaRepository.save(venta);
        } else {
            throw new RuntimeException("Venta no encontrada con ID: " + idVenta);
        }
    }


    public Venta registrarVentaConDetalles(Venta venta) {
        int total = 0;

        for (DetalleVenta detalle : venta.getDetalles()) {
            var producto = productoRepository.findById(detalle.getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalle.getId_producto()));

            int precioReal = producto.getValor_unitario();
            detalle.setPrecioUnitario(precioReal);
            detalle.setVenta(venta);
            total += precioReal * detalle.getCantidad();
        }

        venta.setFecha(LocalDateTime.now());
        if (venta.getEstado() == null || venta.getEstado().isBlank()) {
            venta.setEstado("PAGADA");
        }
        venta.setTotal(total);

        return ventaRepository.save(venta);
    }
}
