package markup;

import java.util.List;

public class Strikeout extends ParagraphLike implements InlineMarkdown {
    public Strikeout(List<InlineMarkdown> objects) {
        super(objects);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append('~');
        super.toMarkdown(s);
        s.append('~');
    }

    @Override
    public void toTex(StringBuilder s) {
        s.append("\\textst{");
        super.toTex(s);
        s.append("}");
    }
}
