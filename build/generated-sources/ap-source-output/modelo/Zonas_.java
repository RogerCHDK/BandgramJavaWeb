package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Boletos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-06T14:35:11")
@StaticMetamodel(Zonas.class)
public class Zonas_ { 

    public static volatile CollectionAttribute<Zonas, Boletos> boletosCollection;
    public static volatile SingularAttribute<Zonas, Long> id;
    public static volatile SingularAttribute<Zonas, String> nombre;
    public static volatile SingularAttribute<Zonas, Integer> status;

}