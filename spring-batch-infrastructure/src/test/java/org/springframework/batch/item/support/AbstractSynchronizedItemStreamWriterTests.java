/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.item.support;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamWriter;

/**
 * Common parent class for {@link SynchronizedItemStreamWriterTests} and
 * {@link org.springframework.batch.item.support.builder.SynchronizedItemStreamWriterBuilderTests}
 *
 * @author Dimitrios Liapis
 *
 */
@ExtendWith(MockitoExtension.class)
public abstract class AbstractSynchronizedItemStreamWriterTests {

	@Mock
	protected ItemStreamWriter<Object> delegate;

	private SynchronizedItemStreamWriter<Object> synchronizedItemStreamWriter;

	private final List<Object> testList = Collections.unmodifiableList(new ArrayList<>());

	private final ExecutionContext testExecutionContext = new ExecutionContext();

	abstract protected SynchronizedItemStreamWriter<Object> createNewSynchronizedItemStreamWriter();

	@BeforeEach
	void init() {
		synchronizedItemStreamWriter = createNewSynchronizedItemStreamWriter();
	}

	@Test
	void testDelegateWriteIsCalled() throws Exception {
		synchronizedItemStreamWriter.write(testList);
		verify(delegate).write(testList);
	}

	@Test
	void testDelegateOpenIsCalled() {
		synchronizedItemStreamWriter.open(testExecutionContext);
		verify(delegate).open(testExecutionContext);
	}

	@Test
	void testDelegateUpdateIsCalled() {
		synchronizedItemStreamWriter.update(testExecutionContext);
		verify(delegate).update(testExecutionContext);
	}

	@Test
	void testDelegateCloseIsClosed() {
		synchronizedItemStreamWriter.close();
		verify(delegate).close();
	}

}
