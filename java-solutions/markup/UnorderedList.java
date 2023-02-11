package markup;

import java.util.List;

public class UnorderedList extends Texlist {
    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    public void toTex(StringBuilder s) {
        s.append("\\begin{itemize}");
        super.toTex(s);
        s.append("\\end{itemize}");
    }
}
