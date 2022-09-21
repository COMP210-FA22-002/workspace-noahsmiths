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

            int index;
            switch(category) {
                case "book":
                    index = 0;
                    break;
                case "jewelry":
                    index = 1;
                    break;
                case "phone":
                    index = 2;
                    break;
                default:
                    throw new Error("Invalid category");
            }

            categoryPrices[index] += price * quantity;
            categoryRatings[index] += rating;
            categoryDurations[index] += duration;
            categoryQuantities[index] += quantity;
            categoryAmounts[index] += 1;
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
