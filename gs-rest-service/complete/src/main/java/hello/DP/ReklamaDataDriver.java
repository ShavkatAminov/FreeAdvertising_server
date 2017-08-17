package hello.DP;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLDataException;
import java.sql.SQLException;



public class ReklamaDataDriver  extends SimpleDriverDataSource {
    public ReklamaDataDriver() throws SQLException {
        this.setDriver(new com.mysql.jdbc.Driver());
        this.setUrl("jdbc:mysql://localhost:3306/reklama");
        this.setUsername("root");
        this.setPassword("");
    }
}
