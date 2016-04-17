public class ReverseWords{
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = array.length - 1; i >= 0; --i) {
            if (!array[i].equals("")) {
                sb.append(array[i]).append(" ");
            }
        }

        //remove the last " "
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] modString = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            modString[i] = s.charAt(s.length() - 1 - i);
        }

        s = s.copyValueOf(modString);
        String reverseWord = "";
        String eachWord;
        for (String part : s.split(" ")) {
            if (part.equals("")) {
                continue;
            }
            eachWord = new StringBuilder(part).reverse().toString();
            reverseWord = reverseWord + eachWord + " ";
        }

        //remove the last " "
        return reverseWord.length() == 0 ? "" : reverseWord.substring(0, reverseWord.length() - 1);
    }
}

