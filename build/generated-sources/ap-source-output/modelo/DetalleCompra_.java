package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Compras;
import modelo.Productos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-05T16:37:07")
@StaticMetamodel(DetalleCompra.class)
public class DetalleCompra_ { 

    public static volatile SingularAttribute<DetalleCompra, Productos> productoId;
    public static volatile SingularAttribute<DetalleCompra, Double> precioCompra;
    public static volatile SingularAttribute<DetalleCompra, Long> id;
    public static volatile SingularAttribute<DetalleCompra, Integer> cantidad;
    public static volatile SingularAttribute<DetalleCompra, Double> precioVenta;
    public static volatile SingularAttribute<DetalleCompra, Compras> compraId;
    public static volatile SingularAttribute<DetalleCompra, Integer> status;

}