package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Productos;
import modelo.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-06T14:35:11")
@StaticMetamodel(DetalleVenta.class)
public class DetalleVenta_ { 

    public static volatile SingularAttribute<DetalleVenta, Double> precioUnitario;
    public static volatile SingularAttribute<DetalleVenta, Productos> productoId;
    public static volatile SingularAttribute<DetalleVenta, Ventas> ventaId;
    public static volatile SingularAttribute<DetalleVenta, Long> id;
    public static volatile SingularAttribute<DetalleVenta, Integer> cantidad;
    public static volatile SingularAttribute<DetalleVenta, Integer> status;

}