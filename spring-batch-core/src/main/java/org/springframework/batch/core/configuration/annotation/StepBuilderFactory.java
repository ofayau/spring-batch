/*
 * Copyright 2012-2022 the original author or authors.
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
package org.springframework.batch.core.configuration.annotation;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;

/**
 * Convenient factory for a {@link StepBuilder} which sets the {@link JobRepository} and
 * {@link PlatformTransactionManager} automatically.
 *
 * @author Dave Syer
 * @author Mahmoud Ben Hassine
 *
 */
public class StepBuilderFactory {

	private JobRepository jobRepository;

	private PlatformTransactionManager transactionManager;

	/**
	 * Constructor for the {@link StepBuilderFactory}.
	 * @param jobRepository The {@link JobRepository} to be used by the builder factory.
	 * Must not be {@code null}.
	 * @param transactionManager The {@link PlatformTransactionManager} to be used by the
	 * builder factory. Must not be {@code null}.
	 */
	public StepBuilderFactory(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		Assert.notNull(jobRepository, "JobRepository must not be null");
		Assert.notNull(transactionManager, "transactionManager must not be null");
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	/**
	 * Creates a step builder and initializes its job repository and transaction manager.
	 * Note that, if the builder is used to create a &#64;Bean definition, the name of the
	 * step and the bean name might be different.
	 * @param name the name of the step
	 * @return a step builder
	 */
	public StepBuilder get(String name) {
		return new StepBuilder(name).repository(this.jobRepository).transactionManager(this.transactionManager);
	}

}
