package markup;

import java.util.List;

public class Emphasis extends ParagraphLike implements InlineMarkdown {
    public Emphasis(List<InlineMarkdown> objects) {
        super(objects);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append('*');
        super.toMarkdown(s);
        s.append('*');
    }

    @Override
    public void toTex(StringBuilder s) {
        s.append("\\emph{");
        super.toTex(s);
        s.append("}");
    }
}
