
package chungtoi.integration.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de isMyTurn complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="isMyTurn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="playerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isMyTurn", propOrder = {
    "playerId"
})
public class IsMyTurn {

    protected int playerId;

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

}
