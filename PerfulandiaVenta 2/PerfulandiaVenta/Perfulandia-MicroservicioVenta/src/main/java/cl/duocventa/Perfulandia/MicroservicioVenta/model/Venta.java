package cl.duocventa.Perfulandia.MicroservicioVenta.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="VENTAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    private String id_ventas;

    private LocalDateTime fecha;

    private int total;

    @Column(nullable = false)
    private String estado;

    private String id_empleado;

    private String id_cliente;

    @Column(name = "id_envio")
    private String id_envio;  // puede ser null

    @Column(name = "id_factura")
    private String id_factura;  // puede ser null

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetalleVenta> detalles;


    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}
