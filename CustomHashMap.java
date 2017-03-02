package hashmap;

import java.lang.*;

public class CustomHashMap {

    public Entry hmap[] = new Entry[10];

    static class Entry {
        private Entry next;
        private int key;
        private String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public int getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public Entry getNext() {
            return this.next;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public int hashFunction(int key) {
        return key % 10;
    }

    public void putElement(Entry newEntry) {
        int position = hashFunction(newEntry.getKey());
        if (hmap[position] == null) {
            hmap[position] = newEntry;
        } else {
            Entry root, current;
            root = current = hmap[position];
            while (current != null) {
                if (current.getKey() == newEntry.getKey()) {
                    current.setValue(newEntry.getValue());
                    break;
                } else {
                    root = current;
                    current = current.getNext();
                }
            }
            root.setNext(newEntry);
        }

    }

    public void findElementbyKey(int key) {
        int position = hashFunction(key);
        Entry current = hmap[position];
        int found = 0;
        while (current != null) {
            if (current.getKey() == key) {
                System.out.println("Key " + key + " Exists with value:" + current.getValue());
                found = 1;
                break;
            }
            current = current.getNext();
        }
        if (found == 0) {
            System.out.println("Key " + key + " does not exist");
        }
    }

    public void deleteElement(int key) {
        int position = hashFunction(key);
        Entry current, root;
        current = root = hmap[position];
        int deleted = 0;
        while (current != null) {
            if (current.getKey() == key) {
                if (root == current) {
                    hmap[position] = null;
                    deleted = 1;
                    break;
                } else {
                    root.setNext(current.getNext());
                    deleted = 1;
                    System.out.print("Record deleted");
                    break;
                }
            } else {
                root = current;
                current = current.getNext();
            }
        }
        if (deleted == 0) {
            System.out.println("No Record found with key " + key);
        }
    }

    public void printHashMap() {
        for (int i = 0; i < hmap.length; i++) {
            Entry current = hmap[i];
            System.out.println("Index: " + i);
            while (current != null) {
                System.out.println("Key: " + current.getKey() + " Value: " + current.getValue());
                current = current.getNext();
            }
        }
    }

    public static void main(String args[]) {
        CustomHashMap cMap = new CustomHashMap();
        cMap.putElement(new Entry(1, "Hello"));
        cMap.putElement(new Entry(11, "World"));
        cMap.putElement(new Entry(73, "Luffy"));
        cMap.putElement(new Entry(87, "Saitama"));

        cMap.printHashMap();

        cMap.deleteElement(87);
        cMap.printHashMap();

    }

}

