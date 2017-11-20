
package chungtoi.integration.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the chungtoi.integration.ws.client package. 
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

    private final static QName _HasGameResponse_QNAME = new QName("http://ws.integration.chungtoi/", "hasGameResponse");
    private final static QName _EndGame_QNAME = new QName("http://ws.integration.chungtoi/", "endGame");
    private final static QName _EndGameResponse_QNAME = new QName("http://ws.integration.chungtoi/", "endGameResponse");
    private final static QName _CreatePlayer_QNAME = new QName("http://ws.integration.chungtoi/", "createPlayer");
    private final static QName _IsMyTurn_QNAME = new QName("http://ws.integration.chungtoi/", "isMyTurn");
    private final static QName _MovePiece_QNAME = new QName("http://ws.integration.chungtoi/", "movePiece");
    private final static QName _HasGame_QNAME = new QName("http://ws.integration.chungtoi/", "hasGame");
    private final static QName _MovePieceResponse_QNAME = new QName("http://ws.integration.chungtoi/", "movePieceResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://ws.integration.chungtoi/", "helloResponse");
    private final static QName _GetGameStatusResponse_QNAME = new QName("http://ws.integration.chungtoi/", "getGameStatusResponse");
    private final static QName _InsertPiece_QNAME = new QName("http://ws.integration.chungtoi/", "insertPiece");
    private final static QName _InsertPieceResponse_QNAME = new QName("http://ws.integration.chungtoi/", "insertPieceResponse");
    private final static QName _CreatePlayerResponse_QNAME = new QName("http://ws.integration.chungtoi/", "createPlayerResponse");
    private final static QName _IsMyTurnResponse_QNAME = new QName("http://ws.integration.chungtoi/", "isMyTurnResponse");
    private final static QName _PreRegister_QNAME = new QName("http://ws.integration.chungtoi/", "preRegister");
    private final static QName _GetOppositePlayerResponse_QNAME = new QName("http://ws.integration.chungtoi/", "getOppositePlayerResponse");
    private final static QName _Hello_QNAME = new QName("http://ws.integration.chungtoi/", "hello");
    private final static QName _PreRegisterResponse_QNAME = new QName("http://ws.integration.chungtoi/", "preRegisterResponse");
    private final static QName _GetGameStatus_QNAME = new QName("http://ws.integration.chungtoi/", "getGameStatus");
    private final static QName _GetOppositePlayer_QNAME = new QName("http://ws.integration.chungtoi/", "getOppositePlayer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: chungtoi.integration.ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EndGame }
     * 
     */
    public EndGame createEndGame() {
        return new EndGame();
    }

    /**
     * Create an instance of {@link EndGameResponse }
     * 
     */
    public EndGameResponse createEndGameResponse() {
        return new EndGameResponse();
    }

    /**
     * Create an instance of {@link HasGameResponse }
     * 
     */
    public HasGameResponse createHasGameResponse() {
        return new HasGameResponse();
    }

    /**
     * Create an instance of {@link HasGame }
     * 
     */
    public HasGame createHasGame() {
        return new HasGame();
    }

    /**
     * Create an instance of {@link MovePieceResponse }
     * 
     */
    public MovePieceResponse createMovePieceResponse() {
        return new MovePieceResponse();
    }

    /**
     * Create an instance of {@link MovePiece }
     * 
     */
    public MovePiece createMovePiece() {
        return new MovePiece();
    }

    /**
     * Create an instance of {@link IsMyTurn }
     * 
     */
    public IsMyTurn createIsMyTurn() {
        return new IsMyTurn();
    }

    /**
     * Create an instance of {@link CreatePlayer }
     * 
     */
    public CreatePlayer createCreatePlayer() {
        return new CreatePlayer();
    }

    /**
     * Create an instance of {@link IsMyTurnResponse }
     * 
     */
    public IsMyTurnResponse createIsMyTurnResponse() {
        return new IsMyTurnResponse();
    }

    /**
     * Create an instance of {@link CreatePlayerResponse }
     * 
     */
    public CreatePlayerResponse createCreatePlayerResponse() {
        return new CreatePlayerResponse();
    }

    /**
     * Create an instance of {@link GetGameStatusResponse }
     * 
     */
    public GetGameStatusResponse createGetGameStatusResponse() {
        return new GetGameStatusResponse();
    }

    /**
     * Create an instance of {@link InsertPiece }
     * 
     */
    public InsertPiece createInsertPiece() {
        return new InsertPiece();
    }

    /**
     * Create an instance of {@link InsertPieceResponse }
     * 
     */
    public InsertPieceResponse createInsertPieceResponse() {
        return new InsertPieceResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link GetGameStatus }
     * 
     */
    public GetGameStatus createGetGameStatus() {
        return new GetGameStatus();
    }

    /**
     * Create an instance of {@link GetOppositePlayer }
     * 
     */
    public GetOppositePlayer createGetOppositePlayer() {
        return new GetOppositePlayer();
    }

    /**
     * Create an instance of {@link GetOppositePlayerResponse }
     * 
     */
    public GetOppositePlayerResponse createGetOppositePlayerResponse() {
        return new GetOppositePlayerResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link PreRegisterResponse }
     * 
     */
    public PreRegisterResponse createPreRegisterResponse() {
        return new PreRegisterResponse();
    }

    /**
     * Create an instance of {@link PreRegister }
     * 
     */
    public PreRegister createPreRegister() {
        return new PreRegister();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "hasGameResponse")
    public JAXBElement<HasGameResponse> createHasGameResponse(HasGameResponse value) {
        return new JAXBElement<HasGameResponse>(_HasGameResponse_QNAME, HasGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "endGame")
    public JAXBElement<EndGame> createEndGame(EndGame value) {
        return new JAXBElement<EndGame>(_EndGame_QNAME, EndGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "endGameResponse")
    public JAXBElement<EndGameResponse> createEndGameResponse(EndGameResponse value) {
        return new JAXBElement<EndGameResponse>(_EndGameResponse_QNAME, EndGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePlayer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "createPlayer")
    public JAXBElement<CreatePlayer> createCreatePlayer(CreatePlayer value) {
        return new JAXBElement<CreatePlayer>(_CreatePlayer_QNAME, CreatePlayer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsMyTurn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "isMyTurn")
    public JAXBElement<IsMyTurn> createIsMyTurn(IsMyTurn value) {
        return new JAXBElement<IsMyTurn>(_IsMyTurn_QNAME, IsMyTurn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MovePiece }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "movePiece")
    public JAXBElement<MovePiece> createMovePiece(MovePiece value) {
        return new JAXBElement<MovePiece>(_MovePiece_QNAME, MovePiece.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "hasGame")
    public JAXBElement<HasGame> createHasGame(HasGame value) {
        return new JAXBElement<HasGame>(_HasGame_QNAME, HasGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MovePieceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "movePieceResponse")
    public JAXBElement<MovePieceResponse> createMovePieceResponse(MovePieceResponse value) {
        return new JAXBElement<MovePieceResponse>(_MovePieceResponse_QNAME, MovePieceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "getGameStatusResponse")
    public JAXBElement<GetGameStatusResponse> createGetGameStatusResponse(GetGameStatusResponse value) {
        return new JAXBElement<GetGameStatusResponse>(_GetGameStatusResponse_QNAME, GetGameStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPiece }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "insertPiece")
    public JAXBElement<InsertPiece> createInsertPiece(InsertPiece value) {
        return new JAXBElement<InsertPiece>(_InsertPiece_QNAME, InsertPiece.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPieceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "insertPieceResponse")
    public JAXBElement<InsertPieceResponse> createInsertPieceResponse(InsertPieceResponse value) {
        return new JAXBElement<InsertPieceResponse>(_InsertPieceResponse_QNAME, InsertPieceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePlayerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "createPlayerResponse")
    public JAXBElement<CreatePlayerResponse> createCreatePlayerResponse(CreatePlayerResponse value) {
        return new JAXBElement<CreatePlayerResponse>(_CreatePlayerResponse_QNAME, CreatePlayerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsMyTurnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "isMyTurnResponse")
    public JAXBElement<IsMyTurnResponse> createIsMyTurnResponse(IsMyTurnResponse value) {
        return new JAXBElement<IsMyTurnResponse>(_IsMyTurnResponse_QNAME, IsMyTurnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PreRegister }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "preRegister")
    public JAXBElement<PreRegister> createPreRegister(PreRegister value) {
        return new JAXBElement<PreRegister>(_PreRegister_QNAME, PreRegister.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOppositePlayerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "getOppositePlayerResponse")
    public JAXBElement<GetOppositePlayerResponse> createGetOppositePlayerResponse(GetOppositePlayerResponse value) {
        return new JAXBElement<GetOppositePlayerResponse>(_GetOppositePlayerResponse_QNAME, GetOppositePlayerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PreRegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "preRegisterResponse")
    public JAXBElement<PreRegisterResponse> createPreRegisterResponse(PreRegisterResponse value) {
        return new JAXBElement<PreRegisterResponse>(_PreRegisterResponse_QNAME, PreRegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "getGameStatus")
    public JAXBElement<GetGameStatus> createGetGameStatus(GetGameStatus value) {
        return new JAXBElement<GetGameStatus>(_GetGameStatus_QNAME, GetGameStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOppositePlayer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.chungtoi/", name = "getOppositePlayer")
    public JAXBElement<GetOppositePlayer> createGetOppositePlayer(GetOppositePlayer value) {
        return new JAXBElement<GetOppositePlayer>(_GetOppositePlayer_QNAME, GetOppositePlayer.class, null, value);
    }

}
