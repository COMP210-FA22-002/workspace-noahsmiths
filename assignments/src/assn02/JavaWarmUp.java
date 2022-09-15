package assn02;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class JavaWarmUp {
    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        Scanner input;

        try {
            input = new Scanner(new File("./assignments/src/assn02/test.txt"));
        } catch (FileNotFoundException e) {
            input = new Scanner(System.in);
        }

        String[] categories = {"book", "jewelry", "phone"};
        Double[] categoryPrices = {0.0, 0.0, 0.0};
        Double[] categoryRatings = {0.0, 0.0, 0.0};
        int[] categoryDurations = {0, 0, 0};
        int[] categoryQuantities = {0, 0, 0};
        int[] categoryAmounts = {0, 0, 0};
        
        Sale lowestSale = null;
        Sale highestSale = null;

        int numOfSales = input.nextInt();
        for (int i = 0; i < numOfSales; i++) {
            String date = input.next();
            String time = input.next();
            String category = input.next();
            double price = input.nextDouble();
            int quantity = input.nextInt();
            double rating = input.nextDouble();
            int duration = input.nextInt();

            if (lowestSale == null || lowestSale._price >= price) {
                lowestSale = new Sale(date, time, category, price, quantity, rating, duration);
            }

            if (highestSale == null || highestSale._price <= price) {
                highestSale = new Sale(date, time, category, price, quantity, rating, duration);
            }

            switch(category) {
                case "book":
                    categoryPrices[0] += price * quantity;
                    categoryRatings[0] += rating;
                    categoryDurations[0] += duration;
                    categoryQuantities[0] += quantity;
                    categoryAmounts[0] += 1;
                    break;
                case "jewelry":
                    categoryPrices[1] += price * quantity;
                    categoryRatings[1] += rating;
                    categoryDurations[1] += duration;
                    categoryQuantities[1] += quantity;
                    categoryAmounts[1] += 1;
                    break;
                case "phone":
                    categoryPrices[2] += price * quantity;
                    categoryRatings[2] += rating;
                    categoryDurations[2] += duration;
                    categoryQuantities[2] += quantity;
                    categoryAmounts[2] += 1;
                    break;
            }
        }

        System.out.printf("Highest per unit sale:%n\tWhen: %s %s%n\tCategory: %s%n\tPrice: %.2f%n\tRating: %.1f%n", highestSale._date, highestSale._time, highestSale._category, highestSale._price, highestSale._rating);
        System.out.printf("Lowest per unit sale:%n\tWhen: %s %s%n\tCategory: %s%n\tPrice: %.2f%n\tRating: %.1f", lowestSale._date, lowestSale._time, lowestSale._category, lowestSale._price, lowestSale._rating);

        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%nAverages by %s%n\tQuantity: %d%n\tPrice: %.2f%n\tRating: %.2f%n\tDuration: %.2f", categories[i], categoryQuantities[i], categoryPrices[i] / categoryQuantities[i], categoryRatings[i] / categoryAmounts[i], (double)categoryDurations[i] / categoryAmounts[i]);
        }
    }
}

class Sale {
    String _date;
    String _time;
    String _category;
    double _price;
    int _quantity;
    double _rating;
    int _duration;

    Sale (String date, String time, String category, double price, int quantity, double rating, int duration) {
        this._date = date;
        this._time = time;
        this._category = category;
        this._price = price;
        this._quantity = quantity;
        this._rating = rating;
        this._duration = duration;
    }
}
