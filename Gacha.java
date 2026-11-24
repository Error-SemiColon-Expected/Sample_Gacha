import java.util.Random;
import javax.swing.JOptionPane;

public class Gacha {

    static Random random = new Random();
    final static Object[] options_main = {"Start", "Top up", "Exit"};
    final static Object[] options_pull = {"1 pull", "10 pulls", "<"};
    final static Object[] options_shop = {"160 Credits", "800 Credits", "1600 Credits", "<"};

    static final int PULL_COST = 160;
    static final int HARD_PITY = 50;
    static final int SSR_RATE = 2;
    static final int SR_RATE = 15;

    static final int BUY_1_PULL = 160;
    static final int BUY_5_PULL = BUY_1_PULL * 5;
    static final int BUY_10_PULL = BUY_1_PULL * 10;

    static int credits = 1600;
    static int pity_count = 0;

    protected static final String[] R_ITEMS = {"R item #1", "R item #2", "R item #3", "R item #4", "R item #5"};
    protected static final String[] SR_CHARACTERS = {"SR character #1", "SR character #2", "SR character #3", "SR character #4"};
    protected static final String[] SSR_CHARACTERS = {"SSR character #1", "SSR character #2", "SSR character #3"};

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            int choice = JOptionPane.showOptionDialog(null, "Welcome to Sample Gacha!\nWould you like to make a wish?", "Sample Gacha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options_main, options_main[0]);
            switch (choice) {
                case 0 -> menu();
                case 1 -> shop();
                default -> running = false;
            }
        }
    }

    public static void menu() {
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "You currently have " + credits + " credits\nAnd " + pity_count + " Pity", "Sample Gacha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options_pull, options_pull[0]);
            int count;
            switch (choice) {
                case 0 -> count = 1;
                case 1 -> count = 10;
                default -> {return;}
            }

            int cost = count * PULL_COST;
            if (credits < cost) {
                JOptionPane.showMessageDialog(null, "You do not have enough credits for a " + count + "-pull.", "Insufficient credits", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            credits -= cost;
            pull(count);
        }
    }

    public static void pull(int count) {
        String results = "";
        for (int i = 0; i < count; i++) {
            pity_count++;
            int roll = random.nextInt(100) + 1;
            String result;

            if (pity_count >= HARD_PITY || roll <= SSR_RATE) {
                result = SSR_CHARACTERS[random.nextInt(SSR_CHARACTERS.length)] + " (SSR)";
                pity_count = 0;
            } else if (roll <= SSR_RATE + SR_RATE) {
                result = SR_CHARACTERS[random.nextInt(SR_CHARACTERS.length)] + " (SR)";
            } else {
                result = R_ITEMS[random.nextInt(R_ITEMS.length)] + " (R)";
            }
            results += (i + 1) + ". " + result + "\n";
        }
        JOptionPane.showMessageDialog(null, results, "Pull Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void shop() {
        while (true) {
            int choice_3 = JOptionPane.showOptionDialog(null, "You currently have " + credits + " credits.", "Sample Gacha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options_shop, options_shop[0]);
            switch (choice_3) {
                case 0 -> credits += BUY_1_PULL;
                case 1 -> credits += BUY_5_PULL;
                case 2 -> credits += BUY_10_PULL;
                default -> {return;}
            }
        }
    }
}
