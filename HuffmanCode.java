import java.util.*;
public class HuffmanCode{
            
        static class Node {
        char ch;
        int freq;
        Node left, right;

        public Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
            left = right = null;
        }
    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2){
            return n1.freq - n2.freq;
        }
    }

    static Node buildHuffmanTree(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        } 

        while (pq.size() > 1){
            Node left = pq.poll();
            Node right = pq.poll();
            Node newNode = new Node('\0', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            pq.offer(newNode);
        }

        return pq.poll();
    }
    
    public static void generateHuffmanCodes(Node root, String code, Map<Character, String> huffmanCode){
            if (root == null) return;

           if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, code);
        }

        generateHuffmanCodes(root.left, code + '0', huffmanCode);
        generateHuffmanCodes(root.right, code + '1', huffmanCode);
        
    }

    public static void main(String[] args) {
        String s = "Welcome to the Galgotiya University";
        Node root = buildHuffmanTree(s);
        Map<Character, String> huffmanCode = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCode);

        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        StringBuilder encodedString = new StringBuilder();
        for (char ch : s.toCharArray()) {
            encodedString.append(huffmanCode.get(ch));
        }

        System.out.println("\nEncoded String:");
        System.out.println(encodedString);
    }
}