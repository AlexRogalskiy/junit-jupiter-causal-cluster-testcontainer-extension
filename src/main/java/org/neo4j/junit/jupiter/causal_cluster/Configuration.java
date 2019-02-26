/*
 * Copyright (c) 2019 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.junit.jupiter.causal_cluster;

import java.io.Serializable;
import java.time.Duration;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The clusters configuration.
 *
 * @author Michael J. Simons
 */
class Configuration implements Serializable {
	private final String neo4jVersion;

	private final int numberOfCoreMembers;

	private final int numberOfReadReplicas;

	private final Duration startupTimeout;

	private final String password;

	public Configuration(String neo4jVersion, int numberOfCoreMembers, int numberOfReadReplicas,
		Duration startupTimeout, String password) {
		this.neo4jVersion = neo4jVersion;
		this.numberOfCoreMembers = numberOfCoreMembers;
		this.numberOfReadReplicas = numberOfReadReplicas;
		this.startupTimeout = startupTimeout;
		this.password = password;
	}

	public String getNeo4jVersion() {
		return neo4jVersion;
	}

	public int getNumberOfCoreMembers() {
		return numberOfCoreMembers;
	}

	public int getNumberOfReadReplicas() {
		return numberOfReadReplicas;
	}

	public Duration getStartupTimeout() {
		return startupTimeout;
	}

	public String getPassword() {
		return password;
	}

	Stream<String> iterateCoreMembers() {
		final IntFunction<String> generateInstanceName = i -> String.format("neo4j%d", i);

		return IntStream.rangeClosed(1, numberOfCoreMembers).mapToObj(generateInstanceName);
	}

	String getImageName() {
		return String.format("neo4j:%s-enterprise", neo4jVersion);
	}

	@Override
	public String toString() {
		return "Configuration{" +
			"neo4jVersion='" + neo4jVersion + '\'' +
			", numberOfCoreMembers=" + numberOfCoreMembers +
			", numberOfReadReplicas=" + numberOfReadReplicas +
			", startupTimeout=" + startupTimeout +
			", password='" + password + '\'' +
			'}';
	}
}
