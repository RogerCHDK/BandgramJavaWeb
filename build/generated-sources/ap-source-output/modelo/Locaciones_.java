package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Estados;
import modelo.Eventos;
import modelo.Municipios;
import modelo.Paises;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-11T14:20:37")
@StaticMetamodel(Locaciones.class)
public class Locaciones_ { 

    public static volatile SingularAttribute<Locaciones, Paises> paisId;
    public static volatile SingularAttribute<Locaciones, String> calle;
    public static volatile SingularAttribute<Locaciones, Estados> estadoId;
    public static volatile SingularAttribute<Locaciones, Long> id;
    public static volatile CollectionAttribute<Locaciones, Eventos> eventosCollection;
    public static volatile SingularAttribute<Locaciones, String> nombre;
    public static volatile SingularAttribute<Locaciones, Municipios> municipioId;
    public static volatile SingularAttribute<Locaciones, String> colonia;
    public static volatile SingularAttribute<Locaciones, Integer> status;

}