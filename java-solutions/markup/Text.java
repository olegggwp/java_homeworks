package markup;

public class Text implements Markdown, InlineMarkdown {
    private final String text;

    public Text(String s) {
        text = s;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(text);
    }

    @Override
    public void toTex(StringBuilder str) {
        str.append(text);
    }
}
