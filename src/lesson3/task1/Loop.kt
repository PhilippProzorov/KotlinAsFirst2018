@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import kotlin.math.sqrt
import lesson1.task1.sqr
import kotlin.math.abs
import java.lang.Math.pow


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var counter = 1
    var number = n
    while (number != 0) {
        if ((number / 10) != 0) {
            counter++
        }
        number /= 10
    }
    return counter
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var number = 0
    var fibFirst = 1
    var fibSecond = 1
    if (n <= 2) return 1
    for (n in 3..n) {
        number = fibFirst + fibSecond
        fibFirst = fibSecond
        fibSecond = number
    }
    return number
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var firstNumber = m
    var secondNumber = n
    val initialProduct = (firstNumber * secondNumber)
    while ((firstNumber != 0) && (secondNumber != 0)) {
        if (firstNumber > secondNumber) firstNumber %= secondNumber
        else secondNumber %= firstNumber
    }
    val afterSum = firstNumber + secondNumber
    return (initialProduct / afterSum)
}
/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (minDiv in 2..n) {
        if (n % minDiv == 0) {
            return minDiv
        }
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int =
        n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var firstNumber = m
    var secondNumber = n
     while ((firstNumber != 0) && (secondNumber != 0)) {
         if (firstNumber > secondNumber) firstNumber %= secondNumber
         else secondNumber %= firstNumber
     }
    firstNumber += secondNumber
    return firstNumber == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt()) {
       if (sqr(i) in m..n) return true
    }
    return false
}
/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var counter = 0
    var collatzOverall = x
    while (collatzOverall > 1) {
        when {
            ((collatzOverall % 2) == 0) -> collatzOverall /= 2
            else -> collatzOverall = (3 * collatzOverall) + 1
        }
        counter++
    }
    return counter
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    val adjustedX = x % (Math.PI * 2)
    val initialSequence = -1
    var step = 3.0
    var result = adjustedX
    var previousCalculations = adjustedX
    while (abs(previousCalculations) >= eps) {
        previousCalculations *= initialSequence * (sqr(adjustedX) / ((step - 1) * step))
        result += previousCalculations
        step += 2
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val adjustedX = x % (Math.PI * 2)
    val initialSequence = -1
    var step = 2
    var previousCalculations = 1.0
    var result = previousCalculations
    while (abs(previousCalculations) >= eps) {
        previousCalculations *= initialSequence * (sqr(adjustedX) / ((step - 1) * step))
        result += previousCalculations
        step += 2
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var givenNumber = n
    var reversedNumber = 0
    do {
        reversedNumber = (reversedNumber * 10 + (givenNumber % 10))
        givenNumber /= 10
    } while (givenNumber > 0)
    return reversedNumber
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var palindromeCheck = n
    var finalResult = 0
    while (palindromeCheck > 0) {
        finalResult = (finalResult * 10) + (palindromeCheck % 10)
        palindromeCheck /= 10
    }
    return n == finalResult
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    var counter = 0
    if (number < 10) return false
    while (number > 10) {
        if ((number % 10) != ((number % 100) / 10)) {
            counter++
            break
        }
        number /= 10
    }
    return counter == 1
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = sequenceDigit(n) { it * it }

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = sequenceDigit(n, ::fib)




/**
 Сокращенная функция, содержащая лямбда-функцию для упрощения заданий squareSequenceDigit и fibSequenceDigit
 Добавлена функция, вычисляющая количество цифр в n (digitNumber)
 **/
fun sequenceDigit(n: Int, f: (Int) -> Int): Int {
    var sequenceFinal = 0
    var initialSequence = 0
    var counter = 0
    while (initialSequence < n) {
        counter++
        sequenceFinal += digitNumber(f(counter))
    }
    sequenceFinal = f(counter)
    while (initialSequence != n) {
        sequenceFinal /= 10
        initialSequence--
    }
    sequenceFinal %= 10
    return sequenceFinal
}
