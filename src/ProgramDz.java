import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramDz {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item(233, 199),
                new Item(356.4, 10),
                new Item(13.75, 40),
                new Item(136.63, 70),
                new Item(13, 300),
                new Item(130, 198)
        );
        double maxWeight = 200.0;
        List<Item> list1 = findBestSetOfItems(items,maxWeight);
        for (Item a : list1) {
            System.out.println(a);
        }
        System.out.println();

        List<List<Item>> list = getAllSubsets(items);
        int i = 0;
        for (List<Item> a : list) {
            System.out.println(++i + " . " + a);
        }
        System.out.println(list);
        System.out.println();





    }

    //...............................................................

    /**
     * ¬ам нужно будет написать два метода:
     * 1.	public static List<Item> findBestSetOfItems(List<Item> items,double maxWeight) Ц метод
     * должен возвращать список (используйте arrayList) объектов класса Item,
     * суммарна€ стоимость которых максимальна, при этом не превышает maxWeight.
     */
    public static List<Item> findBestSetOfItems(List<Item> items, double maxWeight) {

        double maxCost = 0.0;
        List<Item> totalItems = new ArrayList<>();

        for (int mask = 0; mask < (1 << items.size()); mask++) {

            double totalCost = 0;
            double totalWeight = 0;

            for (int index = 0; index < items.size(); ++index) {

                int value = (mask >> index) & 1;
                if (value == 1) {
                    totalCost += items.get(index).getCost();

                    totalWeight += items.get(index).getWeight();//
                }
            }
            if (totalWeight <= maxWeight) {
                maxCost = Math.max(totalCost, maxCost);
//                if(totalCost > maxCost) {
//                    maxCost = totalCost;
//                }
                if (maxCost <= totalCost) {//?
                    if (!totalItems.isEmpty()) {
                        totalItems.clear();
                    }
                    for (int index = 0; index < items.size(); index++) {
                        int value = (mask >> index) & 1;
                        if (value == 1) {
                            totalItems.add(items.get(index));
                        }
                    }
                }
            }
        }
        return totalItems;
    }

    //...............................................................

    /**
     * 2.	public static List<List<Item>> getAllSubsets(List<Item> items) Ц
     * * метод возвращает всевозможные комбинации наших вещей в виде списка списков.
     * * ќбратите внимание: List<List<Item> означает список, который хранит списки,
     * * которые хран€т Item. “.е мы имеем что-то такое:
     * * {{}, {item1}, {item1, item2}, {item2}, {item3}, {item1, item2, item3},
     * * {item1, item3}}.
     */
    public static List<List<Item>> getAllSubsets(List<Item> items) {
        List<List<Item>> allListItems = new ArrayList<>();
        for (int mask = 0; mask < (1 << items.size()); mask++) {
            List<Item> tempList = new ArrayList<>();
            for (int index = 0; index < items.size(); index++) {
                int value = (mask >> index) & 1;
                if (value == 1) {
                    tempList.add(items.get(index));
                }
            }
            allListItems.add(tempList);
        }
        return allListItems;
    }

    //...............................................................
}
