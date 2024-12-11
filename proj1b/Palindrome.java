public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<>();
        int length = word.length();
        for (int index = 0; index < length; index++) {
            dq.addLast(word.charAt(index));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> dq = this.wordToDeque(word);
        return isPalindromeHelper(dq);
    }

    private static boolean isPalindromeHelper(Deque<Character> dq) {
        if (dq.isEmpty() || dq.size() == 1) {
            return true;
        } else {
            Character first = dq.removeFirst();
            Character last = dq.removeLast();
            if (first == last) {
                return isPalindromeHelper(dq);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dq = this.wordToDeque(word);
        return isPalindromeHelper(dq, cc);
    }

    private static boolean isPalindromeHelper(Deque<Character> dq, CharacterComparator cc) {
        if (dq.isEmpty() || dq.size() == 1) {
            return true;
        } else {
            Character first = dq.removeFirst();
            Character last = dq.removeLast();
            if (cc.equalChars(first, last)) {
                return isPalindromeHelper(dq, cc);
            } else {
                return false;
            }
        }
    }
}
