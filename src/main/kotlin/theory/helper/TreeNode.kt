package theory.helper

/**
 * 照搬自力扣的TreeNode
 *
 * 吐槽:为什么kotlin的版本也非得用`val`,
 * 作为一个kt的关键字,总是要加上反引号
 */
class TreeNode(
    var value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
){
    companion object {

        /**
         * 用于测试使用
         * 从已排序数组生成一颗二叉搜索树
         */
        fun createBST(values: IntArray, start: Int, end: Int): TreeNode? {
            if (start > end) return null
            if (start == end) return TreeNode(values[start])
            val mid = (start + end) / 2
            val root = TreeNode(values[mid])
            root.left = createBST(values, start, mid - 1)
            root.right = createBST(values, mid + 1, end)
            return root
        }
    }
}