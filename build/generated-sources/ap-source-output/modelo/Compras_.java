package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.DetalleCompra;
import modelo.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-05T16:37:07")
@StaticMetamodel(Compras.class)
public class Compras_ { 

    public static volatile SingularAttribute<Compras, Date> fecha;
    public static volatile SingularAttribute<Compras, Double> total;
    public static volatile CollectionAttribute<Compras, DetalleCompra> detalleCompraCollection;
    public static volatile SingularAttribute<Compras, Double> iva;
    public static volatile SingularAttribute<Compras, Double> subtotal;
    public static volatile SingularAttribute<Compras, Long> id;
    public static volatile SingularAttribute<Compras, Artistas> artistaId;
    public static volatile SingularAttribute<Compras, Users> userId;
    public static volatile SingularAttribute<Compras, Integer> status;

}