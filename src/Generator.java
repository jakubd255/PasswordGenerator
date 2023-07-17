import java.util.Random;

public class Generator{
    private final String LOWERCASE = "qwertyuiopasdfghjklzxcvbnm";
    private final String UPPERCASE = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private final String NUMBERS = "0123456789";
    private final String SYMBOLS = "!@#$%&-=+:;*^?";

    private final Random random;

    public Generator()
    {
        random = new Random();
    }

    private String shuffleString(String string){
        char[] chars = string.toCharArray();

        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }

    public String generate(int length, boolean lowercase, boolean uppercase, boolean numbers, boolean symbols){
        String charList = "";
        if(lowercase) charList += LOWERCASE;
        if(numbers) charList += NUMBERS;
        if(uppercase) charList += UPPERCASE;
        if(numbers) charList += NUMBERS;
        if(symbols) charList += SYMBOLS;
        charList = shuffleString(charList);

        String password = "";
        for(int i=0; i<length; i++)
        {
            int randomIndex = random.nextInt(charList.length());
            char randomChar = charList.charAt(randomIndex);
            password += randomChar;
        }

        return password;
    }
}
