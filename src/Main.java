import java.util.*;

public class Main {
    // player : queue ; score;
    public static void main(String[] args) {
        clear();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        // Players ARRAY
        ArrayList<String> players = getPlayers();
        // RANDOMIZING QUEUE
        HashMap<Integer, String> playersQueue = randomizeQueue(players.size(), players);
        // PICKING WORD
        String word = getRandomWord();
        // ArrayList<String> players = getPlayers();
        // for(int i = 0 ; i < word.length() - 1; i++)

        // GETTING DESCRIPTION FOR THAT WORD
        String description = getDescription(word);
        // SCORE SETTING
        HashMap<String, Integer> score = new HashMap<String, Integer>();
        for (String e : players) {
            score.put(e, 0);
        }
        // BUGS
        ArrayList<String> used = new ArrayList<>();
        String curWinner = "";
        //
        // GAME
        int maxScore = 1000;
        int curMaxScore = 0; // !
        String log = "";
        int elm = 0;
        boolean stop = false;
        while (curMaxScore < maxScore / 2) {
            if (playersQueue.size() == 1 && elm > 0) {
                break;
            }
            if (stop)
                break;
            System.out.println(playersQueue.size());
            for (int i = 1; i < playersQueue.size() + 1; i++) {

                if (!playersQueue.containsKey(i))
                    continue;
                // LOG
                // player;word;score;scforLetter
                //

                String curPlayer = playersQueue.get(i);
                //
                int scorePerLetter = rand.nextInt(1000 / counter(word) / 2, 1000 / counter(word) * 3 / 2);
                int curScore = score.get(curPlayer);
                menu(word, used, description, scorePerLetter, curPlayer, curScore, log);
                System.out.println("Enter your guess");
                String guess = sc.next();
                while (used.contains(guess.toUpperCase())) {
                    log = "This letter was already used, please enter another one...";
                    menu(word, used, description, scorePerLetter, curPlayer, curScore, log);
                    guess = sc.next();
                }
                guess = guess.toUpperCase();
                //
                if (guess.length() == 1 && word.contains(guess)) {
                    log = "CORRECT";
                    i--;
                    used.add(guess);
                    score.replace(curPlayer, score.get(curPlayer) + scorePerLetter);
                    if (score.get(curPlayer) > curMaxScore)
                        curMaxScore = score.get(curPlayer);
                    menu(word, used, description, scorePerLetter, curPlayer, curScore, log);
                    // EXIT VICTORY
                    if (score.get(curPlayer) >= maxScore) {
                        log = "YES SOLO";// CHANGE TO VICTORY LOG
                        menu(word, used, description, i, curPlayer, curScore, log);
                        System.exit(0);
                    }
                    // EXIT NEXT PART
                    if (curMaxScore > maxScore / 2 && players.size() >= 2) {
                        curWinner = curPlayer;
                        stop = true;
                        break;
                    }
                    //
                } else if (guess.length() == 1 && word.contains(guess) == false) {
                    used.add(guess);
                    log = "INCORRECT";
                    continue;
                } else if (guess.equals(word)) {
                    String[] guessLetters = guess.split("");
                    for (String e : guessLetters) {
                        used.add(e);
                    }
                    log = "VICTORY"; // CHANGE TO VICTORY LOG
                    menu(word, used, description, scorePerLetter, curPlayer, curScore, log);
                    System.exit(0);
                    // ELIMINATED
                } else {
                    if (players.size() == 1) {
                        log = "The word is not correct\nGAME OVER";
                        menu(word, used, description, scorePerLetter, curPlayer, curScore, log);
                        System.exit(0);
                    }
                    eliminationNotification(curPlayer, description);
                    log = "";
                    players.remove(curPlayer);
                    playersQueue.remove(i);
                    score.remove(curPlayer);
                    elm++;
                    System.out.println("Enter anything to continue");
                    String x = sc.next();
                    //
                    //
                }
            }
        }
        clear();
        if (players.size() == 1) {
            victoryLog(players.get(0), description);
            System.exit(0);
        }
        log = "One of players reached more than a half of maximum points.\n So here is the Final";
        int scorePerLetter = 0;
        String curPlayer = "";
        int curScore = 0;
        menu(word, used, description, scorePerLetter, curPlayer, curScore, log);
        for (int i = 1; i < playersQueue.size() + 1; i++) {
            if (playersQueue.containsKey(i)) {
                curPlayer = playersQueue.get(i);
                if (curPlayer.equals(curWinner))
                    continue;
                System.out.println(curPlayer);
                System.out.println("Enter word");
                String guess = sc.nextLine();
                if (guess.equals(word)) {
                    System.out.println(curPlayer + " won! Congrats!");
                    System.exit(0);
                } else {
                    System.out.println("Incorrect!!!");
                }
            } else {
                continue;
            }
        }
        System.out.println(curWinner + "won! Congrats!");
    }

    public static ArrayList<String> getPlayers() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            System.out.println("Enter username or empty line to start.");
            String pName = sc.nextLine();
            if (pName.equals(""))
                break;
            else {
                list.add(pName);
                clear();
            }
        }
        return (list);
    }

    public static HashMap<Integer, String> randomizeQueue(int pAmount, ArrayList<String> pList) {
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
        return (queueMap);
    }

    public static String getRandomWord() {
        Random rand = new Random();
        int wordNum = rand.nextInt(1, 11);
        HashMap<Integer, String> words = new HashMap<Integer, String>();
        words.put(1, "INTELLIGENCE");
        words.put(2, "WAREHOUSE");
        words.put(3, "ARCHITECTURE");
        words.put(4, "NUTRIENTS");
        words.put(5, "THEOCRACY");
        words.put(6, "TECHNOLOGY");
        words.put(7, "OPPORTUNITY");
        words.put(8, "DICTIONARY");
        words.put(9, "IMAGINATION");
        words.put(10, "ENTERTAINMENT");
        String randomWord = words.get(wordNum);
        return randomWord;
    }

    public static String getDescription(String curWord) {
        HashMap<String, String> descriptionsMap = new HashMap<String, String>();
        descriptionsMap.put("INTELLIGENCE", "The ability to acquire and apply knowledge and skills.");
        descriptionsMap.put("WAREHOUSE", "Large building used to store things prior to their distribution.");
        descriptionsMap.put("ARCHITECTURE", "The art or practice of designing and constructing buildings.");
        descriptionsMap.put("NUTRIENTS", "Substance that provides nourishment essential for life and growth.");
        descriptionsMap.put("THEOCRACY", "System of government in which priests rule in the name of God.");
        descriptionsMap.put("TECHNOLOGY", "Application of scientific knowledge for practical purposes.");
        descriptionsMap.put("OPPORTUNITY", "Time or set of circumstances that makes it possible to do something.");
        descriptionsMap.put("DICTIONARY", "Book or electronic resource that lists the words of a language.");
        descriptionsMap.put("IMAGINATION", "Ability to think about something not present in our world.");
        descriptionsMap.put("ENTERTAINMENT", "Action of providing or being provided with amusement or enjoyment.");
        String description = descriptionsMap.get(curWord);
        return description;
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pause() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter anything to continue ");
        String x = sc.nextLine();
        sc.close();
    }

    public static int counter(String word) {
        HashSet<Character> s = new HashSet<Character>();
        for (int i = 0; i < word.length(); i++) {
            s.add(word.charAt(i));
        }
        return s.size();
    }

    
}
