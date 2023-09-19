package kr.co.dbcs.provider;

import kr.co.dbcs.model.NoteVO;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

public class NoteSqlProvider {

    public String selectALL() {
        return new SQL() {{
            SELECT("*");
            FROM("NOTE");
            ORDER_BY("NoteNo");
        }}.toString();
    }

    public String insertNote(NoteVO noteVO) { // 메모 전송
        return new SQL() {{
            INSERT_INTO("NOTE");
            VALUES("noteNo, noteWriter, noteListener, content, noteDate",
                    "Note_Seq.NEXTVAL, #{noteWriter}, #{noteListener}, #{content}, sysdate");
        }}.toString();
    }

    public String selectNote(NoteVO noteVO) {
        return new SQL() {{
            SELECT(" * ");
            FROM("NOTE");
            WHERE("noteWriter = #{noteWriter} AND noteListener = #{noteListener}");
            ORDER_BY("noteNo");
        }}.toString();
    }

    public String selectDialog(HashMap<String, String> map) {
        return new SQL() {{
            SELECT("*");
            FROM("Note");
            WHERE("(NoteWriter = #{username} OR NoteWriter = #{id}) and (NoteListener = #{username} OR NoteListener = #{id})");
            ORDER_BY("NOTENO");
        }}.toString();
    }

    public String selectDialogParter(String username) {
        return new SQL() {{
            SELECT("notelistener AS person");
            FROM("note");
            WHERE("notewriter = #{username}");
        }}
                + " UNION "
                + new SQL() {{
            SELECT("notewriter AS person");
            FROM("note");
            WHERE("notelistener = #{username}");
        }};
    }
}
