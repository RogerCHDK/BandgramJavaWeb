package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.Bandas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-17T14:57:41")
@StaticMetamodel(Integrantes.class)
public class Integrantes_ { 

    public static volatile SingularAttribute<Integrantes, Bandas> bandaId;
    public static volatile SingularAttribute<Integrantes, Long> id;
    public static volatile SingularAttribute<Integrantes, Artistas> artistaId;
    public static volatile SingularAttribute<Integrantes, Integer> status;

}