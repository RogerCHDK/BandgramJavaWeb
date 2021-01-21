package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-20T18:34:34")
@StaticMetamodel(TiposUsuarios.class)
public class TiposUsuarios_ { 

    public static volatile CollectionAttribute<TiposUsuarios, Artistas> artistasCollection;
    public static volatile SingularAttribute<TiposUsuarios, Long> id;
    public static volatile CollectionAttribute<TiposUsuarios, Users> usersCollection;
    public static volatile SingularAttribute<TiposUsuarios, String> nombre;
    public static volatile SingularAttribute<TiposUsuarios, Integer> nivel;
    public static volatile SingularAttribute<TiposUsuarios, Integer> status;

}