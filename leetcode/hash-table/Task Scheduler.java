/*

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int count = 0;
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Character> pq = new PriorityQueue(Comparator.comparing(c -> map.get(c)).reversed());
        pq.addAll(map.keySet());
        
        LinkedList<Character> waiting;
        char c;
        while (!pq.isEmpty()) {
            
            //start new cycle
            waiting = new LinkedList<>();
            for (int i = 0; i <= n; i++) {
                
                if (!pq.isEmpty()) {
                    c = pq.poll();
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) > 0) {
                        waiting.offer(c);
                    }
                } else if (waiting.isEmpty()) {
                    return count;
                }
                
                count++;
            }
            
            pq.addAll(waiting);
        }
        return count;
        
    }
}