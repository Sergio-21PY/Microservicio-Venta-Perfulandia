
package cl.duocventa.Perfulandia.MicroservicioVenta.repository;

import cl.duocventa.Perfulandia.MicroservicioVenta.model.DetalleVenta;
import cl.duocventa.Perfulandia.MicroservicioVenta.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {}
