/*
 * Copyright 2009-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.sample.common;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputFileListenerTests {

	private final OutputFileListener listener = new OutputFileListener();

	private final StepExecution stepExecution = new StepExecution("foo", new JobExecution(0L), 1L);

	@Test
	void testCreateOutputNameFromInput() {
		listener.createOutputNameFromInput(stepExecution);
		assertEquals("{outputFile=file:./target/output/foo.csv}", stepExecution.getExecutionContext().toString());
	}

	@Test
	void testSetPath() {
		listener.setPath("spam/");
		listener.createOutputNameFromInput(stepExecution);
		assertEquals("{outputFile=spam/foo.csv}", stepExecution.getExecutionContext().toString());
	}

	@Test
	void testSetOutputKeyName() {
		listener.setPath("");
		listener.setOutputKeyName("spam");
		listener.createOutputNameFromInput(stepExecution);
		assertEquals("{spam=foo.csv}", stepExecution.getExecutionContext().toString());
	}

	@Test
	void testSetInputKeyName() {
		listener.setPath("");
		listener.setInputKeyName("spam");
		stepExecution.getExecutionContext().putString("spam", "bar");
		listener.createOutputNameFromInput(stepExecution);
		assertEquals("bar.csv", stepExecution.getExecutionContext().getString("outputFile"));
	}

}
