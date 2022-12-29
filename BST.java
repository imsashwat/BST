package BST;

import LinkedList.stacksll.stack;

public class BST {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void display(Node node) {
        if( node == null) {
            return;
        }

        String str = "";
        str += node.left  == null? "." : node.left.data + ""; //
        str += " <- " + node.data + "->";
        str += node.right == null? "." : node.right.data + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static Node construct(int[] arr, int lo, int hi) {
        if(lo>hi) {
            return null;
        }

        int mid = (lo+hi)/2;
        int data = arr[mid];

        Node lc = construct(arr, lo, mid-1);
        Node rc = construct(arr, mid+1, hi);

        Node node = new Node(data,lc,rc);
        return node;
    }

    public static int max(Node node) {
        if(node.right != null) {
            return max(node.right);
        } else {
            return node.data;
        }
    }

    public static Node remove(Node node, int data) {
        if(node == null) {
            return null;
        }

        if(data>node.data) { //data big than node then go right
            node.right = remove(node.right, data);

        } else if(data<node.data) { //data small than node then go left
            node.left = remove(node.left, data);
        } else {
            if(node.left != null && node.right != null) { //if not a leaf node
                int lmax = max(node.left); //to us node ki left ka max nikalo 
                node.data = lmax; //and paste that max into current node
                node.left = remove(node.left, lmax); 
                return node;
            }else if(node.left != null) { //right null left me h kuch
                return node.left;
            }else if(node.right != null) { //left null but right me h kuch
                return node.right;
            }else {  //if its a leaf node
                return null;
            }          
        }
        return node;
    }

    //replace with sum of larger
    public static int sum =0;
    public static void rwsol(Node node) {
        if(node == null) {
            return ;
        }

        //node ki right se inorder chlva diya
        rwsol(node.right);
        int od = node.data;
        node.data = sum;
        sum+=od;

        rwsol(node.left);
    }



    public static void main(String[] args) {
        int[] arr = {12, 25, 37, 50, 62, 75, 87};
        Node root = construct(arr, 0, arr.length-1); 
        display(root);
        //remove(root, 37);
        rwsol(root);
        display(root);
    }
}

