package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.GenerosMusicales;
import modelo.Integrantes;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-11T14:20:37")
@StaticMetamodel(Bandas.class)
public class Bandas_ { 

    public static volatile SingularAttribute<Bandas, String> foto;
    public static volatile SingularAttribute<Bandas, GenerosMusicales> generoId;
    public static volatile SingularAttribute<Bandas, String> biografia;
    public static volatile SingularAttribute<Bandas, Long> id;
    public static volatile SingularAttribute<Bandas, Artistas> artistaId;
    public static volatile SingularAttribute<Bandas, String> nombre;
    public static volatile CollectionAttribute<Bandas, Integrantes> integrantesCollection;
    public static volatile SingularAttribute<Bandas, Integer> status;

}