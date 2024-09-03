package oy.tol.tra;


public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

    public int readFromFileToBST() {
        return count;
    }

    @Override
    public Type getType() {
        return Type.BST; 
    }

    @Override
    public int size() {
        return count; 
    }



    @Override
    public String getStatus() {
        String toReturn = "Tree has max depth of " + maxTreeDepth + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        toReturn += "Min path height to bottom: " + visitor.minHeight + "\n";
        toReturn += "Max path height to bottom: " + visitor.maxHeight + "\n";
        toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n";
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if (root == null) {
            root = new TreeNode<>(key, value);
            count++;
            return true;
        } else {




            return addNode(root, key, value);
        }
    }

    private boolean addNode(TreeNode<K, V> node, K key, V value) {
        int cmp = key.compareTo(node.getKey());
        if (cmp < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new TreeNode<>(key, value));
                count++;
                return true;
            } else {
                return addNode(node.getLeft(), key, value);
            }
        } else if (cmp > 0) {
            if (node.getRight() == null) {
                node.setRight(new TreeNode<>(key, value));
                count++;
                return true;
            } else {
                return addNode(node.getRight(), key, value);
            }
        } else {
            node.setValue(value);
            return false;
        }
    }

    private boolean addNode(Object left, K key, V value) {
        // TODO Auto-generated method stub
        if(find(key)!=null){
            return false;
        }
        throw new UnsupportedOperationException("Unimplemented method 'addNode'");
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        TreeNode<K, V> node = findNode(root, key);
        if (node != null) {
            return node.getValue();
        } else {
            return null;
        }
    }

    private TreeNode<K, V> findNode(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null; 
        }

        int cmp = key.compareTo(node.getKey());
        if (cmp < 0) {
            return findNode(node.getLeft(), key); 
        } else if (cmp > 0) {
            return findNode(node.getRight(), key); 
        } else {
            return node; // 找到匹配的节点
        }
    }


    private TreeNode<K, V> findNode(Object node, K key) {
        if (node instanceof TreeNode) {
            return findNode((TreeNode<K, V>) node, key);
        }
        throw new IllegalArgumentException("Invalid node type");
    }


    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        if (root == null) {
            root = buildBalancedTree(size);
            count = size;
        }
    }

    private TreeNode<K, V> buildBalancedTree(int size) {
        return buildBalancedTreeRecursive(1, size);
    }

    private TreeNode<K, V> buildBalancedTreeRecursive(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode<K, V> node = new TreeNode<>(null, null);
        node.setLeft(buildBalancedTreeRecursive(start, mid - 1));
        node.setRight(buildBalancedTreeRecursive(mid + 1, end));
        return node;
    }


    @Override
    public Pair<K, V>[] toSortedArray() {
        @SuppressWarnings("unchecked")
        Pair<K, V>[] sortedArray = new Pair[count];

        inOrderToArray(root, sortedArray, new int[]{0});
        return sortedArray;
    }

    private void inOrderToArray(TreeNode<K, V> node, Pair<K, V>[] array, int[] index) {
        if (node == null) {
            return;
        }
        inOrderToArray((TreeNode<K, V>) node.getLeft(), array, index);
        array[index[0]++] = new Pair<>(node.getKey(), node.getValue()); 
        inOrderToArray((TreeNode<K, V>) node.getRight(), array, index);
    }


    @Override
    public void compress() throws OutOfMemoryError {
        Pair<K, V>[] sortedArray = toSortedArray();
        root = buildBalancedTree(sortedArray, 0, sortedArray.length - 1);
    }

    private TreeNode<K, V> buildBalancedTree(Pair<K, V>[] array, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode<K, V> node = new TreeNode<>(array[mid].getKey(), array[mid].getValue());

        node.setLeft(buildBalancedTree(array, start, mid - 1));
        node.setRight(buildBalancedTree(array, mid + 1, end));

        return node;
    }


    // 其他方法保持不变
} 