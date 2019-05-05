import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 模板测试.
 * @author Administrator
 *
 */
@Controller
publicclass TemplateController {
/**
 * 返回html模板.
 */
@RequestMapping("/test")
public String helloHtml(Map<String,Object> map){
        map.put("hello","from TemplateController.test");
        return"/test";
        }
}