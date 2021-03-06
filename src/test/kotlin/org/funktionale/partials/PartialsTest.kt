/*
 * Copyright 2013 Mario Arias
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.funktionale.partials

import org.testng.annotations.Test
import org.testng.Assert.*

/**
 * Created by IntelliJ IDEA.
 * @author Mario Arias
 * Date: 29/03/13
 * Time: 21:35
 */
public class PartialsTest {
    [Test] fun partially() {
        val sum5ints = {(a: Int, b: Int, c: Int, d: Int, e: Int) -> a + b + c + d + e }

        val sum4intsTo10 = sum5ints.partially5(10)

        val sum3intsTo15 = sum4intsTo10.partially4(5)

        val sum2intsTo17 = sum3intsTo15.partially3(2)

        assertEquals(sum2intsTo17(1, 2), 20)

        val prefixAndPostfix = {(prefix: String, x: String, postfix: String) -> "${prefix}${x}${postfix}" }

        val helloX = prefixAndPostfix.partially1("Hello, ").partially2("!")

        assertEquals(helloX("funKTionale"), "Hello, funKTionale!")
    }

    [Test] fun partials() {
        val sum5ints = {(a: Int, b: Int, c: Int, d: Int, e: Int) -> a + b + c + d + e }

        val sum4intsTo10: (Int, Int, Int, Int) -> Int = sum5ints(p5 = 10)

        val sum3intsTo15: (Int, Int, Int) -> Int = sum4intsTo10(p4 = 5)

        val sum2intsTo17: (Int, Int) -> Int = sum3intsTo15(p3 = 2)

        assertEquals(sum2intsTo17(1, 2), 20)

        val prefixAndPostfix = {(prefix: String, x: String, postfix: String) -> "${prefix}${x}${postfix}" }

        val helloX: (String) -> String = prefixAndPostfix(p1 = "Hello, ")(p2 ="!")

        assertEquals(helloX("funKTionale"), "Hello, funKTionale!")
    }
}