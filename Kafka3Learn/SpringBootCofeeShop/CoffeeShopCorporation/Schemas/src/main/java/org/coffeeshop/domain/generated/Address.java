/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.coffeeshop.domain.generated;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Address extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1852833021001788915L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Address\",\"namespace\":\"org.coffeeshop.domain.generated\",\"fields\":[{\"name\":\"addressLine1\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"state_province\",\"type\":\"string\"},{\"name\":\"country\",\"type\":\"string\"},{\"name\":\"zip\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Address> ENCODER =
      new BinaryMessageEncoder<Address>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Address> DECODER =
      new BinaryMessageDecoder<Address>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Address> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Address> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Address> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Address>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Address to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Address from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Address instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Address fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence addressLine1;
  @Deprecated public java.lang.CharSequence city;
  @Deprecated public java.lang.CharSequence state_province;
  @Deprecated public java.lang.CharSequence country;
  @Deprecated public java.lang.CharSequence zip;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Address() {}

  /**
   * All-args constructor.
   * @param addressLine1 The new value for addressLine1
   * @param city The new value for city
   * @param state_province The new value for state_province
   * @param country The new value for country
   * @param zip The new value for zip
   */
  public Address(java.lang.CharSequence addressLine1, java.lang.CharSequence city, java.lang.CharSequence state_province, java.lang.CharSequence country, java.lang.CharSequence zip) {
    this.addressLine1 = addressLine1;
    this.city = city;
    this.state_province = state_province;
    this.country = country;
    this.zip = zip;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return addressLine1;
    case 1: return city;
    case 2: return state_province;
    case 3: return country;
    case 4: return zip;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: addressLine1 = (java.lang.CharSequence)value$; break;
    case 1: city = (java.lang.CharSequence)value$; break;
    case 2: state_province = (java.lang.CharSequence)value$; break;
    case 3: country = (java.lang.CharSequence)value$; break;
    case 4: zip = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'addressLine1' field.
   * @return The value of the 'addressLine1' field.
   */
  public java.lang.CharSequence getAddressLine1() {
    return addressLine1;
  }


  /**
   * Sets the value of the 'addressLine1' field.
   * @param value the value to set.
   */
  public void setAddressLine1(java.lang.CharSequence value) {
    this.addressLine1 = value;
  }

  /**
   * Gets the value of the 'city' field.
   * @return The value of the 'city' field.
   */
  public java.lang.CharSequence getCity() {
    return city;
  }


  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.CharSequence value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'state_province' field.
   * @return The value of the 'state_province' field.
   */
  public java.lang.CharSequence getStateProvince() {
    return state_province;
  }


  /**
   * Sets the value of the 'state_province' field.
   * @param value the value to set.
   */
  public void setStateProvince(java.lang.CharSequence value) {
    this.state_province = value;
  }

  /**
   * Gets the value of the 'country' field.
   * @return The value of the 'country' field.
   */
  public java.lang.CharSequence getCountry() {
    return country;
  }


  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(java.lang.CharSequence value) {
    this.country = value;
  }

  /**
   * Gets the value of the 'zip' field.
   * @return The value of the 'zip' field.
   */
  public java.lang.CharSequence getZip() {
    return zip;
  }


  /**
   * Sets the value of the 'zip' field.
   * @param value the value to set.
   */
  public void setZip(java.lang.CharSequence value) {
    this.zip = value;
  }

  /**
   * Creates a new Address RecordBuilder.
   * @return A new Address RecordBuilder
   */
  public static org.coffeeshop.domain.generated.Address.Builder newBuilder() {
    return new org.coffeeshop.domain.generated.Address.Builder();
  }

  /**
   * Creates a new Address RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Address RecordBuilder
   */
  public static org.coffeeshop.domain.generated.Address.Builder newBuilder(org.coffeeshop.domain.generated.Address.Builder other) {
    if (other == null) {
      return new org.coffeeshop.domain.generated.Address.Builder();
    } else {
      return new org.coffeeshop.domain.generated.Address.Builder(other);
    }
  }

  /**
   * Creates a new Address RecordBuilder by copying an existing Address instance.
   * @param other The existing instance to copy.
   * @return A new Address RecordBuilder
   */
  public static org.coffeeshop.domain.generated.Address.Builder newBuilder(org.coffeeshop.domain.generated.Address other) {
    if (other == null) {
      return new org.coffeeshop.domain.generated.Address.Builder();
    } else {
      return new org.coffeeshop.domain.generated.Address.Builder(other);
    }
  }

  /**
   * RecordBuilder for Address instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Address>
    implements org.apache.avro.data.RecordBuilder<Address> {

    private java.lang.CharSequence addressLine1;
    private java.lang.CharSequence city;
    private java.lang.CharSequence state_province;
    private java.lang.CharSequence country;
    private java.lang.CharSequence zip;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.coffeeshop.domain.generated.Address.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.addressLine1)) {
        this.addressLine1 = data().deepCopy(fields()[0].schema(), other.addressLine1);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.city)) {
        this.city = data().deepCopy(fields()[1].schema(), other.city);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.state_province)) {
        this.state_province = data().deepCopy(fields()[2].schema(), other.state_province);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.zip)) {
        this.zip = data().deepCopy(fields()[4].schema(), other.zip);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing Address instance
     * @param other The existing instance to copy.
     */
    private Builder(org.coffeeshop.domain.generated.Address other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.addressLine1)) {
        this.addressLine1 = data().deepCopy(fields()[0].schema(), other.addressLine1);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.city)) {
        this.city = data().deepCopy(fields()[1].schema(), other.city);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.state_province)) {
        this.state_province = data().deepCopy(fields()[2].schema(), other.state_province);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.zip)) {
        this.zip = data().deepCopy(fields()[4].schema(), other.zip);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'addressLine1' field.
      * @return The value.
      */
    public java.lang.CharSequence getAddressLine1() {
      return addressLine1;
    }


    /**
      * Sets the value of the 'addressLine1' field.
      * @param value The value of 'addressLine1'.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder setAddressLine1(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.addressLine1 = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'addressLine1' field has been set.
      * @return True if the 'addressLine1' field has been set, false otherwise.
      */
    public boolean hasAddressLine1() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'addressLine1' field.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder clearAddressLine1() {
      addressLine1 = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'city' field.
      * @return The value.
      */
    public java.lang.CharSequence getCity() {
      return city;
    }


    /**
      * Sets the value of the 'city' field.
      * @param value The value of 'city'.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder setCity(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.city = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'city' field has been set.
      * @return True if the 'city' field has been set, false otherwise.
      */
    public boolean hasCity() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'city' field.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder clearCity() {
      city = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'state_province' field.
      * @return The value.
      */
    public java.lang.CharSequence getStateProvince() {
      return state_province;
    }


    /**
      * Sets the value of the 'state_province' field.
      * @param value The value of 'state_province'.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder setStateProvince(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.state_province = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'state_province' field has been set.
      * @return True if the 'state_province' field has been set, false otherwise.
      */
    public boolean hasStateProvince() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'state_province' field.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder clearStateProvince() {
      state_province = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'country' field.
      * @return The value.
      */
    public java.lang.CharSequence getCountry() {
      return country;
    }


    /**
      * Sets the value of the 'country' field.
      * @param value The value of 'country'.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder setCountry(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.country = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'country' field has been set.
      * @return True if the 'country' field has been set, false otherwise.
      */
    public boolean hasCountry() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'country' field.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder clearCountry() {
      country = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'zip' field.
      * @return The value.
      */
    public java.lang.CharSequence getZip() {
      return zip;
    }


    /**
      * Sets the value of the 'zip' field.
      * @param value The value of 'zip'.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder setZip(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.zip = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'zip' field has been set.
      * @return True if the 'zip' field has been set, false otherwise.
      */
    public boolean hasZip() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'zip' field.
      * @return This builder.
      */
    public org.coffeeshop.domain.generated.Address.Builder clearZip() {
      zip = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Address build() {
      try {
        Address record = new Address();
        record.addressLine1 = fieldSetFlags()[0] ? this.addressLine1 : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.city = fieldSetFlags()[1] ? this.city : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.state_province = fieldSetFlags()[2] ? this.state_province : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.country = fieldSetFlags()[3] ? this.country : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.zip = fieldSetFlags()[4] ? this.zip : (java.lang.CharSequence) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Address>
    WRITER$ = (org.apache.avro.io.DatumWriter<Address>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Address>
    READER$ = (org.apache.avro.io.DatumReader<Address>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.addressLine1);

    out.writeString(this.city);

    out.writeString(this.state_province);

    out.writeString(this.country);

    out.writeString(this.zip);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.addressLine1 = in.readString(this.addressLine1 instanceof Utf8 ? (Utf8)this.addressLine1 : null);

      this.city = in.readString(this.city instanceof Utf8 ? (Utf8)this.city : null);

      this.state_province = in.readString(this.state_province instanceof Utf8 ? (Utf8)this.state_province : null);

      this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);

      this.zip = in.readString(this.zip instanceof Utf8 ? (Utf8)this.zip : null);

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.addressLine1 = in.readString(this.addressLine1 instanceof Utf8 ? (Utf8)this.addressLine1 : null);
          break;

        case 1:
          this.city = in.readString(this.city instanceof Utf8 ? (Utf8)this.city : null);
          break;

        case 2:
          this.state_province = in.readString(this.state_province instanceof Utf8 ? (Utf8)this.state_province : null);
          break;

        case 3:
          this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);
          break;

        case 4:
          this.zip = in.readString(this.zip instanceof Utf8 ? (Utf8)this.zip : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










