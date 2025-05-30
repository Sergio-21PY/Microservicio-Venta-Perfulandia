package cl.duocventa.Perfulandia.MicroservicioVenta.repository;

import cl.duocventa.Perfulandia.MicroservicioVenta.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, String> {}
