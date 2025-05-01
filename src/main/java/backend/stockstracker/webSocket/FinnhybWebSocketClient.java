//package webSocket;
//
//import javax.websocket.ClientEndpoint;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.ContainerProvider;
//import javax.websocket.WebSocketContainer;
//import java.net.URI;
//
//import org.glassfish.tyrus.client.ClientManager;
//
//@ClientEndpoint
//public class FinnhybWebSocketClient {
//
//    private Session userSession = null;
//
//    public void connect() {
//        try {
//            String apiKey = "d058lr1r01qoigrt2jugd058lr1r01qoigrt2jv0";
//            String endpoint = "wss://ws.finnhub.io?token=" + apiKey;
//
//            ClientManager client = ClientManager.createClient();
//            client.connectToServer(this, new URI(endpoint));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @OnOpen
//    public void onOpen(Session session) {
//        this.userSession = session;
//        System.out.println("✅ Connected to Finnhub WebSocket");
//
//        // Example: Subscribe to AAPL stock
//        session.getAsyncRemote().sendText("{\"type\":\"subscribe\",\"symbol\":\"AAPL\"}");
//    }
//
//    @OnMessage
//    public void onMessage(String message) {
//        System.out.println("📩 Received message: " + message);
//    }
//}
