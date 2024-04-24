//Wang Yaxin A0258848H
import java.io.*;
class AVLNode {//create node for AVL tree
    String name;
    int height;
    int size;
    AVLNode left;
    AVLNode right;
    AVLNode parent;
    AVLNode (String s) {
        this.name = s;
        this.height = 0;
        this.size = 1;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
class AVLTree {//create class for AVL tree
    AVLNode root;
    int height (AVLNode node) {//get height for a certain node
        if(node == null)
            return -1;
        return node.height;
    }
    void updateHeight(AVLNode node) {//update the height for a certain node
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }
    int getSize(AVLNode node) {//get size for a certain node
        if (node == null)
            return 0;
        return node.size;
    }
    void updateSize(AVLNode node) {//update the size for a certain node
        node.size = getSize(node.left) + getSize(node.right) + 1;
    }
    int balanceFactor(AVLNode node) {//get balance factor
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }
    AVLNode rightRotate(AVLNode node) {//right rotation operation
        if (node.left != null) {
            AVLNode anotherNode = node.left;
            node.left = anotherNode.right;
            if (anotherNode.right != null) {
                anotherNode.right.parent = node;
            }
            anotherNode.parent = node.parent;
            if (node.parent == null) {
                this.root = anotherNode;
            } else if (node == node.parent.right) {
                node.parent.right = anotherNode;
            } else {
                node.parent.left = anotherNode;
            }
            anotherNode.right = node;
            node.parent = anotherNode;

            anotherNode.size = node.size;
            updateSize(node);
            updateHeight(node);
            updateHeight(anotherNode);
            return anotherNode;
        }
        return node;
    }
    AVLNode leftRotate(AVLNode node) {//left rotation operation
        if (node.right != null) {
            AVLNode anotherNode = node.right;
            node.right = anotherNode.left;
            if (anotherNode.left != null) {
                anotherNode.left.parent = node;
            }
            anotherNode.parent = node.parent;
            if (node.parent == null) {
                this.root = anotherNode;
            } else if (node == node.parent.left) {
                node.parent.left = anotherNode;
            } else {
                node.parent.right = anotherNode;
            }
            anotherNode.left = node;
            node.parent = anotherNode;

            anotherNode.size = node.size;
            updateSize(node);
            updateHeight(node);
            updateHeight(anotherNode);
            return anotherNode;
        }
        return node;
    }
    AVLNode checkIfNeedRotation(AVLNode node) {//check if need rotation
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            node = leftRotate(node);
        } else if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            node = rightRotate(node);
        }
        return node;
    }
    AVLNode insertion(AVLNode node, String name) {//insertion operation
        if (node == null) {
            return new AVLNode(name);
        }
        if (name.compareTo(node.name) < 0) {
            node.left = insertion(node.left, name);
            node.left.parent = node;
        } else {
            node.right = insertion(node.right, name);
            node.right.parent = node;
        }
        updateSize(node);
        updateHeight(node);
        return checkIfNeedRotation(node);
    }
    void inserting(String name) {
        this.root = insertion(this.root, name);
    }
    int countValidLeft(AVLNode node, String name) {//output the number of valid node on the left
        if (node == null) {
            return 0;
        }
        String current = node.name;
        if (current.indexOf(name) == 0) {
            return 1 + countValidLeft(node.left, name) + getSize(node.right);
        } else {
            return countValidLeft(node.right, name);
        }
    }
    int countValidRight(AVLNode node, String name) {//output the number of valid node on the right
        if (node == null) {
            return 0;
        }
        String current = node.name;
        if (current.indexOf(name) == 0) {
            return 1 + countValidRight(node.right, name) + getSize(node.left);
        } else {
            return countValidRight(node.left, name);
        }
    }
    AVLNode findHighest(AVLNode node, String name) {//output the highest valid node
        if (node == null) {
            return null;
        }
        String current = node.name;
        if (current.indexOf(name) == 0) {
            return node;
        }
        int compare = name.compareTo(current);
        if (compare < 0) {
            return findHighest(node.left, name);
        } else {
            return findHighest(node.right, name);
        }
    }
    int countValidMatches(String name) {//output the total number of valid nodes
        AVLNode highest = findHighest(this.root, name);
        if (highest == null) {
            return 0;
        }
        return 1 + countValidLeft(highest.left, name) + countValidRight(highest.right, name);
    }
}
public class nicknames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        AVLTree[] trees = new AVLTree[26]; //create an array to store AVL trees
        int numberOfNames = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfNames; i++) {
            String name = reader.readLine();
            char firstCharacter = name.charAt(0);
            int index = firstCharacter - 'a'; //get the index for the AVL tree array
            if (trees[index] == null) {
                trees[index] = new AVLTree();
            }
            trees[index].inserting(name);
        }
        int numberOfNicknames = Integer.parseInt(reader.readLine());
        for (int j = 0; j < numberOfNicknames; j++) {
            String nickname = reader.readLine();
            int matches = 0;
            char firstCharacter = nickname.charAt(0);
            int index = firstCharacter - 'a'; //get index for the AVL tree array
            if (trees[index] != null) {
                matches = trees[index].countValidMatches(nickname);
            }
            writer.write(matches + "\n");
        }
        writer.close();
    }
}