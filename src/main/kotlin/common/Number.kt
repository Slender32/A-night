package common

import kotlin.math.abs
import kotlin.math.sqrt

/**
 * 初学编程/课堂作业时写的算法
 *
 * 为了保证连贯性,也一起放到这里
 *
 * 虽然现在看起来可能有些简陋
 */
private fun doc() = Unit

/**
 * 求一个数的阶乘
 * 需保证输入 > 0
 *
 * 时间复杂度 `O(n)`
 * 空间复杂度 `O(1)`
 */
fun Int.rank(): Int {
    var result = 1
    for (number in 2..this) {
        result *= number
    }
    return result
}

/**
 * 判断一个数是不是质数
 *
 * 时间复杂度 `O(√n)`
 * 空间复杂度 `O(1)`
 *
 * 原理：基于`6k±1`的质数筛，所有大于3的质数都可以表示为`6k±1`的形式
 */
fun Int.isPrime(): Boolean {
    if (this <= 3) return this > 1
    if (this % 6 != 1 && this % 6 != 5) return false

    val sqrt = sqrt(this.toDouble()).toInt()
    for (factor in 5..sqrt step 6) {
        if(this % factor == 0 || this % (factor + 2) == 0) return false
    }
    return true
}

/**
 * 返回一个数的各个位数的数字(从高到低)
 *
 * 时间复杂度`O(log n)`
 * 空间复杂度`O(log n)`
 */
fun Int.list(): List<Int> {
    var temp = this
    val result = ArrayDeque<Int>()
    while (temp != 0){
        result.addFirst(temp % 10)
        temp /= 10
    }
    return result
}

/**
 * 获取一个数的`n`次幂
 *
 * 时间复杂度`O(log n)`
 * 空间复杂度`O(1)`
 *
 * 原理: 倍增法（快速幂）
 *
 * 将指数 count 表示为二进制形式，例如 count = 13 = 1101₂
 *
 * 则 base^13 = base^(8+4+1) = base^8 * base^4 * base^1
 */
fun Int.pow(count: Int): Long {
    if(count < 0) return 0
    if(count == 0) return 1
    if(this == 2) return 1L shl count
    var (result,current,count) = Triple(1L,this.toLong(),count)
    while (count != 0){
        if(count % 2 == 1) result *= current
        current *= current
        count = count shr 1
    }
    return result
}

/**
 * 获取两个整数的最大公因数
 *
 * 时间复杂度 `O(log min(n1, n2))`
 * 空间复杂度 `O(1)`
 *
 * 原理: 辗转相除法
 *
 * 核心性质：gcd(a, b) = gcd(b, a mod b)
 */
fun maxCommonFactor(n1: Int, n2: Int): Int {
    var (number1, number2) = n1 to n2
    while (number2 != 0) {
        val temp = number2
        number2 = number1 % number2
        number1 = temp
    }
    return abs(number1)
}

/**
 * 获取两个整数的最小公倍数
 *
 * 时间复杂度 `O(log min(n1, n2))`
 * 空间复杂度 `O(1)`
 *
 * 原理: 利用最大公因数计算最小公倍数
 *
 * 核心性质：`lcm(a, b) = |a * b| / gcd(a, b)`
 */
fun minCommonMultiple(n1: Int, n2: Int) = n1 / maxCommonFactor(n1,n2) * n2