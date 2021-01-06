package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.GenerosMusicales;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-17T14:57:41")
@StaticMetamodel(Canciones.class)
public class Canciones_ { 

    public static volatile SingularAttribute<Canciones, String> foto;
    public static volatile SingularAttribute<Canciones, String> ruta;
    public static volatile SingularAttribute<Canciones, GenerosMusicales> generoId;
    public static volatile SingularAttribute<Canciones, String> album;
    public static volatile SingularAttribute<Canciones, Long> id;
    public static volatile SingularAttribute<Canciones, Artistas> artistaId;
    public static volatile SingularAttribute<Canciones, String> nombre;
    public static volatile SingularAttribute<Canciones, Integer> status;

}