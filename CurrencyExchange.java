//Name: Amil Patel
//UFL ID 29332851
//Section: 5994
//Project Number: 3
//Brief description of file contents: Currency Exchange
import java.util.Scanner;


public class CurrencyExchange 
{
    private static double balance = 0;

    /**
     * Get the current balance of the account
     */
    public static double getBalance() 
    {
        return balance;
    }

     /**
     * Update the balance of current account, will do a roundup to 2 significant digits
     *
     * @return if update succeed, will return true. If failed, return false
     */
    private static boolean updateBalance(double newBalance) 
    {

        balance = Math.round(newBalance * 100) / 100.0;
        if (balance >= 0) {
            return true;
        } else
            return false;
    }

/****************************************************************
*        Do not modify anything above this line                 *
*****************************************************************/

    /**
     * main method, put your business logic and console input here
     */
    public static void main(String[] args) 
    {
        //Please DO NOT create any other Scanner objects
        Scanner input = new Scanner(System.in);
        
        int menuChoice;														//Integer used to take menu choice from method
        int currencyChoice;													//Integer used to take currency choice from method
        
        System.out.println("Welcome to Currency Exchange 2.0\n");
       
        printConversionTable();												//Conversion table method called
        
        do{																
        	menuChoice = mainMenuOptionSelector(input);						//Taking in menu choice from method
        
        	if (menuChoice == 1)													//Option 1 from menu choice
        		System.out.println("Your current balance is: " + balance + "\n");
        
        	if (menuChoice == 2)													//Option 2 from menu choice
        	{
        		currencyChoice = currencyMenuOptionSelector(input);				//Taking in currency choice for deposit
        	
        		System.out.println("Please enter the deposit amount:");			
        	
        		double deposit = input.nextDouble();							//Taking in deposit amount
        	
        		deposit(deposit,currencyChoice);								//Calling deposit method
        	}
        	if (menuChoice == 3)												//Option 3 from menu choice
        	{
        		currencyChoice = currencyMenuOptionSelector(input);				//Currency choice for withdrawal
        		
        		System.out.println("Please enter the withdrawal amount:");
        		
        		double withdraw = input.nextDouble();							//Withdraw amount
        		
        		withdraw(withdraw,currencyChoice);								//Calling withdraw method
        		
        	}
        	if (menuChoice == 4)												//Option 4 from menu choice
        	{
        		System.out.println(logTransaction(balance, 1, false));
        		System.out.println("Goodbye");
        		System.exit(0);
        	}
        }while(true);															//End of loop
        

        // this is where you will write the code that invokes (calls) 
        // the methods below to facilitate the successful running of 
        // your code

    }
    
    /**
     * Prints the conversion table at the start of the program (see the output examples).
     */
    private static void printConversionTable() 
    {
        System.out.println("Current rates are as follows:\n");						//Print statements for conversion table
        System.out.println("1 - U.S. Dollar - 1.00");
        System.out.println("2 - Euro - 0.89");
        System.out.println("3 - British Pound - 0.78");
        System.out.println("4 - Indian Rupee - 66.53");
        System.out.println("5 - Australian Dollar - 1.31");
        System.out.println("6 - Canadian Dollar - 1.31");
        System.out.println("7 - Singapore Dollar - 1.37");
        System.out.println("8 - Swiss Franc - 0.97");
        System.out.println("9 - Malaysian Ringgit - 4.12");
        System.out.println("10 - Japanese Yen - 101.64");
        System.out.println("11 - Chinese Yuan Renminbi - 6.67\n"); 
    }
    
    /**
     * Prints the main menu (see output examples), allows the user to make a selection from available operations
     *
     * @param input the Scanner object you created at the beginning of the main method. Any value other than the 4
     *              valid selections should generate an invalid value prompt. Print the list again and prompt user to
     *              select a valid value from the list.
     * @return an integer from 1-4 inclusive representing the user’s selection.
     */
    private static int mainMenuOptionSelector(Scanner input) 
    {
    	int menuChoice;															//Integer for menu choice within method
    	do																		//Loop until valid choice
    	{
        System.out.println("Please select an option from the list below:");
        System.out.println("1. Check the balance of your account");
        System.out.println("2. Make a deposit");
        System.out.println("3. Withdraw an amount in a specific currency");
        System.out.println("4. End your session (and withdraw all remaining currency in U.S. Dollars)");
        
        menuChoice = input.nextInt();											//Menu choice input
        
        if (menuChoice > 4 || menuChoice < 1)									//Validation for menu choice
        	System.out.println("Input failed validation. Please try again.\n");
    	}while (menuChoice > 4 || menuChoice < 1);								//End of loop 
    	
    	return menuChoice;														//Menu choice return
    }
    
    /**
     * Prints the currency menu (see output examples), allows the user to make a selection from available currencies
     *
     * @param input the Scanner object you created at the beginning of the main method. Any value other than the
     *              11 valid valid currency types should generate an invalid value prompt. Print the list again
     *              and prompt user to select a valid value from the list. the currency type will be the same as
     *              the type number used in {@link #convertCurrency(double, int, boolean)}
     * @return an integer from 1-11 inclusive representing the user’s selection.
     */
    private static int currencyMenuOptionSelector(Scanner input) 
    {
    	int currencyChoice;												//Integer for currency choice within method
        do																//Loop until valid choice
        {
        	System.out.println("Please select the currency type:");
        	System.out.println("1. U.S. Dollars");
        	System.out.println("2. Euros");
        	System.out.println("3. British Pounds");
        	System.out.println("4. Indian Rupees");
        	System.out.println("5. Australian Dollars");
        	System.out.println("6. Canadian Dollars");
        	System.out.println("7. Singapore Dollars");
        	System.out.println("8. Swiss Francs");
        	System.out.println("9. Malaysian Ringgits");
        	System.out.println("10. Japanese Yen");
        	System.out.println("11. Chinese Yuan Renminbi");
        	
        	currencyChoice = input.nextInt();							//Currency choice input
        	
        	if (currencyChoice > 11 || currencyChoice < 1 )
        		System.out.println("Input failed validation. Please try again.\n");				//Currency choice validation 
        	
        }while(currencyChoice > 11 || currencyChoice < 1 );				//End of loop
        
        return currencyChoice;											//Currency choice return
    }
    
    /**
     * Convert the value amount between U.S. dollars and a specific currency.
     *
     * @param amount         The amount of the currency to be converted.
     * @param currencyType   The integer currencyType works as follows:
     *                       1 for usd (U.S. Dollars)
     *                       2 for eur (Euros)
     *                       3 for bri (British Pounds)
     *                       4 for inr (Indian Rupees)
     *                       5 for aus (Australian Dollars)
     *                       6 for cnd (Canadian Dollars)
     *                       7 for sid (Singapore Dollars)
     *                       8 for swf (Swiss Francs)
     *                       9 for mar (Malaysian Ringgits)
     *                       10 for jpy (Japanese Yen)
     *                       11 for cyr (Chinese Yuan Renminbi)
     * @param isConvertToUSD Tells the direction of the conversion. If the value is true, then the conversion is from a
     *                       foreign currency to USD. If the value is false, then the conversion is from USD to the
     *                       foreign currency
     * @return the converted amount
     */
    public static double convertCurrency(double amount, int currencyType, boolean isConvertToUSD) 
    {
    	double convertedAmount = 0;									//Double for the currency converted within method
        if (isConvertToUSD == true)									//If statement for currency conversion that is from a foreign currency 
        {
        	if(currencyType == 2)
        		convertedAmount = amount / 0.89;
        	if(currencyType == 3)
        		convertedAmount = amount / 0.78;
        	if(currencyType == 4)
        		convertedAmount = amount / 66.53;
        	if(currencyType == 5)
        		convertedAmount = amount / 1.31;
        	if(currencyType == 6)
        		convertedAmount = amount / 1.31;
        	if(currencyType == 7)
        		convertedAmount = amount / 1.37;
        	if(currencyType == 8)
        		convertedAmount = amount / 0.97;
        	if(currencyType == 9)
        		convertedAmount = amount / 4.12;
        	if(currencyType == 10)
        		convertedAmount = amount / 101.64;
        	if(currencyType == 11)
        		convertedAmount = amount / 6.67;
        }
        
        if (isConvertToUSD == false)								//If statement for currency conversion that is from USD
        {
        	if(currencyType == 1)
        		convertedAmount = amount * 1.00;
        	if(currencyType == 2)
        		convertedAmount = amount * 0.89;
        	if(currencyType == 3)
        		convertedAmount = amount * 0.78;
        	if(currencyType == 4)
        		convertedAmount = amount * 66.53;
        	if(currencyType == 5)
        		convertedAmount = amount * 1.31;
        	if(currencyType == 6)
        		convertedAmount = amount * 1.31;
        	if(currencyType == 7)
        		convertedAmount = amount * 1.37;
        	if(currencyType == 8)
        		convertedAmount = amount * 0.97;
        	if(currencyType == 9)
        		convertedAmount = amount * 4.12;
        	if(currencyType == 10)
        		convertedAmount = amount * 101.64;
        	if(currencyType == 11)
        		convertedAmount = amount * 6.67;
        }
        
        return convertedAmount;									//Converted amount returned
    }
    
    /**
     * deposit the amount of a specific currency to the account
     *
     * @param amount       the amount to be deposited.
     * @param currencyType the currency type will be the same as the type number used
     *                     in the convertCurrency(double, int, boolean) method. An Invalid type will result in a
     *                     deposit failure.
     * @return if deposit succeed, will return true. If failed, return false
     */
    public static boolean deposit(double amount, int currencyType) 
    {
        boolean UsOrNot = true;				//Boolean to see if deposit is US or not
    	
    	if (currencyType > 1)				//Assignment of UsOrNot	
    		UsOrNot = true;
    	if (currencyType == 1)
    		UsOrNot = false;
    	if (amount > 0)						//If statement for valid deposit
    	{
    		System.out.println(logTransaction(amount, currencyType, true));
    		balance += convertCurrency(amount, currencyType, UsOrNot);
    		updateBalance(balance);
    		return true;					//Return boolean if deposit works
    	}
    	else								//Else clause for invalid deposit
    	{
    		System.out.println(logTransaction(amount, currencyType, true));
    		return false;					//Return variable if deposit fails
    	}
    }
    
    /**
     * withdraw the value amount with a specific currency from the account. The withdraw amount should never exceed the current account balance, or the withdrawal will fail. If the currency is other than USD, a 0.5% convenience fee will be charged
     *
     * @param amount       the amount to be withdrawn.
     * @param currencyType the currency type will be the same as the type number used
     *                     in the convertCurrency(double, int, boolean) method. An invalid
     * 		         type will result a withdraw failure.
     * @return if withdraw succeed, will return true. If failed, return false
     */
    public static boolean withdraw(double amount, int currencyType) 
    {
    	boolean UsOrNot = true;						//Boolean to see if deposit is US or not
        double withdraw;							//Double for amount to be withdrawn
        
        if (currencyType > 1)						//Assignment of UsOrNot
    		UsOrNot = true;
    	if (currencyType == 1)
    		UsOrNot = false;
    	
		withdraw = convertCurrency(amount, currencyType, UsOrNot);				//Converting the withdraw amount 
		
		if (amount > 0 && withdraw <= balance && UsOrNot == true)				//Valid amount from foreign currency
		{
			System.out.println(logTransaction(amount, currencyType, false));	//Transaction logged
			balance -= withdraw * 1.005;										//updating balance with fee
			updateBalance(balance);												//Rounding balance
			return true;														//Return for success
		}
		if (amount > 0 && withdraw <= balance && UsOrNot == false)				//Valid withdraw is US currency 
		{
			System.out.println(logTransaction(amount, currencyType, false));	//Transaction logged
			balance -= withdraw;												//Update balance
			updateBalance(balance);												//Round balance
			return true;														//Return for success 
		}
		else 																	//Else clause for invalid withdraw
		{
			System.out.println(logTransaction(amount, currencyType, false));	//Transaction logged
			return false;														//Return for failure		
		}
    }
    
    /**
     * Displays message at the end of the withdraw, deposit, and endTransaction stating
     * how much the user just withdrew/deposited and what type (this will be used in both features B, C and D of the
     * main menu).
     *
     * @param amount       the amount of currency withdrew/deposited
     * @param currencyType the currency type will be the same as the type number used
     *                     in {@link #convertCurrency(double, int, boolean)}
     * @param isDeposit    if true log the deposit transaction, false log the withdraw transaction
     * @return Return the formatted log message as following examples:
     * You successfully withdrew 10.0 U.S. Dollars
     * You successfully deposited 30.0 Japanese Yen
     * <p>
     * The invalid input like invalid currencyType or negative amount,
     * will return a “Logging Error”
     */
    private static String logTransaction(double amount, int currencyType, boolean isDeposit) 
    {
    	String type = "";										//String declaration for currency type
    	String output = "";										//String declaration for log transaction output
    	switch(currencyType)									//Switch for assigning currency type string
        {
        	case 1 : type = "U.S. Dollars";
        	break;
        	case 2 : type = "Euros";
        	break;
        	case 3 : type = "British Pounds";
        	break;
        	case 4 : type = "Indian Rupees";
        	break;
        	case 5 : type = "Australian Dollars";
        	break;
        	case 6 : type = "Canadian Dollars";
        	break;
        	case 7 : type = "Singapore Dollars";
        	break;
        	case 8 : type = "Swiss Francs";
        	break;
        	case 9 : type = "Malaysian Ringgits";
        	break;
        	case 10 : type = "Japanese Yen";
        	break;
        	case 11 : type = "Chinese Yuan Renminbi";
        	break;
        }

        if(amount > 0 && isDeposit == true)												//Log for successful deposit
        {
        	output = "You successfully deposited " + amount + " " + type + "\n";
        	return output;																//Return
        }
        if(amount > 0 && amount <= balance && isDeposit == false)						//log for successful withdraw
        {
        	output = "You successfully withdrew " + amount + " " + type + "\n";
        	return output;																//Return
        }
        if(amount == balance && isDeposit == false)										//Log for option 4 with 0.0 balance
        {
        	output = "Your remaining balance is 0.0 U.S. Dollars";
        	return output;																//Return
        }
        if(amount > balance && isDeposit == false )										//Log for invalid withdraw
        {
        	output = "Error: Insufficient funds.\nLogging Error\n";
        	return output;																//Return
        }	
        if(amount <= 0)																	//Log for 0 withdraw/deposit
        {
        	output = "Logging Error";
        	return output;																//Return
        }
        else return output;																//Else clause 
    }    
}
