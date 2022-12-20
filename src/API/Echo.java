package API;

public class Echo implements Controller{

    @Override
    public void respond(Request req) {
        req.reply(req.getNBR());
    }
}
