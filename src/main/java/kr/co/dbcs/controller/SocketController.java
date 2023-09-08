package kr.co.dbcs.controller;

import kr.co.dbcs.model.ChatMessageDTO;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;

@Controller
@ServerEndpoint(value = "/socket/{roomId}")
public class SocketController {
    private static final HashMap<Integer, HashSet<Session>> sessionMapping = new HashMap<>();

    public SocketController() {
        System.out.println("create socket");
    }

    @OnOpen  // socket 연결 시
    public void onOpen(Session session){
        System.out.println("open session : " + session.getId());
        int roomId = Integer.parseInt(session.getPathParameters().get("roomId"));
        System.out.println("room ID : " + roomId);
        try{
            final Basic basic = session.getBasicRemote();
            basic.sendText("연결 완료");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
         if (!sessionMapping.containsKey(roomId)) sessionMapping.put(roomId, new HashSet<>());
         sessionMapping.get(roomId).add(session);
    }

    @OnMessage
    public void onMessage (String message, Session session) throws ParseException {
        try {
            //메세지 보낸 사람에게 표시됨
            final Basic basic = session.getBasicRemote();
            basic.sendText("변경하였습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 다른 사람에게 메세지 보내기
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject)parser.parse(message);
        HashMap<String, String> map = new HashMap<>();
        map.put("nickname", obj.get("nickname").toString());
        map.put("message", obj.get("message").toString());
        sendAllSessionToMessage(session, map);
    }

    @OnError
    public void onError(Throwable e, Session session){
        System.out.println(e.getMessage() + "by session : " + session.getId());
    }
    @OnClose
    public void onClose(Session session){
        System.out.println("Session : "+ session.getPathParameters() + " " + session.getId() + " closed");
        int roomId = Integer.parseInt(session.getPathParameters().get("roomId"));
        sessionMapping.get(roomId).remove(session);
        if (sessionMapping.get(roomId).isEmpty()) sessionMapping.remove(roomId);
    }


    private void sendAllSessionToMessage(Session self,HashMap<String, String> map){ // 연결된 모든 사용자에게 메세지 전달
        try {
            int roomId = Integer.parseInt(self.getPathParameters().get("roomId"));
            for(Session s : sessionMapping.get(roomId)){
                if(!self.getId().equals(s.getId())){
                    s.getBasicRemote().sendText(map.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}