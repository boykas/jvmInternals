public class TextService implements Service {
    public static String staticText() {
        return "Some";
    }

    @Override
    public String variable(String variable) {
        return variable;
    }

    public String exception(String text) throws RuntimeException {
        //TODO throw your custom exception
        return text;
    }

}
