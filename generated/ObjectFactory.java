//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.08.08 a las 12:16:58 PM CDT 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GasPrice_QNAME = new QName("", "gas_price");
    private final static QName _Places_QNAME = new QName("", "places");
    private final static QName _Place_QNAME = new QName("", "place");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GasPrice }
     * 
     */
    public GasPrice createGasPrice() {
        return new GasPrice();
    }

    /**
     * Create an instance of {@link Places }
     * 
     */
    public Places createPlaces() {
        return new Places();
    }

    /**
     * Create an instance of {@link Place }
     * 
     */
    public Place createPlace() {
        return new Place();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GasPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gas_price")
    public JAXBElement<GasPrice> createGasPrice(GasPrice value) {
        return new JAXBElement<GasPrice>(_GasPrice_QNAME, GasPrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Places }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "places")
    public JAXBElement<Places> createPlaces(Places value) {
        return new JAXBElement<Places>(_Places_QNAME, Places.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Place }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "place")
    public JAXBElement<Place> createPlace(Place value) {
        return new JAXBElement<Place>(_Place_QNAME, Place.class, null, value);
    }

}
