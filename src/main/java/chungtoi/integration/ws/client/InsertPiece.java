
package chungtoi.integration.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de insertPiece complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="insertPiece">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="playerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orientation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertPiece", propOrder = {
    "playerId",
    "position",
    "orientation"
})
public class InsertPiece {

    protected int playerId;
    protected int position;
    protected int orientation;

    /**
     * Obtém o valor da propriedade playerId.
     * 
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Define o valor da propriedade playerId.
     * 
     */
    public void setPlayerId(int value) {
        this.playerId = value;
    }

    /**
     * Obtém o valor da propriedade position.
     * 
     */
    public int getPosition() {
        return position;
    }

    /**
     * Define o valor da propriedade position.
     * 
     */
    public void setPosition(int value) {
        this.position = value;
    }

    /**
     * Obtém o valor da propriedade orientation.
     * 
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * Define o valor da propriedade orientation.
     * 
     */
    public void setOrientation(int value) {
        this.orientation = value;
    }

}
