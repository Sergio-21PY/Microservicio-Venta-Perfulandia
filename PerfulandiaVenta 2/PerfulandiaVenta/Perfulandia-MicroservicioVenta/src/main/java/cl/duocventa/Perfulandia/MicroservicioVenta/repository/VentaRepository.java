package cl.duocventa.Perfulandia.MicroservicioVenta.repository;

import cl.duocventa.Perfulandia.MicroservicioVenta.model.DetalleVenta;
import cl.duocventa.Perfulandia.MicroservicioVenta.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {
    List<Venta> findAll();
}