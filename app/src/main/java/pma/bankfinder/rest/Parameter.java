package pma.bankfinder.rest;

/**
 * Created by rade on 6/29/16.
 */
public class Parameter {
    private String fieldName;
    private String fieldValue;

    public Parameter() {

    }

    public Parameter(String fieldName, String value) {
        this.fieldName = fieldName;
        this.fieldValue = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
