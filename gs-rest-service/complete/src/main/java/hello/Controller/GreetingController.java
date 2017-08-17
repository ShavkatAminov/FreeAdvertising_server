package hello.Controller;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MultipartDataSource;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting() throws IOException {
        return "";
    }
}
