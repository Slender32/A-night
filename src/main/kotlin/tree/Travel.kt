package tree

import helper.TreeNode

/**
 * 二叉树的层序遍历（广度优先搜索）
 *
 * 时间复杂度 `O(n)`
 * 空间复杂度 `O(width)` 最坏情况为完全二叉树,需要存储n/2个节点
 *
 * 原理: 队列（双端队列）实现 BFS
 *
 * while负责向下,for负责每一层遍历
 * 到达每一个节点时将左右节点入队,在下一层取出
 *
 * @param action 在每个节点上执行的操作，接收当前节点所在层级作为参数
 */
fun TreeNode.travelByBFS(action: TreeNode.(Int) -> Unit){
    val deque = ArrayDeque<TreeNode>()
    deque.addLast(this)
    var level = 0
    while (deque.isNotEmpty()) {
        repeat(deque.size){
            deque.removeFirst().apply {
                action(level)
                left?.let { deque.addLast(it) }
                right?.let { deque.addLast(it) }
            }
        }
        level++
    }
}