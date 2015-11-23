/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.test.tool.core.output.matcher;

import org.apache.commons.lang.ArrayUtils;
import org.apache.flink.streaming.test.tool.core.assertion.OutputMatcher;
import org.apache.flink.streaming.test.tool.matcher.partial.FromPartialMatcher;
import org.hamcrest.Description;

import java.util.Arrays;
import java.util.List;

public class OrderMatcher<T> extends OutputMatcher<T> {

	private org.apache.flink.streaming.test.tool.matcher.partial.OrderMatcher<T> matcher;

	public OrderMatcher(org.apache.flink.streaming.test.tool.matcher.ListMatcherBuilder<T> builder) {
		matcher = org.apache.flink.streaming.test.tool.matcher.partial.OrderMatcher.createFromBuilder(builder);
	}

	public FromPartialMatcher from(int n) {
		return matcher.from(n);
	}

	public void to(int n) {
		matcher.to(n);
	}

	public void all() {
		matcher.all();
	}

	public void indices(int first, int second, int... rest) {
		int[] front = new int[]{first,second};
		List<Integer> list = Arrays.asList(ArrayUtils.toObject(ArrayUtils.addAll(front, rest)));
		matcher.indices(list);
	}

	@Override
	protected boolean matchesSafely(Iterable<T> item) {
		return matcher.matches(item);
	}

	@Override
	public void describeTo(Description description) {
		matcher.describeTo(description);
	}
}
