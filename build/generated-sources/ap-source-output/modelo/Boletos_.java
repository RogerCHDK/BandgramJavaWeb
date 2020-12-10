package modelo;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Eventos;
import modelo.Zonas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-05T16:37:07")
@StaticMetamodel(Boletos.class)
public class Boletos_ { 

    public static volatile SingularAttribute<Boletos, BigDecimal> precio;
    public static volatile SingularAttribute<Boletos, Eventos> eventoId;
    public static volatile SingularAttribute<Boletos, Long> id;
    public static volatile SingularAttribute<Boletos, Zonas> zonaId;
    public static volatile SingularAttribute<Boletos, Integer> status;

}