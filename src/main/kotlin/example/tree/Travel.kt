package example.tree

import theory.helper.TreeNode

/**
 * 传递型问题(只有递)
 *
 *
 * 力扣 ` https://leetcode.cn/problems/sum-of-nodes-with-even-valued-grandparent/ `
 *
 * 当前节点能不能满足完全看祖父节点是不是偶数
 * 只依赖父节点信息,典型传递型问题
 */
class Solution1315 {
    fun sumEvenGrandparent(root: TreeNode): Int {
        //dummy节点设为0,表示root的祖父和父节点不存在
        root.travel(0,0)
        return result
    }

    private var result = 0

    /**
     * 如果只传祖父节点,那子节点会不知道祖父节点是谁
     *
     * 偷瞄一眼题目值的范围,0可作为标志位表示不存在
     * @param grandParent 祖父节点值
     * @param parent 父节点值
     */
    //通常来讲,传递型是不太需要方法名和返回值的,只需确定参数即可
    private fun TreeNode.travel(grandParent: Int, parent: Int){
        if(grandParent != 0 && grandParent % 2 == 0){
            result += value
        }

        left?.travel(parent,value)
        right?.travel(parent,value)
    }
}


/**递归型问题(有递有归)
 *
 * 力扣` https://leetcode.cn/problems/convert-bst-to-greater-tree/ `
 *
 *
 * 分析题目可知,我们需要累加比自己大的节点的值
 *
 * 由于二叉搜索树特性,必然要累加右子树,再到自己,再到左子树
 *
 * 也就是中序遍历的过程,那问题就转为递归型问题
 */
class Solution538 {
    fun convertBST(root: TreeNode?): TreeNode? {
        //dummy节点不对下面节点产生影响,值为0
        root?.sumAndUpdate(0)
        return root
    }

    /**
     * 明确含义,该递归方法需要一边求和一边更新
     * @param sum 需要父节点的值+父节点的右子树的和来更新自己和子树
     * @return `sum` - 自己加子树的和
     */
    //递归型方法需要确定方法名,参数,返回值
    private fun TreeNode?.sumAndUpdate(sum: Int): Int {
        //如果不存在,不用更新子树,隐式更新自己,返回 自己的值(0 + sum) + 子树的和(0)
        if(this == null) return sum
        val rightSum = right.sumAndUpdate(sum)
        value += rightSum
        return left.sumAndUpdate(value)
    }
}