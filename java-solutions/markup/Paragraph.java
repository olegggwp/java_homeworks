package markup;

import java.util.List;

public class Paragraph extends ParagraphLike implements LIItem {
    public Paragraph(List<InlineMarkdown> objects) {
        super(objects);
    }
}
