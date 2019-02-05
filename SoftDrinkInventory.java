import java.util.Arrays;
import java.util.Scanner;

public class SoftDrinkInventory {

   private final int MAX_SOFT_DRINK_TYPES = 100;

   private String[] names;
   private String[] iDs;
   private int[] startingInventory;
   private int[] finalInventory;
   private int[] numberTransactions;

   // Initializes arrays holding soft drink name, ID, starting inventory, 
   // final inventory, transaction count
   public SoftDrinkInventory() {
      names = initializeString(new String[MAX_SOFT_DRINK_TYPES]);
      iDs = initializeString(new String[MAX_SOFT_DRINK_TYPES]);
      
      startingInventory = initializeInt(new int[MAX_SOFT_DRINK_TYPES]);
      finalInventory = initializeInt(new int[MAX_SOFT_DRINK_TYPES]);
      numberTransactions = initializeInt(new int[MAX_SOFT_DRINK_TYPES]);
   }
   
   // builds inventory arrays based on information in the data file
   public void buildInventory(Scanner inventoryFileScanner) {
      int i = 0;
      while (inventoryFileScanner.hasNextLine()) {
         String line = inventoryFileScanner.nextLine();
         Scanner tokens = new Scanner(line);
         names[i] = tokens.next();
         iDs[i] = tokens.next();
         startingInventory[i] = tokens.nextInt();
         finalInventory[i] = startingInventory[i];
         i++;
      }
   }

   // processes the transactions by adjusting the final inventory and 
   // transaction count arrays
   public void processTransactions(Scanner transactionFileScanner) {
      while (transactionFileScanner.hasNextLine()) {
         String line = transactionFileScanner.nextLine();
         Scanner tokens = new Scanner(line);
         String id = tokens.next();
         int transaction = tokens.nextInt();
         int index = findID(id);
         if (index != -1) {
            finalInventory[index] += transaction; 
            numberTransactions[index]++;
         }
      } 
   }
   
   // Displays a report including soft drink name, ID, starting inventory, final inventory, and
   // number of transactions processed.
   public void displayReport() { // better way to do this allignment??
      System.out.printf("%-15s%-7s%-23s%-20s%s\n", 
         "Soft drink", "ID", "Starting Inventory", "Final Inventory", "# transactions");
      int i = 0;
      while (names[i] != "") {
         System.out.printf("%-15s%-10s%6s%22s%21s\n",
            names[i], iDs[i], startingInventory[i], finalInventory[i], numberTransactions[i]);
         i++;
      }
   }

   // Determines array position based on ID parameter
   private int findID(String id) {
      for (int i = 0; i < MAX_SOFT_DRINK_TYPES; i++) { // should I use iDs.length instead?
         if (id.equals(iDs[i].trim())) return i;
      }
      return -1;
   }
   
   // Initializes int array values to 0
   private int[] initializeInt(int[] array) {
      Arrays.fill(array, 0);
      return array;
   }

   // Initializes String array values to ""
   private String[] initializeString(String[] array) {
      Arrays.fill(array, "");
      return array;
   }

}