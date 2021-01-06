package modelo;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.CategoriasProductos;
import modelo.DetalleCompra;
import modelo.DetalleVenta;
import modelo.FotosProducto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-17T14:57:41")
@StaticMetamodel(Productos.class)
public class Productos_ { 

    public static volatile SingularAttribute<Productos, String> descripcion;
    public static volatile CollectionAttribute<Productos, FotosProducto> fotosProductoCollection;
    public static volatile SingularAttribute<Productos, BigDecimal> precio;
    public static volatile CollectionAttribute<Productos, DetalleCompra> detalleCompraCollection;
    public static volatile SingularAttribute<Productos, Long> id;
    public static volatile SingularAttribute<Productos, Artistas> artistaId;
    public static volatile SingularAttribute<Productos, Integer> stock;
    public static volatile SingularAttribute<Productos, String> nombre;
    public static volatile SingularAttribute<Productos, Integer> status;
    public static volatile CollectionAttribute<Productos, DetalleVenta> detalleVentaCollection;
    public static volatile SingularAttribute<Productos, CategoriasProductos> categoriaId;

}