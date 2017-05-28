package lab8;

import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * KPI- FPM - PZKS Course: Algorithms and Data Structures (2) Laboratory work 5
 * 
 * @author Olena Khomenko <br>
 * 
 *         Binary search tree based implementation StudentDictionary Keeps
 *         specified information about students
 * 
 *         each node contains id (number of student's card) and information
 *         about student (name, surname etc.)
 * 
 *         all search, delete and get operation use unique id as a key
 * 
 * @param <E>
 */

public class BSTree<E extends Student> implements StudentDictionary<E> {

	/**
	 * root of a tree
	 */
	private TreeNode<E> root;
    int size;

	public BSTree() {

	}


	/**
	 * Returns true if this dictionary (binary search tree) contains a student
	 * for the specified cardNumber
	 * 
	 * @param cardNumber
	 *            cardNumber whose presence in this tree is to be tested
	 * @return true if this tree contains a student record for the specified
	 *         cardNumber
	 */
	@Override
	public boolean containsKey(int cardNumber) {
		// TODO
		// Error: if a cardNumber <= 0 return false

        return get(cardNumber) != null;
	}



	/**
	 * Returns the number of nodes in this tree.
	 * 
	 * @return he number of nodes in this tree
	 */
	@Override
	public int size() {
        return size;
	}

	/**
	 * Returns the student to which the specified cardNumber is associated, or
	 * null if this tree contains no student for the cardNumber.
     *
     * @param cardNumber
	 *            the cardNumber whose associated student is to be returned
	 * @return the student with the specified cardNumber, or null if this tree
	 *         contains no student for the cardNumber or cardNumber is invalid
	 *         (negative or 0)
	 */
	@Override
	public E get(int cardNumber)
    {
        TreeNode cur = root;

        while(cur != null)
        {
            if(cur.st.getkey() > cardNumber)
            {
                cur = cur.left;
            }
            else if(cur.st.getkey() < cardNumber)
            {
                cur = cur.right;
            }
            else
            {
                return (E)cur.st;
            }
        }
        return null;
    }

	/**
	 * Removes the student for this cardNumber from this tree if present.
	 *
	 * @return the previous student associated with cardNumber, or null if there
	 *         was no student for cardNumber.
	 */
	@Override
	public E remove(int num) {

        Stack<TreeNode> stack = new Stack<TreeNode>();

        // Check for empty tree

        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty())
        {
            TreeNode current = stack.peek();

            /* go down the tree in search of a leaf an if so process it
            and pop stack otherwise move down */
            if (prev == null || prev.left == current ||
                    prev.right == current)
            {
                if (current.left != null)
                    stack.push(current.left);
                else if (current.right != null)
                    stack.push(current.right);
                else
                {
                    stack.pop();
                    if(current.st.getkey() == num)
                    {
                        Student toDelete = current.st;
                        root = current.remove(root , num);
                        return (E)toDelete;
                    }
                }

                /* go up the tree from left node, if the child is right
                   push it onto stack otherwise process parent and pop
                   stack */
            }
            else if (current.left == prev)
            {
                if (current.right != null)
                    stack.push(current.right);
                else
                {
                    stack.pop();
                    if(current.st.getkey() == num)
                    {
                        Student toDelete = current.st;
                        root = current.remove(root , num);
                        return (E)toDelete;
                    }
                }

                /* go up the tree from right node and after coming back
                 from right node process parent and pop stack */
            }
            else if (current.right == prev)
            {
                stack.pop();
                if(current.st.getkey() == num)
                {
                    Student toDelete = current.st;
                    root = current.remove(root , num);
                    return (E)toDelete;
                }
            }
            prev = current;
        }



        return null;

	}

	// use this method when remove nodes
	private void replaceSubTree(TreeNode<E> u, TreeNode<E> v) {
		if (u == root) {
			root = v;
		} else {
			TreeNode<E> par = u.parent;
			if (par.isLeftChild(u)) {
				par.setLeft(v);
			} else {
				par.setRight(v);
			}
		}
	}


    /**
     *
     * @param genderCriterion
     * @param residenceCriterion
     * @return
     */
	public int remove(String genderCriterion , String residenceCriterion) {
		// TODO
		// 1) find all nodes which students satisfy specified removeCriteria
		// save in a list nodes to be removed
		// 2) call method remove (node) for each node in a list
		// 3) call size method to check successful removing
		// 4) return difference between old size and new size

        int initialSize = size;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<TreeNode>toDelete = new ArrayList<TreeNode>();

        // Check for empty tree
        if (root == null)
            return initialSize;
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty())
        {
            TreeNode current = stack.peek();

            /* go down the tree in search of a leaf an if so process it
            and pop stack otherwise move down */
            if (prev == null || prev.left == current ||
                    prev.right == current)
            {
                if (current.left != null)
                    stack.push(current.left);
                else if (current.right != null)
                    stack.push(current.right);
                else
                {
                    stack.pop();
                    if(current.st.gender.equals(genderCriterion) && current.st.residence.contains(residenceCriterion))
                    {
                        toDelete.add(current);
                    }
                }

                /* go up the tree from left node, if the child is right
                   push it onto stack otherwise process parent and pop
                   stack */
            }
            else if (current.left == prev)
            {
                if (current.right != null)
                    stack.push(current.right);
                else
                {
                    stack.pop();
                    if(current.st.gender.equals(genderCriterion) && current.st.residence.contains(residenceCriterion))
                    {
                        toDelete.add(current);
                    }
                }

                /* go up the tree from right node and after coming back
                 from right node process parent and pop stack */
            }
            else if (current.right == prev)
            {
                stack.pop();
                if(current.st.gender.equals(genderCriterion) && current.st.residence.contains(residenceCriterion))
                {
                    toDelete.add(current);
                }
            }

            prev = current;
        }

        for(TreeNode iterator: toDelete)
        {
            root = iterator.remove(root , iterator.st.getkey());
            System.out.println("__________________");
            size--;
        }

        return initialSize - size;
	}

	/**
	 * Returns true if this dictionary contains no key-value mappings
	 * 
	 * @return true if this dictionary contains no key-value mappings
	 */
	@Override
	public boolean isEmpty() {
		// TODO

		return false;
	}

	/**
	 * Associates the specified student with the specified cardNumber in this
	 * dictionary. If the dictionary previously contained a mapping for the
	 * cardNumber, the old student is replaced by the specified student.
	 * 
	 * @param num
	 *            cardNumber with which the specified student is to be
	 *            associated
	 * @param s
	 *            student to be associated with the specified cardNumber
	 * @return the previous student associated with cardNumber, or null if there
	 *         was no mapping for key
	 */
	@Override
	public E put(int num, Student s) {
		// TODO

        if(s == null)
        {
            return null;
        }

        TreeNode temp = new TreeNode(s);

        if(root == null)
        {
            root = temp;
            size++;
            return null;
        }

        TreeNode par = null;
        TreeNode cur = root;

        while(cur != null)
        {
            par = cur;

            if(num < cur.st.getkey())
            {
                cur = cur.left;
            }
            else if(num > cur.st.getkey())
            {
                cur = cur.right;
            }
            else
            {
                if(cur.st.getkey() == s.getkey())
                {
                    Student toDelete = cur.st;
                    cur.st = s;
                    return (E)toDelete;
                }
            }
        }

        if(num < par.st.getkey())
            par.left = temp;

        else
            par.right = temp;
        size++;
        return null;
    }




	/**
	 * Outputs dictionary elements in table form
	 */

	@Override
	public void printDictionary() {
		// TODO

        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Student>toPrint = new ArrayList<Student>();

        // Check for empty tree
        if (root == null)
            return;
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty())
        {
            TreeNode current = stack.peek();

            /* go down the tree in search of a leaf an if so process it
            and pop stack otherwise move down */
            if (prev == null || prev.left == current ||
                    prev.right == current)
            {
                if (current.left != null)
                    stack.push(current.left);
                else if (current.right != null)
                    stack.push(current.right);
                else
                {
                    stack.pop();
                    toPrint.add(current.st);
                }

                /* go up the tree from left node, if the child is right
                   push it onto stack otherwise process parent and pop
                   stack */
            }
            else if (current.left == prev)
            {
                if (current.right != null)
                    stack.push(current.right);
                else
                {
                    stack.pop();
                    toPrint.add(current.st);
                }

                /* go up the tree from right node and after coming back
                 from right node process parent and pop stack */
            }
            else if (current.right == prev)
            {
                stack.pop();

                toPrint.add(current.st);
            }

            prev = current;
        }

        for(Student it : toPrint)
        {
            System.out.println(it.secondName + "|" + it.firstName + "|" + it.year + "|" + it.gender + "|" + it.residence + "|" + it.getkey() + "|");
        }
	}


    class TreeNode<E extends Student> {
		/**
		 * information about student. Instance of class Student
		 */
		private E st;

		/**
		 * reference to the right node
		 */
		private TreeNode<E> right;

		/**
		 * reference to the left node
		 */
		private TreeNode<E> left;
		/**
		 * reference to the parent node
		 */

		private TreeNode<E> parent;

		public TreeNode(E e) {
			st = e;
			// TODO: complete initialization
            this.right = null;
            this.left = null;
            this.parent = null;
		}




		public TreeNode(E s, TreeNode<E> parent) {
			this(s);
			// TODO: complete initialization
            this.right = null;
            this.left = null;
            this.parent = parent;
		}

        private TreeNode min(TreeNode node) {
            if (node.left == null) {
                return node;
            }
            return min(node.left);
        }

        public TreeNode remove(TreeNode node, int data) {
            if (null == node) {
                return null;
            }
            if (data < node.st.getkey()) {
                node.left = remove(node.left, data);
            } else if (data > node.st.getkey()) {
                node.right = remove(node.right, data);
            } else { // case for equality

                if (node.left != null && node.right != null) {
                    TreeNode minInRightSubTree = min(node.right);

                    copyData(node , minInRightSubTree);

                    node.right = remove(node.right, minInRightSubTree.st.getkey());
                } else {
                    if (node.left == null && node.right == null) {
                        node = null;
                    } else {// one child case
                        TreeNode deleteNode = node;
                        node = (node.left != null) ? (node.left) : (node.right);
                        deleteNode = null;
                    }
                }
            }
            return node;
        }



        public int minValue()
        {
            if(left == null)
                return st.getkey();
            else
                return left.minValue();
        }



		public int getKey() {
			return st.getkey();

		}

		public E getValue() {
			return st;

		}

		public TreeNode<E> addLeftChild(E s) {
            TreeNode leftNode = new TreeNode(s);
            this.left = leftNode;
			return null;

		}

		public TreeNode<E> addRightChild(E s) {
            TreeNode rightNode = new TreeNode(s);
            this.right = rightNode;
			return null;

		}

		public void copyData(TreeNode dest , TreeNode orig)
        {
            dest.st.setKey(orig.getKey());
            dest.st.firstName = orig.st.firstName;
            dest.st.secondName = orig.st.secondName;
            dest.st.gender = orig.st.gender;
            dest.st.residence = orig.st.residence;
            dest.st.year = orig.st.year;
        }



		public void setLeft(TreeNode<E> left) {
            this.left = left;
		}

		public void setRight(TreeNode<E> right) {
            this.right = right;
		}

		public boolean isLeftChild(TreeNode<E> node) {
            if(this.st.getkey() == node.st.getkey()
                    && this.left.st.residence.equals(node.st.residence)
                    && this.left.st.firstName.equals(node.st.firstName)
                    && this.left.st.secondName.equals(node.st.secondName)
                    && this.left.st.gender.equals(node.st.gender)
                    && this.left.st.year == node.st.year)
            {
                return true;
            }
			return false;
		}

		public boolean isRightChild(TreeNode<E> node) {
            if(this.st.getkey() == node.st.getkey()
                    && this.right.st.residence.equals(node.st.residence)
                    && this.right.st.firstName.equals(node.st.firstName)
                    && this.right.st.secondName.equals(node.st.secondName)
                    && this.right.st.gender.equals(node.st.gender)
                    && this.right.st.year == node.st.year)
            {
                return true;
            }
            return false;
		}

		public TreeNode<E> getMinimum() {
			return null;

		}

		@Override
		public String toString() {
			return st.toString();
		}

	}



}
