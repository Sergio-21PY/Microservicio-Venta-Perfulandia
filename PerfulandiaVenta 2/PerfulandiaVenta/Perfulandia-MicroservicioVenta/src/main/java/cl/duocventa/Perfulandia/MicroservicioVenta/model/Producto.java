package cl.duocventa.Perfulandia.MicroservicioVenta.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    private String id_producto;

    private int valor_unitario;

    // getters y setters
}
