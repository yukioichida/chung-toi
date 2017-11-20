
package chungtoi.integration.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de preRegister complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="preRegister">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="player1Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="player1Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="player2Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="player2Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preRegister", propOrder = {
    "player1Name",
    "player1Id",
    "player2Name",
    "player2Id"
})
public class PreRegister {

    protected String player1Name;
    protected int player1Id;
    protected String player2Name;
    protected int player2Id;

    /**
     * Obtém o valor da propriedade player1Name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayer1Name() {
        return player1Name;
    }

    /**
     * Define o valor da propriedade player1Name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayer1Name(String value) {
        this.player1Name = value;
    }

    /**
     * Obtém o valor da propriedade player1Id.
     * 
     */
    public int getPlayer1Id() {
        return player1Id;
    }

    /**
     * Define o valor da propriedade player1Id.
     * 
     */
    public void setPlayer1Id(int value) {
        this.player1Id = value;
    }

    /**
     * Obtém o valor da propriedade player2Name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayer2Name() {
        return player2Name;
    }

    /**
     * Define o valor da propriedade player2Name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayer2Name(String value) {
        this.player2Name = value;
    }

    /**
     * Obtém o valor da propriedade player2Id.
     * 
     */
    public int getPlayer2Id() {
        return player2Id;
    }

    /**
     * Define o valor da propriedade player2Id.
     * 
     */
    public void setPlayer2Id(int value) {
        this.player2Id = value;
    }

}
