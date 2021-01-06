package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-17T14:57:41")
@StaticMetamodel(Videos.class)
public class Videos_ { 

    public static volatile SingularAttribute<Videos, String> ruta;
    public static volatile SingularAttribute<Videos, Long> id;
    public static volatile SingularAttribute<Videos, Artistas> artistaId;
    public static volatile SingularAttribute<Videos, String> nombre;
    public static volatile SingularAttribute<Videos, Integer> status;

}