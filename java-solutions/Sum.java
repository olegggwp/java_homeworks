public class Sum {
    public static void main(String[] args){
        int result = 0;
        for (String arg : args) {
            int numberStart = 0;
            for (int j = 0; j < arg.length(); j = j + 1) {
                if (Character.isWhitespace(arg.charAt(j))) {
                    if (numberStart < j) {
                        result = result + Integer.parseInt(arg.substring(numberStart, j));
                    }
                    numberStart = j + 1;
                } else if (arg.charAt(j) == '+' || arg.charAt(j) == '-') {
                        if (numberStart < j) {
                        result = result + Integer.parseInt(arg.substring(numberStart, j));
                    }
                    numberStart = j;
                }
            }
            if (numberStart < arg.length()) {
                result = result + Integer.parseInt(arg.substring(numberStart));
            }
        }
        System.out.println(result);
    }
}