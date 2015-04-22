package com.catchingnow.evangelion.unit00.dummy;

import com.catchingnow.cbclient.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
//    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static ArrayList<Map<String, Object>> ITEMS = new ArrayList<>();


    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, DummyItem> ITEM_MAP = new HashMap<>();

    static {
        // Add 3 sample items.
//        addItem(new DummyItem("1", "Item 1"));
//        addItem(new DummyItem("2", "Item 2"));
//        addItem(new DummyItem("3", "Item 3"));
        for (int i = 0; i < 10; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", i);
            item.put("title", "Article "+(i+1));
            item.put("image", R.mipmap.ic_launcher);
            item.put("date", new Date());
            item.put("title", "This is content "+i);
            ITEMS.add(item);
        }
//        for (int i = 0; i < 10; i++){
//            addItem(new DummyItem(i, "Article "+i, R.mipmap.ic_launcher, new Date(), "This is just a Simple Test"));
//        }
    }

//    private static void addItem(DummyItem item) {
//        ITEMS.add(item);
//        ITEM_MAP.put(item.id, item);
//    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public int id;
        public String title;
        public int image;
        public Date date;
        public String content;

        public DummyItem(int id, String title, int image, Date date, String content) {
            this.id = id;
            this.title = title;
            this.image = image;
            this.date = date;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
