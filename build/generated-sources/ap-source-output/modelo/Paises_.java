package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Estados;
import modelo.Locaciones;
import modelo.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-13T14:00:30")
@StaticMetamodel(Paises.class)
public class Paises_ { 

    public static volatile CollectionAttribute<Paises, Estados> estadosCollection;
    public static volatile CollectionAttribute<Paises, Locaciones> locacionesCollection;
    public static volatile SingularAttribute<Paises, Long> id;
    public static volatile CollectionAttribute<Paises, Users> usersCollection;
    public static volatile SingularAttribute<Paises, String> nombre;
    public static volatile SingularAttribute<Paises, Integer> status;

}