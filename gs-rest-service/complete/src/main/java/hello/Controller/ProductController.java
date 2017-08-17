package hello.Controller;

import hello.Class.JustForId;
import hello.Class.Product;
import hello.DP.ReklamaDataDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.RowSet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = "application/json")
    public List<Product> productList(@RequestHeader(value = "login") String login) throws SQLException {

        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        if(!login.equals("")) {
            String sqlSelect = "SELECT * FROM product WHERE user_login=?";
            List<Product> list = jdbcTemplate.query(sqlSelect, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                    Product product = new Product(
                            resultSet.getString("name"),
                            resultSet.getString("score"),
                            resultSet.getString("comment"),
                            resultSet.getString("imagepath"),
                            resultSet.getLong("type"),
                            resultSet.getString("user_login")
                    );
                    product.setId(resultSet.getInt("id"));
                    return product;
                }
            }, login);
            return list;
        }
        else {
            String sqlSelect = "SELECT * FROM product";
            List<Product> list = jdbcTemplate.query(sqlSelect, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                    Product product = new Product(
                            resultSet.getString("name"),
                            resultSet.getString("score"),
                            resultSet.getString("comment"),
                            resultSet.getString("imagepath"),
                            resultSet.getLong("type"),
                            resultSet.getString("user_login")
                            );
                    product.setId(resultSet.getInt("id"));
                    return product;
                }
            });
            return list;
        }
    }
    @RequestMapping(value = "/productwithid", method = RequestMethod.GET, produces = "application/json")
    public List<Product> product(@RequestHeader(value = "id") String id) throws SQLException {

        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

            String sqlSelect = "SELECT * FROM product WHERE id=?";
            List<Product> list = jdbcTemplate.query(sqlSelect, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                    Product product = new Product(
                            resultSet.getString("name"),
                            resultSet.getString("score"),
                            resultSet.getString("comment"),
                            resultSet.getString("imagepath"),
                            resultSet.getLong("type"),
                            resultSet.getString("user_login")
                    );
                    product.setId(resultSet.getInt("id"));
                    return product;
                }
            }, strtoint(id));
            return list;
    }
    @RequestMapping(value = "/productdelete")
    public List<Product> delet(@RequestHeader(value = "id") String id) throws SQLException {

        ReklamaDataDriver datasource = new ReklamaDataDriver();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        String imagepaths = "";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM product WHERE id=?", strtoint(id));
        while (rowSet.next()) {
            imagepaths = rowSet.getString("imagepath");
        }
        String sqlSelect = "DELETE FROM product WHERE id=?";
        jdbcTemplate.update(sqlSelect, strtoint(id));
        String help = "";
        for(int i = 0; i < imagepaths.length(); i ++) {
            if(imagepaths.charAt(i) == '|') {
                Path path = Paths.get("C:\\xampp\\htdocs\\", help);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                help = "";
            }
            else {
                help += imagepaths.charAt(i);
            }
        }
        return null;
    }
    @RequestMapping(value = "/addnewreklam", produces = "application/json")
    public List<JustForId> idList(
            @RequestParam("name") String name,
            @RequestParam("type") String typestr,
            @RequestParam("score") String score,
            @RequestParam("comment") String comment,
            @RequestParam("login") String login,
            @RequestParam("imagesize") String imagesize,
            @RequestParam(value = "file0", required = false) MultipartFile file0,
            @RequestParam(value = "file1", required = false) MultipartFile file1,
            @RequestParam(value = "file2", required = false) MultipartFile file2,
            @RequestParam(value = "file3", required = false) MultipartFile file3,
            @RequestParam(value = "file4", required = false) MultipartFile file4
            ) throws SQLException, IOException {
        ReklamaDataDriver datasource = new ReklamaDataDriver();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        jdbcTemplate.update("INSERT INTO product (name, type, score, comment, imagepath, user_login) VALUES (?, ?, ? ,? ,? , ?)",
                name, strtoint(typestr), score, comment, "", login);
        int product_id = 0;
        SqlRowSet rowSet =  jdbcTemplate.queryForRowSet("SELECT * FROM product WHERE name=? AND type=? AND score=? AND comment=? AND user_login=?",
                name, strtoint(typestr), score, comment, login);
        while (rowSet.next()) {
            product_id = rowSet.getInt("id");
        }
        String imagepath = "";
        int imagescnt = strtoint(imagesize);
        if(imagescnt >= 1) {
            byte[] bytes = file0.getBytes();
            Path path = Paths.get("C:\\xampp\\htdocs\\img", product_id + "0.jpg");
            Files.write(path, bytes);
            imagepath += "img/" + product_id + "0.jpg|";
        }
        if(imagescnt >= 2) {
            byte[] bytes = file1.getBytes();
            Path path = Paths.get("C:\\xampp\\htdocs\\img", product_id + "1.jpg");
            Files.write(path, bytes);
            imagepath += "img/" + product_id + "1.jpg|";
        }
        if(imagescnt >= 3) {
            byte[] bytes = file2.getBytes();
            Path path = Paths.get("C:\\xampp\\htdocs\\img", product_id + "2.jpg");
            Files.write(path, bytes);
            imagepath += "img/" + product_id + "2.jpg|";
        }
        if(imagescnt >= 4) {
            byte[] bytes = file3.getBytes();
            Path path = Paths.get("C:\\xampp\\htdocs\\img", product_id + "3.jpg");
            Files.write(path, bytes);
            imagepath += "img/" + product_id + "3.jpg|";
        }
        if(imagescnt >= 5) {
            byte[] bytes = file4.getBytes();
            Path path = Paths.get("C:\\xampp\\htdocs\\img", product_id + "4.jpg");
            Files.write(path, bytes);
            imagepath += "img/" + product_id + "4.jpg|";
        }
        jdbcTemplate.update("UPDATE product SET imagepath=? WHERE id=?", imagepath, product_id);
        return null;
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
