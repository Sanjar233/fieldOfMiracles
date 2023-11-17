import java.util.*;
public class Main
{
    // player : queue ; score;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        //Players ARRAY
        ArrayList<String> players = getPlayers();
        //RANDOMIZING QUEUE
        HashMap<Integer, String> playersQueue = randomizeQueue(players.size(), players);
        //PICKING WORD
        String word = getRandomWord();
        //GETTING DESCRIPTION FOR THAT WORD
        String description = getDescription(word);
        //GAME
        int scorePerLetter = 1000 / word.length();
        int maxScore = 1000;
        int curMaxScore = 0; //!
        //SCORE SETTING
        HashMap<String, Integer> score = new HashMap<String, Integer>();
        // for (String e : players) {
        //     score.put(e, 0);
        // }
        // System.out.println(score);
        // while (curMaxScore < maxScore / 2) {
        //     // Guessing letter

        // }
        // for (String p : players) {
        //     // Guessing word
        // }
    }

    public static ArrayList<String> getPlayers() 
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Enter username...");
        while (true) {
            String pName = sc.nextLine();
            if (pName.equals(""))
                break;
            else {
                list.add(pName);
            }
        }
        System.out.println(list);
        return(list);
    }

    public static HashMap<Integer,String> randomizeQueue(int pAmount ,ArrayList <String> pList)
    {
        Random rand = new Random();
        HashMap<Integer, String> queueMap = new HashMap<Integer, String>();
        int cnt = 0;
        while (cnt < pAmount) {
            int queue = rand.nextInt(1, pAmount + 1);
            if (queueMap.containsKey(queue))
                continue;
            else {
                String name = pList.get(cnt);
                queueMap.put(queue, name);
                cnt++;
            }
        }
        System.out.println(queueMap);
        return (queueMap);
    }
    public static String getRandomWord()
    {
        Random rand = new Random();
        int wordNum = rand.nextInt(1, 11);
        HashMap<Integer, String> words = new HashMap<Integer, String>();
        words.put(1, "Intelligence");
        words.put(2, "Warehouse");
        words.put(3, "Architecture");
        words.put(4, "Nutrients");
        words.put(5, "Theocracy");
        words.put(6, "Technology");
        words.put(7, "Opportunity");
        words.put(8, "Dictionary");
        words.put(9, "Imagination");
        words.put(10, "Entertainment");
        String randomWord = words.get(wordNum);
        System.out.println(randomWord);
        return randomWord;
    }

    public static String getDescription(String curWord)
    {
        HashMap<String, String> descriptionsMap = new HashMap<String, String>();
        descriptionsMap.put("Intelligence", "The ability to acquire and apply knowledge and skills.");
        descriptionsMap.put("Warehouse", "Large building used to store things prior to their distribution.");
        descriptionsMap.put("Architecture", "The art or practice of designing and constructing buildings.");
        descriptionsMap.put("Nutrients", "Substance that provides nourishment essential for life and growth.");
        descriptionsMap.put("Theocracy","System of government in which priests rule in the name of God.");
        descriptionsMap.put("Opportunity", "Time or set of circumstances that makes it possible to do something.");
        descriptionsMap.put("Dictionary", "Book or electronic resource that lists the words of a language.");
        descriptionsMap.put("Imagination", "Ability to think about something not present in our world.");
        descriptionsMap.put("Entertainment", "Action of providing or being provided with amusement or enjoyment.");
        String description = descriptionsMap.get(curWord);
        System.out.println(description);
        return description;
    }
   
}
