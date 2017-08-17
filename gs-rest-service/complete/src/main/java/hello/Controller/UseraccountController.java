package hello.Controller;

import hello.Class.User;
import hello.DP.ReklamaDataDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UseraccountController {
    @RequestMapping(value = "/useraccount",  produces = "application/json")
    public List<User> account(@RequestHeader(value = "login")String login) throws IOException, SQLException {
        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        User user = new User("", "", "", "", "");
        String sqlSelect = "SELECT * FROM user WHERE login=?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sqlSelect, login);
        if(row != null) {
            while (row.next()) {
                user.setFirstname(row.getString("first_name"));
                user.setLastname(row.getString("last_name"));
                user.setTelefon(row.getString("telefon"));
            }
        }
        List<User> luser = new ArrayList<>();
        luser.add(user);
        return luser;
    }
}
