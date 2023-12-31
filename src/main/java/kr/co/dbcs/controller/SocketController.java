package kr.co.dbcs.controller;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@ServerEndpoint(value = "/chat/{roomId}/{nickname}")
public class SocketController {
    private static final Map<Integer, Map<Session, String>> sessionMapping = new HashMap<>();

    public SocketController() {
        log.info("create socket");
    }

    @OnOpen
    public void onOpen(@NonNull Session session) {
        log.info("open session: {}", session.getId());
        int roomId = Integer.parseInt(session.getPathParameters().get("roomId"));
        log.info("room ID: {}", roomId);
        try {
            final Basic basic = session.getBasicRemote();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (!sessionMapping.containsKey(roomId)) sessionMapping.put(roomId, new HashMap<>());

        String nickName = session.getPathParameters().get("nickname");
        sessionMapping.get(roomId).put(session, nickName);
        JSONObject obj = new JSONObject();
        obj.put("type", "0");
        obj.put("nickname", nickName);
        obj.put("people", sessionMapping.get(roomId).size());
        sendAllSessionToMessage(session, obj);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws ParseException {
        try {
            final Basic basic = session.getBasicRemote();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info(message);
        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(message);
        sendAllSessionToMessage(session, obj);
    }

    @OnError
    public void onError(@NonNull Throwable e, @NonNull Session session) {
        log.error(e.getMessage() + "by session : " + session.getId());
    }

    @OnClose
    public void onClose(@NonNull Session session) {

        log.info("Session: {} {} closed", session.getPathParameters(), session.getId());
        int roomId = Integer.parseInt(session.getPathParameters().get("roomId"));

        String nickName = sessionMapping.get(roomId).get(session);
        sessionMapping.get(roomId).remove(session);

        JSONObject obj = new JSONObject();
        obj.put("type", "2");
        obj.put("nickname", nickName);
        obj.put("people", sessionMapping.get(roomId).size());
        sendAllSessionToMessage(session, obj);

        if (sessionMapping.get(roomId).isEmpty()) sessionMapping.remove(roomId);
    }

    private void sendAllSessionToMessage(Session self, JSONObject obj) { // 연결된 모든 사용자에게 메세지 전달
        try {
            int roomId = Integer.parseInt(self.getPathParameters().get("roomId"));
            log.info(obj.toString());
            for (Session s : sessionMapping.get(roomId).keySet()) {
                s.getBasicRemote().sendText(obj.toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}