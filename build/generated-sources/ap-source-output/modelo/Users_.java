package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Compras;
import modelo.Estados;
import modelo.Municipios;
import modelo.Paises;
import modelo.TarjetaCredito;
import modelo.TiposUsuarios;
import modelo.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-17T14:57:41")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile CollectionAttribute<Users, Compras> comprasCollection;
    public static volatile SingularAttribute<Users, Date> fechaNacimiento;
    public static volatile CollectionAttribute<Users, TarjetaCredito> tarjetaCreditoCollection;
    public static volatile SingularAttribute<Users, String> calle;
    public static volatile CollectionAttribute<Users, Ventas> ventasCollection;
    public static volatile SingularAttribute<Users, String> nombre;
    public static volatile SingularAttribute<Users, Municipios> municipioId;
    public static volatile SingularAttribute<Users, String> colonia;
    public static volatile SingularAttribute<Users, Paises> paisId;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> apMaterno;
    public static volatile SingularAttribute<Users, String> genero;
    public static volatile SingularAttribute<Users, Estados> estadoId;
    public static volatile SingularAttribute<Users, TiposUsuarios> tipoUsuario;
    public static volatile SingularAttribute<Users, Long> id;
    public static volatile SingularAttribute<Users, String> apPaterno;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, Integer> status;

}