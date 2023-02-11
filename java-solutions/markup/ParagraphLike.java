package markup;

import java.util.List;

public abstract class ParagraphLike implements Markdown {
    private final List<InlineMarkdown> list;

    protected ParagraphLike(List<InlineMarkdown> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        for (InlineMarkdown item : list) {
            item.toMarkdown(s);
        }
    }

    @Override
    public void toTex(StringBuilder str) {
        for (InlineMarkdown item : list) {
            item.toTex(str);
        }
    }
}
