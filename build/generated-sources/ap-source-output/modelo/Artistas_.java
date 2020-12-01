package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Bandas;
import modelo.Canciones;
import modelo.Compras;
import modelo.Eventos;
import modelo.GenerosMusicales;
import modelo.Integrantes;
import modelo.Productos;
import modelo.TiposUsuarios;
import modelo.Videos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-30T21:59:52")
@StaticMetamodel(Artistas.class)
public class Artistas_ { 

    public static volatile CollectionAttribute<Artistas, Canciones> cancionesCollection;
    public static volatile CollectionAttribute<Artistas, Compras> comprasCollection;
    public static volatile SingularAttribute<Artistas, GenerosMusicales> generoId;
    public static volatile SingularAttribute<Artistas, String> biografia;
    public static volatile CollectionAttribute<Artistas, Productos> productosCollection;
    public static volatile SingularAttribute<Artistas, String> nombre;
    public static volatile CollectionAttribute<Artistas, Bandas> bandasCollection;
    public static volatile CollectionAttribute<Artistas, Integrantes> integrantesCollection;
    public static volatile CollectionAttribute<Artistas, Videos> videosCollection;
    public static volatile SingularAttribute<Artistas, String> password;
    public static volatile SingularAttribute<Artistas, String> foto;
    public static volatile SingularAttribute<Artistas, String> apMaterno;
    public static volatile SingularAttribute<Artistas, TiposUsuarios> tipoUsuario;
    public static volatile SingularAttribute<Artistas, Long> id;
    public static volatile CollectionAttribute<Artistas, Eventos> eventosCollection;
    public static volatile SingularAttribute<Artistas, String> apPaterno;
    public static volatile SingularAttribute<Artistas, String> email;
    public static volatile SingularAttribute<Artistas, Integer> status;

}