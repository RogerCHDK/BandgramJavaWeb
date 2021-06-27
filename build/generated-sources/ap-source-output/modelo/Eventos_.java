package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Artistas;
import modelo.Boletos;
import modelo.FotosEvento;
import modelo.Locaciones;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-25T13:21:28")
@StaticMetamodel(Eventos.class)
public class Eventos_ { 

    public static volatile SingularAttribute<Eventos, String> descripcion;
    public static volatile CollectionAttribute<Eventos, FotosEvento> fotosEventoCollection;
    public static volatile CollectionAttribute<Eventos, Boletos> boletosCollection;
    public static volatile SingularAttribute<Eventos, Date> fechaInicio;
    public static volatile SingularAttribute<Eventos, Locaciones> locacionId;
    public static volatile SingularAttribute<Eventos, Date> fechaCreacion;
    public static volatile SingularAttribute<Eventos, Long> id;
    public static volatile SingularAttribute<Eventos, Artistas> artistaId;
    public static volatile SingularAttribute<Eventos, Date> horaInicio;
    public static volatile SingularAttribute<Eventos, Integer> status;

}