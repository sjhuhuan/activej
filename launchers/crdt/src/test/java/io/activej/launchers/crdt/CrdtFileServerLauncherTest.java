package io.activej.launchers.crdt;

import io.activej.crdt.util.CrdtDataSerializer;
import io.activej.crdt.util.TimestampContainer;
import io.activej.inject.annotation.Provides;
import io.activej.types.TypeT;
import org.junit.Test;

import static io.activej.serializer.BinarySerializers.INT_SERIALIZER;
import static io.activej.serializer.BinarySerializers.UTF8_SERIALIZER;

public class CrdtFileServerLauncherTest {
	@Test
	public void testInjector() {
		new CrdtFileServerLauncher<String, TimestampContainer<Integer>>() {
			@Override
			protected CrdtFileServerLogicModule<String, TimestampContainer<Integer>> getBusinessLogicModule() {
				return new CrdtFileServerLogicModule<String, TimestampContainer<Integer>>() {};
			}

			@Provides
			CrdtDescriptor<String, TimestampContainer<Integer>> descriptor() {
				return new CrdtDescriptor<>(
						TimestampContainer.createCrdtFunction(Integer::max),
						new CrdtDataSerializer<>(UTF8_SERIALIZER,
								TimestampContainer.createSerializer(INT_SERIALIZER)),
						String.class,
						new TypeT<TimestampContainer<Integer>>() {}.getType());
			}
		}.testInjector();
	}
}
