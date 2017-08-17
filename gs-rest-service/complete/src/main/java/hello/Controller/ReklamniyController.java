package hello.Controller;

import hello.DP.ReklamaDataDriver;
import hello.Reklamniy;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ReklamniyController {
    @RequestMapping(value = "/reklama", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Reklamniy> courselist() throws SQLException {
        ReklamaDataDriver dataSource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sqlSelect = "SELECT * FROM type_product";
        List <Reklamniy> list = jdbcTemplate.query(sqlSelect, new RowMapper<Reklamniy>() {
            @Override
            public Reklamniy mapRow(ResultSet resultSet, int i) throws SQLException {
                Reklamniy reklamniy = new Reklamniy();
                reklamniy.setId(resultSet.getInt("id"));
                reklamniy.setName(resultSet.getString("name"));
                reklamniy.setParentid(resultSet.getInt("parent_id"));
                return reklamniy;
            }
        });
        return list;
    }
}
