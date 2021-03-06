/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.leo.serialize.avro.domain;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Man extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Man\",\"namespace\":\"org.leo.serialize.avro.domain\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":[\"int\",\"null\"]},{\"name\":\"color\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence name;
  @Deprecated public java.lang.Integer age;
  @Deprecated public java.lang.CharSequence color;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public Man() {}

  /**
   * All-args constructor.
   */
  public Man(java.lang.CharSequence name, java.lang.Integer age, java.lang.CharSequence color) {
    this.name = name;
    this.age = age;
    this.color = color;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return age;
    case 2: return color;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: age = (java.lang.Integer)value$; break;
    case 2: color = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'age' field.
   */
  public java.lang.Integer getAge() {
    return age;
  }

  /**
   * Sets the value of the 'age' field.
   * @param value the value to set.
   */
  public void setAge(java.lang.Integer value) {
    this.age = value;
  }

  /**
   * Gets the value of the 'color' field.
   */
  public java.lang.CharSequence getColor() {
    return color;
  }

  /**
   * Sets the value of the 'color' field.
   * @param value the value to set.
   */
  public void setColor(java.lang.CharSequence value) {
    this.color = value;
  }

  /** Creates a new Man RecordBuilder */
  public static org.leo.serialize.avro.domain.Man.Builder newBuilder() {
    return new org.leo.serialize.avro.domain.Man.Builder();
  }
  
  /** Creates a new Man RecordBuilder by copying an existing Builder */
  public static org.leo.serialize.avro.domain.Man.Builder newBuilder(org.leo.serialize.avro.domain.Man.Builder other) {
    return new org.leo.serialize.avro.domain.Man.Builder(other);
  }
  
  /** Creates a new Man RecordBuilder by copying an existing Man instance */
  public static org.leo.serialize.avro.domain.Man.Builder newBuilder(org.leo.serialize.avro.domain.Man other) {
    return new org.leo.serialize.avro.domain.Man.Builder(other);
  }
  
  /**
   * RecordBuilder for Man instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Man>
    implements org.apache.avro.data.RecordBuilder<Man> {

    private java.lang.CharSequence name;
    private java.lang.Integer age;
    private java.lang.CharSequence color;

    /** Creates a new Builder */
    private Builder() {
      super(org.leo.serialize.avro.domain.Man.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.leo.serialize.avro.domain.Man.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.color)) {
        this.color = data().deepCopy(fields()[2].schema(), other.color);
        fieldSetFlags()[2] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Man instance */
    private Builder(org.leo.serialize.avro.domain.Man other) {
            super(org.leo.serialize.avro.domain.Man.SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.color)) {
        this.color = data().deepCopy(fields()[2].schema(), other.color);
        fieldSetFlags()[2] = true;
      }
    }

    /** Gets the value of the 'name' field */
    public java.lang.CharSequence getName() {
      return name;
    }
    
    /** Sets the value of the 'name' field */
    public org.leo.serialize.avro.domain.Man.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'name' field has been set */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'name' field */
    public org.leo.serialize.avro.domain.Man.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'age' field */
    public java.lang.Integer getAge() {
      return age;
    }
    
    /** Sets the value of the 'age' field */
    public org.leo.serialize.avro.domain.Man.Builder setAge(java.lang.Integer value) {
      validate(fields()[1], value);
      this.age = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'age' field has been set */
    public boolean hasAge() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'age' field */
    public org.leo.serialize.avro.domain.Man.Builder clearAge() {
      age = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'color' field */
    public java.lang.CharSequence getColor() {
      return color;
    }
    
    /** Sets the value of the 'color' field */
    public org.leo.serialize.avro.domain.Man.Builder setColor(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.color = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'color' field has been set */
    public boolean hasColor() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'color' field */
    public org.leo.serialize.avro.domain.Man.Builder clearColor() {
      color = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public Man build() {
      try {
        Man record = new Man();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.age = fieldSetFlags()[1] ? this.age : (java.lang.Integer) defaultValue(fields()[1]);
        record.color = fieldSetFlags()[2] ? this.color : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
