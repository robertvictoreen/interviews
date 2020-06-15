/*
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc" 
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: "" 
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.
*/
class Solution {
    public String rearrangeString(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        //Reverse frequency
        PriorityQueue<Character> pq = new PriorityQueue<>(Comparator.comparing(c -> map.get(c)).reversed());
        
        for (Character c : map.keySet()) {
            pq.offer(c);
        }
        
        //Wait queue
        LinkedList<Character> waiting = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            char c = pq.poll();
            if (map.get(c) > 0) {
                sb.append(c);
            }
            
            map.put(c, map.get(c) - 1);
            //Insert even 0 frequency for buffer
            if (map.get(c) >= 0) {
                waiting.offer(c);
            }
            
            //k may be 0
            if (!waiting.isEmpty() && waiting.size() >= k) {
                pq.offer(waiting.poll());
            }
            
        }
        if (sb.length() < s.length()) {
            return "";
        }
        return sb.toString();
    }
}