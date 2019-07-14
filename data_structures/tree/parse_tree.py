from lib import binary_tree
from lib import stack
import operator


def parse(exp):
    root = binary_tree.BinaryTree()
    current_node = root
    st = stack.Stack()
    st.push(current_node)
    lst = list(exp)
    op_lst = ['+', '-', '*', '/']

    for i in lst:
        if i == "(":
            root.insert_left('')
            current_node = root.get_left_child()
        elif i.isdigit():
            current_node.set_value(int(i))
            parent = st.pop()
            current_node = parent
        elif i in op_lst:
            parent.set_value(i)
            st.push(parent)
            parent.insert_right('')
            current_node = parent.get_right_child()
        elif i == ")":
            current_node = st.pop()
        else:
            raise ValueError

    return root


def evaluate(self, parse_tree):
    """Evaluates the parser tree.

       :returns The evaluated expression.
    """
    opers = {'+': operator.add, '-': operator.sub, '*': operator.mul,
             '/': operator.truediv}

    left_c = parse_tree.getLeftChild()
    right_c = parse_tree.getRightChild()

    if left_c and right_c:
        fn = opers[parse_tree.getRootVal()]
        return fn(self.evaluate(left_c), self.evaluate(right_c))
    else:
        return parse_tree.getRootVal()


if __name__ == "__main__":
    inp = "(3d+(4*5))"
    parse(inp)
