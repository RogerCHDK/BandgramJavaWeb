package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Productos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-25T13:21:28")
@StaticMetamodel(CategoriasProductos.class)
public class CategoriasProductos_ { 

    public static volatile CollectionAttribute<CategoriasProductos, Productos> productosCollection;
    public static volatile SingularAttribute<CategoriasProductos, Long> id;
    public static volatile SingularAttribute<CategoriasProductos, String> nombre;
    public static volatile SingularAttribute<CategoriasProductos, Integer> status;

}