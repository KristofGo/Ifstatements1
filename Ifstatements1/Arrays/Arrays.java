// Author: Kristof Gomez

public class Arrays {
    public static void main(String[] args) {
        String[] chest = {"Alpha","Bravo","Charlie","Delta","Applesauce"};
        chest[4] = "Echo";
        String sentence = "";
        for (String chest_item : chest){
            System.out.println(chest_item);
            sentence += chest_item;
            if (chest_item != chest[chest.length-1]) {
                sentence += "-";
            }
        }
        System.out.println(sentence); 
    }
}
