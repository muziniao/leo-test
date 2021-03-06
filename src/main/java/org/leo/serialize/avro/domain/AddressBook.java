/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.leo.serialize.avro.domain;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class AddressBook extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AddressBook\",\"namespace\":\"org.leo.serialize.avro.domain\",\"fields\":[{\"name\":\"person\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Person\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"email\",\"type\":\"string\"},{\"name\":\"phone\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"PhoneNumber\",\"fields\":[{\"name\":\"number\",\"type\":\"string\"},{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"PhoneType\",\"symbols\":[\"MOBILE\",\"HOME\",\"WORK\"]}}]}}}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.util.List<org.leo.serialize.avro.domain.Person> person;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public AddressBook() {}

  /**
   * All-args constructor.
   */
  public AddressBook(java.util.List<org.leo.serialize.avro.domain.Person> person) {
    this.person = person;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return person;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: person = (java.util.List<org.leo.serialize.avro.domain.Person>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'person' field.
   */
  public java.util.List<org.leo.serialize.avro.domain.Person> getPerson() {
    return person;
  }

  /**
   * Sets the value of the 'person' field.
   * @param value the value to set.
   */
  public void setPerson(java.util.List<org.leo.serialize.avro.domain.Person> value) {
    this.person = value;
  }

  /** Creates a new AddressBook RecordBuilder */
  public static org.leo.serialize.avro.domain.AddressBook.Builder newBuilder() {
    return new org.leo.serialize.avro.domain.AddressBook.Builder();
  }
  
  /** Creates a new AddressBook RecordBuilder by copying an existing Builder */
  public static org.leo.serialize.avro.domain.AddressBook.Builder newBuilder(org.leo.serialize.avro.domain.AddressBook.Builder other) {
    return new org.leo.serialize.avro.domain.AddressBook.Builder(other);
  }
  
  /** Creates a new AddressBook RecordBuilder by copying an existing AddressBook instance */
  public static org.leo.serialize.avro.domain.AddressBook.Builder newBuilder(org.leo.serialize.avro.domain.AddressBook other) {
    return new org.leo.serialize.avro.domain.AddressBook.Builder(other);
  }
  
  /**
   * RecordBuilder for AddressBook instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AddressBook>
    implements org.apache.avro.data.RecordBuilder<AddressBook> {

    private java.util.List<org.leo.serialize.avro.domain.Person> person;

    /** Creates a new Builder */
    private Builder() {
      super(org.leo.serialize.avro.domain.AddressBook.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.leo.serialize.avro.domain.AddressBook.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.person)) {
        this.person = data().deepCopy(fields()[0].schema(), other.person);
        fieldSetFlags()[0] = true;
      }
    }
    
    /** Creates a Builder by copying an existing AddressBook instance */
    private Builder(org.leo.serialize.avro.domain.AddressBook other) {
            super(org.leo.serialize.avro.domain.AddressBook.SCHEMA$);
      if (isValidValue(fields()[0], other.person)) {
        this.person = data().deepCopy(fields()[0].schema(), other.person);
        fieldSetFlags()[0] = true;
      }
    }

    /** Gets the value of the 'person' field */
    public java.util.List<org.leo.serialize.avro.domain.Person> getPerson() {
      return person;
    }
    
    /** Sets the value of the 'person' field */
    public org.leo.serialize.avro.domain.AddressBook.Builder setPerson(java.util.List<org.leo.serialize.avro.domain.Person> value) {
      validate(fields()[0], value);
      this.person = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'person' field has been set */
    public boolean hasPerson() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'person' field */
    public org.leo.serialize.avro.domain.AddressBook.Builder clearPerson() {
      person = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public AddressBook build() {
      try {
        AddressBook record = new AddressBook();
        record.person = fieldSetFlags()[0] ? this.person : (java.util.List<org.leo.serialize.avro.domain.Person>) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
