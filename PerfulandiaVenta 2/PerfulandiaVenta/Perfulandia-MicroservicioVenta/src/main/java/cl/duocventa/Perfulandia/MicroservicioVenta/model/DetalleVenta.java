package cl.duocventa.Perfulandia.MicroservicioVenta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Table(name = "detalle_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

    @Id
    private String id_detalle;

    private String id_producto;

    private Integer cantidad;

    private int precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_ventas")
    @JsonBackReference
    private Venta venta;
}