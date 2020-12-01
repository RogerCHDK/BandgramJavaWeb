package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Users;
import modelo.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-30T21:59:52")
@StaticMetamodel(TarjetaCredito.class)
public class TarjetaCredito_ { 

    public static volatile SingularAttribute<TarjetaCredito, Long> numero;
    public static volatile CollectionAttribute<TarjetaCredito, Ventas> ventasCollection;
    public static volatile SingularAttribute<TarjetaCredito, Integer> mes;
    public static volatile SingularAttribute<TarjetaCredito, Long> id;
    public static volatile SingularAttribute<TarjetaCredito, Users> userId;
    public static volatile SingularAttribute<TarjetaCredito, String> titular;
    public static volatile SingularAttribute<TarjetaCredito, Integer> anio;
    public static volatile SingularAttribute<TarjetaCredito, Integer> status;

}