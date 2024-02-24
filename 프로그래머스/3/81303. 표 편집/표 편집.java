import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Node[] nodeArr = new Node[1000001];
        nodeArr[0] = new Node();
        for(int i = 1; i < n; i++) {
            nodeArr[i] = new Node();
            nodeArr[i].prev = nodeArr[i - 1];
            nodeArr[i - 1].next = nodeArr[i];
        }
        Stack<Node> delete = new Stack<>();
        Node now = nodeArr[k];

        int cnt = 0;
        for(int i = 0; i < cmd.length; i++) {
            char command = cmd[i].charAt(0);
            switch(command) {
                case 'U':
                    cnt = Integer.parseInt(String.valueOf(cmd[i].charAt(2)));
                    for(int c = 0; c < cnt; c++) {
                        now = now.prev;
                    }
                    break;
                case 'D':
                    cnt = Integer.parseInt(String.valueOf(cmd[i].charAt(2)));
                    for(int c = 0; c < cnt; c++) {
                        now = now.next;
                    }
                    break;
                case 'C':
                    now.isDelete = true;
                    delete.push(now);
                    
                    Node prev = now.prev;
                    Node next = now.next;
                    
                    if(prev != null) {
                        prev.next = next;
                    } 
                    if(next != null) {
                        next.prev = prev;
                        now = next;
                    } else {
                        now = prev;
                    }
                    break;
                case 'Z':
                    Node node = delete.pop();
                    prev = node.prev;
                    next = node.next;
                    
                    node.isDelete = false;
                    if(prev != null) {
                        prev.next = node;
                    }
                    if(next != null) {
                        next.prev = node;
                    }
                    break;
            }
            
        }
        StringBuilder sb = new StringBuilder();
            for(int s = 0; s < n; s++) {
                if(nodeArr[s].isDelete) {
                    sb.append("X");
                } else {
                    sb.append("O");
                }
            }
        
        return sb.toString();
    }
    
    static class Node {
        Node prev = null;
        Node next = null;
        boolean isDelete;
    }
}

