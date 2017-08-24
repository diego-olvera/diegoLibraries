//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.08.08 a las 12:16:58 PM CDT 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para place complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="place">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gas_prices" type="{}gas_price" maxOccurs="unbounded"/>
 *         &lt;element name="place_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "place", propOrder = {
    "gasPrices",
    "placeId"
})
public class Place {

    @XmlElement(name = "gas_prices", required = true)
    protected List<GasPrice> gasPrices;
    @XmlElement(name = "place_id")
    protected long placeId;

    /**
     * Gets the value of the gasPrices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gasPrices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGasPrices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GasPrice }
     * 
     * 
     */
    public List<GasPrice> getGasPrices() {
        if (gasPrices == null) {
            gasPrices = new ArrayList<GasPrice>();
        }
        return this.gasPrices;
    }

    /**
     * Obtiene el valor de la propiedad placeId.
     * 
     */
    public long getPlaceId() {
        return placeId;
    }

    /**
     * Define el valor de la propiedad placeId.
     * 
     */
    public void setPlaceId(long value) {
        this.placeId = value;
    }

}
