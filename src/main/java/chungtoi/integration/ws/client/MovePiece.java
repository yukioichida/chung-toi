
package chungtoi.integration.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de movePiece complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="movePiece">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="playerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="actualPosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="movementDirection" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stepSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "movePiece", propOrder = {
    "playerId",
    "actualPosition",
    "movementDirection",
    "stepSize",
    "orientation"
})
public class MovePiece {

    protected int playerId;
    protected int actualPosition;
    protected int movementDirection;
    protected int stepSize;
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
     * Obtém o valor da propriedade actualPosition.
     * 
     */
    public int getActualPosition() {
        return actualPosition;
    }

    /**
     * Define o valor da propriedade actualPosition.
     * 
     */
    public void setActualPosition(int value) {
        this.actualPosition = value;
    }

    /**
     * Obtém o valor da propriedade movementDirection.
     * 
     */
    public int getMovementDirection() {
        return movementDirection;
    }

    /**
     * Define o valor da propriedade movementDirection.
     * 
     */
    public void setMovementDirection(int value) {
        this.movementDirection = value;
    }

    /**
     * Obtém o valor da propriedade stepSize.
     * 
     */
    public int getStepSize() {
        return stepSize;
    }

    /**
     * Define o valor da propriedade stepSize.
     * 
     */
    public void setStepSize(int value) {
        this.stepSize = value;
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
