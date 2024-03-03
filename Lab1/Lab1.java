package lab1;

/**
 * @author MotelicaSandu
 */
public class Lab1 {
    public static int sumDigit(int a) {
        int sum = 0;
        while (a != 0) {
            sum = sum + a % 10;
            a = a / 10;
        }
        return sum;
    }

    void compulsory() {
        System.out.println("Hello World!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n = (n * 3 + Integer.parseInt("10101", 2) + Integer.parseInt("FF", 16)) * 6;
        int sum = sumDigit(n);
        while (sum >= 10) {
            sum = sumDigit((sum));
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);
    }

    void homework(int a, int b, int k) {
        long startTime = System.nanoTime();
        StringBuilder result = new StringBuilder();
        int copy;
        for (int i = a; i <= b; i++) {
            int sum = i;
            while (sum > 9) {
                copy = sum;
                sum = 0;
                while (copy > 0) {
                    sum = sum + (copy % 10) * (copy % 10);
                    copy = copy / 10;
                }
            }
            if (sum == k) {
                result.append(i).append(" ");
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Result: " + result);
        System.out.println("Execution time: " + duration + " milliseconds");
    }

    void bonus(int n){
        int[][] adjacencyMatrix = new int[n][n];

        for(int i = 0; i < n - 1; i++){
            adjacencyMatrix[i][i+1] = 1;
            adjacencyMatrix[i+1][i] = 1;
            adjacencyMatrix[i][n-1] = 1;
            adjacencyMatrix[n-1][i] = 1;
        }
        adjacencyMatrix[0][n-2] = 1;
        adjacencyMatrix[n-2][0] = 1;

        System.out.println("Adjacency Matrix:");
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // Cautare cicluri
        System.out.println("Cycles of the constructed graph:");
        int count = 0;
        for(int i = 0; i < n - 1; i++){
            int len = 1;
            while(len < n - 1){
                StringBuilder cycle = new StringBuilder();
                for(int j = i; j < i + len + 1; j++){
                    cycle.append(j % (n - 1)).append(" ");
                }
                cycle.append(n-1).append(" ").append(i);
                System.out.println(cycle);
                len++;
                count++;
            }
        }
        // Ultimul ciclu - Ciclu ce contine toate nodurile mai putin cel central
        for(int i = 0; i < n - 1; i++){
            System.out.print(i + " ");
        }
        System.out.println(0);
        count++;
        // Compararea rezultatelor
        System.out.println("Number of cycles resulted from the algorithm: " + count);
        System.out.println("Expected number of cycles: " + (n * n - 3 * n + 3));
    }


    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
        lab1.compulsory();
        if (args.length != 3) {
            System.out.println("Invalid input! Usage: <a> <b> <k>");
            System.exit(-1);
        }
        int a, b, k;
        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments! Please enter valid integers for a, b, and k.");
            return;
        }
        if (a >= b) {
            System.out.println("Invalid arguments! 'a' must be less than 'b'.");
            return;
        }
        lab1.homework(a, b, k);
        lab1.bonus(5);
    }
}
