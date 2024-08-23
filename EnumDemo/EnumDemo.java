package EnumDemo;

import java.util.Random;

public class EnumDemo {
    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            DayOfTheWeek day = getRandomDay();
            System.out.printf("It is %s, and the Ordinal is %d%n", day.name(), day.ordinal());
            if(day == DayOfTheWeek.SUN) {
                System.out.println("Yay!! Its Sunday!!");
            }
        }


    }

    public static DayOfTheWeek getRandomDay() {
        int dayIndexValue = new Random().nextInt(7);
        DayOfTheWeek[] days = DayOfTheWeek.values();
        return days[dayIndexValue];
    }
}
