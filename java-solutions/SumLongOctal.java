public class SumLongOctal {
    public static void main(String[] args) {
        long result = 0;
        for (int i = 0; i < args.length; i++) {
            int numberStart = 0;
            for(int j = 0; j <= args[i].length(); j++) {
                if (j == args[i].length() || Character.isWhitespace(args[i].charAt(j)) || Character.toUpperCase(args[i].charAt(j)) == 'O' || args[i].charAt(j) == '+' || args[i].charAt(j) == '-' ) {
                    if ( numberStart < j ) {
                        result += Long.parseLong( args[i].substring(numberStart, j), (j == args[i].length() || Character.toUpperCase(args[i].charAt(j)) != 'O') ? 10 : 8 );
                    }
                    if (j == args[i].length() || args[i].charAt(j) == '+' || args[i].charAt(j) == '-' ) {
                        numberStart = j;
                    } else {
                        numberStart = j + 1;
                    }
                } 
            }
        }
        System.out.println(result);
    }
}