package hello.Controller;

import hello.Class.Typeproduct;
import hello.Class.User;
import hello.DP.ReklamaDataDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ReklamTypeController {
    @RequestMapping(value = "/type",   method = RequestMethod.GET, produces = "application/json")
    public List<Typeproduct> typeproductList(@RequestHeader(value = "id") String id) throws IOException, SQLException {

        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        int ind = 0;
        for(int i = 0; i < id.length(); i ++) {
            int d = (id.charAt(i));
            ind = (ind * 10) + (d - 48);
        }
        String sqlselect = "SELECT * FROM type_product WHERE parent_id=?";
        List<Typeproduct> list = jdbcTemplate.query(sqlselect, new RowMapper<Typeproduct>() {
            @Override
            public Typeproduct mapRow(ResultSet resultSet, int i) throws SQLException {
                Typeproduct typeproduct = new Typeproduct(
                        resultSet.getString("name"),
                        resultSet.getInt("id"),
                        resultSet.getInt("parent_id")
                );
                return typeproduct;
            }
        }, ind);
        return list;
    }
}
