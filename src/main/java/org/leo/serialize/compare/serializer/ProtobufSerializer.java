package org.leo.serialize.compare.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.leo.serialize.protobuf.domain.NewAddressBookProtos.AddressBook;

public class ProtobufSerializer extends AbstractSerializer<AddressBook> {
	
	@Override
	public String getName() {
		return "protobuf";
	}
	
	@Override
	public byte[] serialize(AddressBook addressBook) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		addressBook.writeTo(os);
		os.close();
		return os.toByteArray();
	}

	@Override
	public AddressBook deserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		AddressBook ab = AddressBook.parseFrom(is);
		return ab;
	}

}
