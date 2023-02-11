package markup;

import java.util.List;

public abstract class Texlist implements LIItem {
    private final List<ListItem> list;

    protected Texlist(List<ListItem> list) {
        this.list = list;
    }

    @Override
    public void toTex(StringBuilder s) {
        for (ListItem item : list) {
            item.toTex(s);
        }
    }
}
