package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleVenta;
import modelo.TarjetaCredito;
import modelo.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-06T14:35:11")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile SingularAttribute<Ventas, Date> fecha;
    public static volatile SingularAttribute<Ventas, Double> total;
    public static volatile SingularAttribute<Ventas, Double> iva;
    public static volatile SingularAttribute<Ventas, Double> subtotal;
    public static volatile SingularAttribute<Ventas, TarjetaCredito> tarjetaCreditoId;
    public static volatile SingularAttribute<Ventas, Long> id;
    public static volatile SingularAttribute<Ventas, Users> userId;
    public static volatile SingularAttribute<Ventas, Integer> status;
    public static volatile CollectionAttribute<Ventas, DetalleVenta> detalleVentaCollection;

}