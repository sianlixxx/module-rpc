/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.busap.rpc.thrift.idl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-1-27")
public class BadRequestException extends TException implements org.apache.thrift.TBase<BadRequestException, BadRequestException._Fields>, java.io.Serializable, Cloneable, Comparable<BadRequestException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BadRequestException");

  private static final org.apache.thrift.protocol.TField REQUEST_FIELD_DESC = new org.apache.thrift.protocol.TField("Request", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField ERROR_MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("ErrorMessage", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new BadRequestExceptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new BadRequestExceptionTupleSchemeFactory());
  }

  public ThriftSRequest Request; // required
  public String ErrorMessage; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    REQUEST((short)1, "Request"),
    ERROR_MESSAGE((short)2, "ErrorMessage");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // REQUEST
          return REQUEST;
        case 2: // ERROR_MESSAGE
          return ERROR_MESSAGE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REQUEST, new org.apache.thrift.meta_data.FieldMetaData("Request", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ThriftSRequest.class)));
    tmpMap.put(_Fields.ERROR_MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("ErrorMessage", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BadRequestException.class, metaDataMap);
  }

  public BadRequestException() {
  }

  public BadRequestException(
    ThriftSRequest Request,
    String ErrorMessage)
  {
    this();
    this.Request = Request;
    this.ErrorMessage = ErrorMessage;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BadRequestException(BadRequestException other) {
    if (other.isSetRequest()) {
      this.Request = new ThriftSRequest(other.Request);
    }
    if (other.isSetErrorMessage()) {
      this.ErrorMessage = other.ErrorMessage;
    }
  }

  public BadRequestException deepCopy() {
    return new BadRequestException(this);
  }

  @Override
  public void clear() {
    this.Request = null;
    this.ErrorMessage = null;
  }

  public ThriftSRequest getRequest() {
    return this.Request;
  }

  public BadRequestException setRequest(ThriftSRequest Request) {
    this.Request = Request;
    return this;
  }

  public void unsetRequest() {
    this.Request = null;
  }

  /** Returns true if field Request is set (has been assigned a value) and false otherwise */
  public boolean isSetRequest() {
    return this.Request != null;
  }

  public void setRequestIsSet(boolean value) {
    if (!value) {
      this.Request = null;
    }
  }

  public String getErrorMessage() {
    return this.ErrorMessage;
  }

  public BadRequestException setErrorMessage(String ErrorMessage) {
    this.ErrorMessage = ErrorMessage;
    return this;
  }

  public void unsetErrorMessage() {
    this.ErrorMessage = null;
  }

  /** Returns true if field ErrorMessage is set (has been assigned a value) and false otherwise */
  public boolean isSetErrorMessage() {
    return this.ErrorMessage != null;
  }

  public void setErrorMessageIsSet(boolean value) {
    if (!value) {
      this.ErrorMessage = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case REQUEST:
      if (value == null) {
        unsetRequest();
      } else {
        setRequest((ThriftSRequest)value);
      }
      break;

    case ERROR_MESSAGE:
      if (value == null) {
        unsetErrorMessage();
      } else {
        setErrorMessage((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case REQUEST:
      return getRequest();

    case ERROR_MESSAGE:
      return getErrorMessage();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case REQUEST:
      return isSetRequest();
    case ERROR_MESSAGE:
      return isSetErrorMessage();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof BadRequestException)
      return this.equals((BadRequestException)that);
    return false;
  }

  public boolean equals(BadRequestException that) {
    if (that == null)
      return false;

    boolean this_present_Request = true && this.isSetRequest();
    boolean that_present_Request = true && that.isSetRequest();
    if (this_present_Request || that_present_Request) {
      if (!(this_present_Request && that_present_Request))
        return false;
      if (!this.Request.equals(that.Request))
        return false;
    }

    boolean this_present_ErrorMessage = true && this.isSetErrorMessage();
    boolean that_present_ErrorMessage = true && that.isSetErrorMessage();
    if (this_present_ErrorMessage || that_present_ErrorMessage) {
      if (!(this_present_ErrorMessage && that_present_ErrorMessage))
        return false;
      if (!this.ErrorMessage.equals(that.ErrorMessage))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_Request = true && (isSetRequest());
    list.add(present_Request);
    if (present_Request)
      list.add(Request);

    boolean present_ErrorMessage = true && (isSetErrorMessage());
    list.add(present_ErrorMessage);
    if (present_ErrorMessage)
      list.add(ErrorMessage);

    return list.hashCode();
  }

  @Override
  public int compareTo(BadRequestException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRequest()).compareTo(other.isSetRequest());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRequest()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Request, other.Request);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetErrorMessage()).compareTo(other.isSetErrorMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrorMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ErrorMessage, other.ErrorMessage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BadRequestException(");
    boolean first = true;

    sb.append("Request:");
    if (this.Request == null) {
      sb.append("null");
    } else {
      sb.append(this.Request);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ErrorMessage:");
    if (this.ErrorMessage == null) {
      sb.append("null");
    } else {
      sb.append(this.ErrorMessage);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (Request == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'Request' was not present! Struct: " + toString());
    }
    if (ErrorMessage == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'ErrorMessage' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (Request != null) {
      Request.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class BadRequestExceptionStandardSchemeFactory implements SchemeFactory {
    public BadRequestExceptionStandardScheme getScheme() {
      return new BadRequestExceptionStandardScheme();
    }
  }

  private static class BadRequestExceptionStandardScheme extends StandardScheme<BadRequestException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BadRequestException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REQUEST
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.Request = new ThriftSRequest();
              struct.Request.read(iprot);
              struct.setRequestIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ERROR_MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ErrorMessage = iprot.readString();
              struct.setErrorMessageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, BadRequestException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.Request != null) {
        oprot.writeFieldBegin(REQUEST_FIELD_DESC);
        struct.Request.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.ErrorMessage != null) {
        oprot.writeFieldBegin(ERROR_MESSAGE_FIELD_DESC);
        oprot.writeString(struct.ErrorMessage);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BadRequestExceptionTupleSchemeFactory implements SchemeFactory {
    public BadRequestExceptionTupleScheme getScheme() {
      return new BadRequestExceptionTupleScheme();
    }
  }

  private static class BadRequestExceptionTupleScheme extends TupleScheme<BadRequestException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BadRequestException struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.Request.write(oprot);
      oprot.writeString(struct.ErrorMessage);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BadRequestException struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.Request = new ThriftSRequest();
      struct.Request.read(iprot);
      struct.setRequestIsSet(true);
      struct.ErrorMessage = iprot.readString();
      struct.setErrorMessageIsSet(true);
    }
  }

}

