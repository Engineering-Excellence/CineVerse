package kr.co.dbcs.controller;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.HashSet;

@Log4j2
@Controller
@ServerEndpoint(value = "/chat/{roomId}")
public class SocketController {
    private static final HashMap<Integer, HashSet<Session>> sessionMapping = new HashMap<>();

    public SocketController() {
        log.info("create socket");
    }

    @OnOpen  // socket 연결 시
    public void onOpen(Session session) {
        log.info("open session: {}", session.getId());
        int roomId = Integer.parseInt(session.getPathParameters().get("roomId"));
        log.info("room ID: {}", roomId);
        try {
            final Basic basic = session.getBasicRemote();
//            basic.sendText("연결 완료"); ~~님이 입장하였습니다 메세지로 보내기
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (!sessionMapping.containsKey(roomId)) sessionMapping.put(roomId, new HashSet<>());
        sessionMapping.get(roomId).add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws ParseException {
        try {
            //메세지 보낸 사람에게 표시됨
            final Basic basic = session.getBasicRemote();
//            basic.sendText("변경하였습니다."); // ~~님이 퇴장하였습니다
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info(message);
        // 다른 사람에게 메세지 보내기
        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(message);
        sendAllSessionToMessage(session, obj);
    }

    @OnError
    public void onError(Throwable e, Session session) {
        log.error(e.getMessage() + "by session : " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        log.info("Session: {} {} closed", session.getPathParameters(), session.getId());
        int roomId = Integer.parseInt(session.getPathParameters().get("roomId"));
        sessionMapping.get(roomId).remove(session);
        if (sessionMapping.get(roomId).isEmpty()) sessionMapping.remove(roomId);
    }


    private void sendAllSessionToMessage(Session self, JSONObject obj) { // 연결된 모든 사용자에게 메세지 전달
        try {
            int roomId = Integer.parseInt(self.getPathParameters().get("roomId"));
            log.info(obj.toString());
            for (Session s : sessionMapping.get(roomId)) {
                s.getBasicRemote().sendText(obj.toString());
//                if(!self.getId().equals(s.getId())){
//                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}