package hello.Controller;

import hello.Class.Product;
import hello.Class.User;
import hello.DP.ReklamaDataDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@RestController
public class AuthController {
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> adduser(
            @RequestParam("login") String login,
            @RequestParam("parol") String parol) throws IOException, SQLException {
        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        int cnt = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM user WHERE login=? AND parol=?",  login, parol);
        if(cnt == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}

