package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Locaciones;
import modelo.Municipios;
import modelo.Paises;
import modelo.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-25T13:21:28")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile SingularAttribute<Estados, Paises> paisId;
    public static volatile CollectionAttribute<Estados, Locaciones> locacionesCollection;
    public static volatile SingularAttribute<Estados, Long> id;
    public static volatile CollectionAttribute<Estados, Users> usersCollection;
    public static volatile SingularAttribute<Estados, String> nombre;
    public static volatile CollectionAttribute<Estados, Municipios> municipiosCollection;
    public static volatile SingularAttribute<Estados, Integer> status;

}