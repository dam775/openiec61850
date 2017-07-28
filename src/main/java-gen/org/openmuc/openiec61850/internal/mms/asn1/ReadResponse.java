/**
 * This class file was automatically generated by jASN1 v1.8.0 (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.BerTag;

public class ReadResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static class ListOfAccessResult implements Serializable {

        private static final long serialVersionUID = 1L;

        public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
        public byte[] code = null;
        private List<AccessResult> seqOf = null;

        public ListOfAccessResult() {
            seqOf = new ArrayList<>();
        }

        public ListOfAccessResult(byte[] code) {
            this.code = code;
        }

        public List<AccessResult> getAccessResult() {
            if (seqOf == null) {
                seqOf = new ArrayList<>();
            }
            return seqOf;
        }

        public int encode(BerByteArrayOutputStream os) throws IOException {
            return encode(os, true);
        }

        public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

            if (code != null) {
                for (int i = code.length - 1; i >= 0; i--) {
                    os.write(code[i]);
                }
                if (withTag) {
                    return tag.encode(os) + code.length;
                }
                return code.length;
            }

            int codeLength = 0;
            for (int i = (seqOf.size() - 1); i >= 0; i--) {
                codeLength += seqOf.get(i).encode(os);
            }

            codeLength += BerLength.encodeLength(os, codeLength);

            if (withTag) {
                codeLength += tag.encode(os);
            }

            return codeLength;
        }

        public int decode(InputStream is) throws IOException {
            return decode(is, true);
        }

        public int decode(InputStream is, boolean withTag) throws IOException {
            int codeLength = 0;
            int subCodeLength = 0;
            if (withTag) {
                codeLength += tag.decodeAndCheck(is);
            }

            BerLength length = new BerLength();
            codeLength += length.decode(is);
            int totalLength = length.val;

            while (subCodeLength < totalLength) {
                AccessResult element = new AccessResult();
                subCodeLength += element.decode(is, null);
                seqOf.add(element);
            }
            if (subCodeLength != totalLength) {
                throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength
                        + " but has " + subCodeLength);

            }
            codeLength += subCodeLength;

            return codeLength;
        }

        public void encodeAndSave(int encodingSizeGuess) throws IOException {
            BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
            encode(os, false);
            code = os.getArray();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAsString(sb, 0);
            return sb.toString();
        }

        public void appendAsString(StringBuilder sb, int indentLevel) {

            sb.append("{\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            if (seqOf == null) {
                sb.append("null");
            }
            else {
                Iterator<AccessResult> it = seqOf.iterator();
                if (it.hasNext()) {
                    it.next().appendAsString(sb, indentLevel + 1);
                    while (it.hasNext()) {
                        sb.append(",\n");
                        for (int i = 0; i < indentLevel + 1; i++) {
                            sb.append("\t");
                        }
                        it.next().appendAsString(sb, indentLevel + 1);
                    }
                }
            }

            sb.append("\n");
            for (int i = 0; i < indentLevel; i++) {
                sb.append("\t");
            }
            sb.append("}");
        }

    }

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

    public byte[] code = null;
    private VariableAccessSpecification variableAccessSpecification = null;
    private ListOfAccessResult listOfAccessResult = null;

    public ReadResponse() {
    }

    public ReadResponse(byte[] code) {
        this.code = code;
    }

    public void setVariableAccessSpecification(VariableAccessSpecification variableAccessSpecification) {
        this.variableAccessSpecification = variableAccessSpecification;
    }

    public VariableAccessSpecification getVariableAccessSpecification() {
        return variableAccessSpecification;
    }

    public void setListOfAccessResult(ListOfAccessResult listOfAccessResult) {
        this.listOfAccessResult = listOfAccessResult;
    }

    public ListOfAccessResult getListOfAccessResult() {
        return listOfAccessResult;
    }

    public int encode(BerByteArrayOutputStream os) throws IOException {
        return encode(os, true);
    }

    public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            if (withTag) {
                return tag.encode(os) + code.length;
            }
            return code.length;
        }

        int codeLength = 0;
        int sublength;

        codeLength += listOfAccessResult.encode(os, false);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
        os.write(0xA1);
        codeLength += 1;

        if (variableAccessSpecification != null) {
            sublength = variableAccessSpecification.encode(os);
            codeLength += sublength;
            codeLength += BerLength.encodeLength(os, sublength);
            // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
            os.write(0xA0);
            codeLength += 1;
        }

        codeLength += BerLength.encodeLength(os, codeLength);

        if (withTag) {
            codeLength += tag.encode(os);
        }

        return codeLength;

    }

    public int decode(InputStream is) throws IOException {
        return decode(is, true);
    }

    public int decode(InputStream is, boolean withTag) throws IOException {
        int codeLength = 0;
        int subCodeLength = 0;
        BerTag berTag = new BerTag();

        if (withTag) {
            codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);

        int totalLength = length.val;
        codeLength += totalLength;

        subCodeLength += berTag.decode(is);
        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
            subCodeLength += length.decode(is);
            variableAccessSpecification = new VariableAccessSpecification();
            subCodeLength += variableAccessSpecification.decode(is, null);
            subCodeLength += berTag.decode(is);
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
            listOfAccessResult = new ListOfAccessResult();
            subCodeLength += listOfAccessResult.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
        }
        throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: "
                + subCodeLength);

    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
        encode(os, false);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        sb.append("{");
        boolean firstSelectedElement = true;
        if (variableAccessSpecification != null) {
            sb.append("\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("variableAccessSpecification: ");
            variableAccessSpecification.appendAsString(sb, indentLevel + 1);
            firstSelectedElement = false;
        }

        if (!firstSelectedElement) {
            sb.append(",\n");
        }
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (listOfAccessResult != null) {
            sb.append("listOfAccessResult: ");
            listOfAccessResult.appendAsString(sb, indentLevel + 1);
        }
        else {
            sb.append("listOfAccessResult: <empty-required-field>");
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

}