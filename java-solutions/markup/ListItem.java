package markup;

import java.util.List;

public class ListItem implements TexItem {
    private final List<LIItem> list;

    public ListItem(List<LIItem> objects) {
        list = objects;
    }

    @Override
    public void toTex(StringBuilder s) {
        s.append("\\item ");
        for (LIItem item : list) {
            item.toTex(s);
        }
    }
}
