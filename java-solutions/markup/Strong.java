package markup;

import java.util.List;

public class Strong extends ParagraphLike implements InlineMarkdown {

    public Strong(List<InlineMarkdown> objects) {
        super(objects);
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append("__");
        super.toMarkdown(s);
        s.append("__");
    }

    @Override
    public void toTex(StringBuilder s) {
        s.append("\\textbf{");
        super.toTex(s);
        s.append("}");
    }

}
