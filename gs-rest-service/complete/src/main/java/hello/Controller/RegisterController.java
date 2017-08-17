package hello.Controller;

import hello.Class.Product;
import hello.Class.User;
import hello.DP.ReklamaDataDriver;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.swing.text.html.parser.Entity;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.SystemOut;


@RestController
public class RegisterController {
    @RequestMapping(value = "/register", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public ResponseEntity<?> adduser(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("telefon") String telefon,
            @RequestParam("login") String login,
            @RequestParam("parol") String parol) throws IOException, SQLException {
        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        System.out.println("Salom");
        int cnt = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM user WHERE login=?",  login);
        if(cnt > 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        jdbcTemplate.update("INSERT INTO user (first_name, last_name, telefon, login, parol) VALUES (?, ?, ?, ?, ?)",
                firstname,
                lastname,
                telefon,
                login,
                parol);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/updrek", method = RequestMethod.POST)
    public ResponseEntity<?> updrek(
            @RequestParam("id") String id,
            @RequestParam("des") String des,
            @RequestParam("score") String score) throws IOException, SQLException {
        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

        String sql = "UPDATE product SET comment=?, score=? WHERE id=?";
        jdbcTemplate.update(sql, des, score, strtoint(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }
    public int strtoint(String s) {
        int x = 0;
        for(int i = 0; i < s.length(); i ++) {
            int d = (s.charAt(i));
            x = (x * 10) + (d - 48);
        }
        return x;
    }

}
