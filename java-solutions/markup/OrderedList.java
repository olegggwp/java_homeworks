package markup;

import java.util.List;

public class OrderedList extends Texlist {
    public OrderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    public void toTex(StringBuilder s) {
        s.append("\\begin{enumerate}");
        super.toTex(s);
        s.append("\\end{enumerate}");
    }
}
