//Wang Yaxin A0258848H
import java.util.*;
class Money {
    int type;
    int buy;
    int sell;
    public Money(int type, int buy, int sell) {
        this.type = type;
        this.buy = buy;
        this.sell = sell;
    }
}
public class CardTrading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//number of cards in Anthony's deck
        int t = sc.nextInt();//number of total types
        int k = sc.nextInt();//number of combos
        int[] cards = new int[t + 1];
        for (int i = 0; i < n; i++) {
            int cardType = sc.nextInt();
            cards[cardType]++;//store all the numbers of the cards in the deck into an array
        }
        ArrayList<Money> money = new ArrayList<>();
        for (int i = 1; i <= t; i++) {
            int typeOfCard = i;
            int buyPrice = sc.nextInt();//read in all the buying price
            int sellPrice = sc.nextInt();//read in all the selling price
            int buy = (2 - cards[typeOfCard]) * buyPrice;//calculate the money needed to buy up to form a combo
            int sell = cards[typeOfCard] * sellPrice;//calculate the money earned to sell this type of card
            money.add(new Money(typeOfCard, buy, sell));//store the money needed and money earned into the array list
        }
        Collections.sort(money, new moneyComparator());//sort the different type of cards based on their cost
        long profit = 0;
        for (int i = 0; i < k; i++) {
            profit -= money.get(i).buy;//we buy the cards we needed to form k combo from the first k type of cards
        }
        for (int j = k; j < t; j++) {
            profit += money.get(j).sell;//we sell the rest of type of cards to earn profit
        }
        System.out.println(profit);//print out the net profit made by buying and selling
        sc.close();
    }
}
class moneyComparator implements Comparator<Money> {
    public int compare(Money type1, Money type2) {
        int type1Cost = type1.buy + type1.sell;
        int type2Cost = type2.buy + type2.sell;
        return type1Cost < type2Cost ? -1 : type1Cost == type2Cost ? 0 : 1;//compare and sort with respect to cost of not sell and buy one type of card
    }
}