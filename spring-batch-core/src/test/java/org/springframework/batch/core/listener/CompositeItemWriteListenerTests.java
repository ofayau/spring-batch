/*
 * Copyright 2006-2022 the original author or authors.
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
package org.springframework.batch.core.listener;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ItemWriteListener;

/**
 * @author Lucas Ward
 * @author Will Schipp
 *
 */
class CompositeItemWriteListenerTests {

	ItemWriteListener<Object> listener;

	CompositeItemWriteListener<Object> compositeListener;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() {
		listener = mock(ItemWriteListener.class);
		compositeListener = new CompositeItemWriteListener<>();
		compositeListener.register(listener);
	}

	@Test
	void testBeforeWrite() {
		List<Object> item = Collections.singletonList(new Object());
		listener.beforeWrite(item);
		compositeListener.beforeWrite(item);
	}

	@Test
	void testAfterWrite() {
		List<Object> item = Collections.singletonList(new Object());
		listener.afterWrite(item);
		compositeListener.afterWrite(item);
	}

	@Test
	void testOnWriteError() {
		List<Object> item = Collections.singletonList(new Object());
		Exception ex = new Exception();
		listener.onWriteError(ex, item);
		compositeListener.onWriteError(ex, item);
	}

	@Test
	void testSetListeners() {
		compositeListener.setListeners(new ArrayList<ItemWriteListener<? super Object>>() {
			{
				add(listener);
			}
		});
		List<Object> item = Collections.singletonList(new Object());
		listener.beforeWrite(item);
		compositeListener.beforeWrite(item);
	}

}
