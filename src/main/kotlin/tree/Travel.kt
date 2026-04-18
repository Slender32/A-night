package tree

import helper.Data
import helper.TreeNode

/**
 * 二叉树的层序遍历（广度优先搜索）
 *
 * 时间复杂度 `O(n)`
 * 空间复杂度 `O(width)` 最坏情况为完全二叉树,需要存储`n/2`个节点
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

/**
 * 二叉树深度优先遍历
 *
 * 前序遍历/传递型 处理问题时只需要父节点信息
 *
 * 通常需要成员变量来记录结果,数据通过系统栈隐式保存并传递
 *
 * 需要指定`dummy`节点信息(`root`节点的父节点)作为初始数据
 *
 * 遍历顺序：根节点 -> 左子树 -> 右子树
 *
 * 时间复杂度：`O(n)`
 * 空间复杂度：`O(height)` 最坏情况为链表,方法递归`n`次
 *
 * @param data 父节点信息
 * @param action 接收父节点信息,处理每个节点
 */
fun TreeNode.travelByDFS(data: Data, action: TreeNode.(Any?) -> Unit){
    action(data)
    //更新数据并向下传递
    data.update(value)
    left?.travelByDFS(data,action)
    right?.travelByDFS(data,action)
}