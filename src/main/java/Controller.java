public class Controller {
    @RequestMapping("/hello")
    public static String index() {
        return "Hello World!";
    }
}