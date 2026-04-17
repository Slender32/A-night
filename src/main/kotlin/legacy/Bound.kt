package legacy

import java.util.ArrayList
import kotlin.math.max

/**
 * 计算多个双闭区间合并后的总长度
 *
 * 时间复杂度 `O(nlogn)`
 * 空间复杂度 `O(1)`
 *
 * 原理: 离线算法 + 贪心合并
 *
 * 先按区间起点升序排序(保证新区间头一定递增)
 *
 *
 * 使用 currentEnd 维护当前已合并区间的右边界（取开区间，即已覆盖到 currentEnd - 1）
 *
 * 对于新区间 [start, end]：
 * - 如果 end < currentEnd，说明完全被已有覆盖，跳过
 * - 新增区间头可能超过维护的区间尾,则使用start
 * - 没超过时使用维护的区间尾
 * - 双闭区间长度 = end - start + 1
 * - 更新区间尾
 *
 * @return 所有区间合并后的总覆盖长度
 */
fun List<IntArray>.union(): Int {
    val interval = sortedBy{ it.first() }
    var (currentEnd,result) = Pair(-1,0)
    for ((start, end) in interval) {
        if(end < currentEnd) continue
        result += end - max(start, currentEnd) + 1
        currentEnd = end + 1
    }
    return result
}

/**
 * 合并区间重叠部分
 *
 * 时间复杂度 `O(nlogn)`
 * 空间复杂度 `O(1)`. 使用了常数个变量,不计入结果占用
 *
 * 原理: 排序 + 贪心合并
 *
 * 先按区间起点升序排序，然后遍历每个区间。
 * 使用 startPoint 和 endPoint 维护当前合并区间的左右边界。
 * 对于新区间 [start, end]：
 * - 如果 start > endPoint，说明当前合并区间已完整，将其加入结果，并开始新的合并区间
 * - 否则，说明有重叠或相邻，更新 endPoint 为 max(endPoint, end)
 * 遍历结束后，将最后一个合并区间加入结果
 *
 * @return 合并后的区间列表
 */
fun MutableList<IntArray>.merge(): List<IntArray> {
    sortBy { it.first() }
    var (startPoint,endPoint) = first()
    val result = ArrayList<IntArray>()
    for ((start, end) in this) {
        if(start > endPoint){
            result.add(intArrayOf(startPoint,endPoint))
            startPoint = start
        }
        endPoint = max(endPoint, end)
    }
    result.add(intArrayOf(startPoint,endPoint))
    return result
}
