package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.Bandas;
import modelo.Canciones;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-05T16:37:06")
@StaticMetamodel(GenerosMusicales.class)
public class GenerosMusicales_ { 

    public static volatile CollectionAttribute<GenerosMusicales, Canciones> cancionesCollection;
    public static volatile CollectionAttribute<GenerosMusicales, Artistas> artistasCollection;
    public static volatile SingularAttribute<GenerosMusicales, Long> id;
    public static volatile SingularAttribute<GenerosMusicales, String> nombre;
    public static volatile CollectionAttribute<GenerosMusicales, Bandas> bandasCollection;
    public static volatile SingularAttribute<GenerosMusicales, Integer> status;

}