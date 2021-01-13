package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Estados;
import modelo.Locaciones;
import modelo.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-13T14:00:30")
@StaticMetamodel(Municipios.class)
public class Municipios_ { 

    public static volatile CollectionAttribute<Municipios, Locaciones> locacionesCollection;
    public static volatile SingularAttribute<Municipios, Estados> estadoId;
    public static volatile SingularAttribute<Municipios, Long> id;
    public static volatile CollectionAttribute<Municipios, Users> usersCollection;
    public static volatile SingularAttribute<Municipios, String> nombre;
    public static volatile SingularAttribute<Municipios, Integer> status;

}