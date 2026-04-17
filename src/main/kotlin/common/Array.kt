package common

/**
 * 删除已排序数组中的重复元素
 *
 * 
 *
 * 时间复杂度 `O(n)`
 * 空间复杂度 `O(1)`
 *
 * 原理: 双指针法
 *
 * 由于数组已排序，重复元素必然相邻。
 * 慢指针 `current` 指向不重复区域的最后一个位置，
 * 快指针 `index` 遍历整个数组。
 * @return 新数组的长度
 */
fun IntArray.removeDuplicates(): Int {
    var current = 0
    for (index in 1 until size) {
        if(this[index] != this[current]){
            this[++current] = this[index]
        }
    }

    current++
    for (index in current until size) {
        this[index] = 0
    }

    return current
}

/**
 * 有序数组找到`>=target`的第一个索引
 *
 * 时间复杂度 `O(log n)`
 * 空间复杂度 `O(1)`
 *
 * 原理: 二分查找
 * 当满足条件`(>=)`时不断收缩右边界
 *
 * 最后 left左边(不包含left) 的数一定 <target
 * 而 right(不包含right) 右边的数一定 >=target
 *
 * 且有`left = right + 1`
 */
fun IntArray.binSearch(target: Int): Int {
    var (left, right) = Pair(0,lastIndex)
    while (left <= right){
        val mid = ((right - left) shr 1) + left
        if(this[mid] >= target)
            right = mid - 1
        else
            left = mid + 1
    }
    return left
}