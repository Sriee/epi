package util;

import java.lang.reflect.Array;

public class ArrayUtil {

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static byte[] clone(byte[] arr){
        if(isEmpty(arr)) return null;

        byte[] result = new byte[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static short[] clone(short[] arr){
        if(isEmpty(arr)) return null;

        short[] result = new short[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static boolean[] clone(boolean[] arr){
        if(isEmpty(arr)) return null;

        boolean[] result = new boolean[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static char[] clone(char[] arr){
        if(isEmpty(arr)) return null;

        char[] result = new char[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static int[] clone(int[] arr){
        if(isEmpty(arr)) return null;

        int[] result = new int[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static long[] clone(long[] arr){
        if(isEmpty(arr)) return null;

        long[] result = new long[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static float[] clone(float[] arr){
        if(isEmpty(arr)) return null;

        float[] result = new float[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static double[] clone(double[] arr){
        if(isEmpty(arr)) return null;

        double[] result = new double[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Performs deep copy of the contents of the array
     * This method returns null for a null input array or an empty array.
     *
     * @param clazz Data Type
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static <Z> Z[] clone(Class<Z> clazz, Z[] arr){
        if(isEmpty(arr)) return null;

        Z[] result = (Z[]) Array.newInstance(clazz, arr.length);
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(byte[] arr, byte value){
        if(isEmpty(arr)) return false;

        for(byte n : arr){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(short[] arr, short value){
        if(isEmpty(arr)) return false;

        for(short n : arr){
            if(arr[n] == value) return true;
        }
        return false;
    }
    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(boolean[] arr, boolean value){
        if(isEmpty(arr)) return false;

        for(int n = 0; n < arr.length; n++){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(char[] arr, char value){
        if(isEmpty(arr)) return false;

        for(int n = 0; n < arr.length; n++){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(int[] arr, int value){
        if(isEmpty(arr)) return false;

        for(int n : arr){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(long[] arr, long value){
        if(isEmpty(arr)) return false;

        for(int n = 0; n < arr.length; n++){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(float[] arr, float value){
        if(isEmpty(arr)) return false;

        for(int n = 0; n < arr.length; n++){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static boolean contains(double[] arr, double value){
        if(isEmpty(arr)) return false;

        for(int n = 0; n < arr.length; n++){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if the value is in the given array.
     *
     * @param arr the array to search through
     * @param value the value to find
     * @return true if the value is present; false if the value is not present or array is null or array is empty
     */
    public static <Y> boolean contains(Y[] arr, Y value){
        if(isEmpty(arr)) return false;

        for(int n = 0; n < arr.length; n++){
            if(arr[n] == value) return true;
        }
        return false;
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(byte[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(short[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(boolean[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(char[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(int[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(long[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(float[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static boolean isEmpty(double[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of generic type is empty or null.
     *
     * @param arr the array to test
     * @return true if the array is empty or null; false otherwise
     */
    public static <T> boolean isEmpty(T[] arr){
        return (arr == null || arr.length == 0);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(byte[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(short[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(boolean[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(char[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(int[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(long[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(float[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of primitive type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static boolean isNotEmpty(double[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks if an array of generic type is not empty or not null.
     * @param arr the array to test
     * @return true if the array is not empty or not null; false otherwise
     */
    public static <T> boolean isNotEmpty(T[] arr){
        return !isEmpty(arr);
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(short[] array1, short[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(byte[] array1, byte[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(boolean[] array1, boolean[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(char[] array1, char[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(int[] array1, int[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(long[] array1, long[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(float[] array1, float[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static boolean isSameLength(double[] array1, double[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same length
     *
     * @param array1 the first array,
     * @param array2 the second array
     * @return true if length of arrays matches; false if length of arrays mismatch or if they are null
     */
    public static <S> boolean isSameLength(S[] array1, S[] array2) {
        return array1 != null && array2 != null && array1.length == array2.length;
    }

    /**
     * Checks whether two arrays are the same type. Handles only one-dimensional array
     *
     * @param first the first array
     * @param second the second array
     * @return true if type of arrays matches; false if the array type mismatches or either of the array is null
     */
    public static boolean isSameType(Object first, Object second) {
        return first != null && second != null && first.getClass().equals(second.getClass());
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Byte[] nullToEmpty(Byte[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = 0;
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Short[] nullToEmpty(Short[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = 0;
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Boolean[] nullToEmpty(Boolean[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = false;
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Character[] nullToEmpty(Character[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = ' ';
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Integer[] nullToEmpty(Integer[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = 0;
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Long[] nullToEmpty(Long[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = (long)0;
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Float[] nullToEmpty(Float[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = (float)0.0;
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static Double[] nullToEmpty(Double[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = 0.0;
        }
        return arr;
    }

    /**
     * Changes 'null' reference to an empty one based on it's type.
     *
     * @param arr the array to check for 'null'
     * @return the same array with 'null' fields replaced
     */
    public static String[] nullToEmpty(String[] arr){
        if(arr == null)
            return null;

        for(int j = 0; j < arr.length; j++){
            if(arr[j] == null) arr[j] = " ";
        }
        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static byte[] reverse(byte[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        byte temp = -1;

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static short[] reverse(short[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        short temp = -1;

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static boolean[] reverse(boolean[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        boolean temp;

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static char[] reverse(char[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        char temp = ' ';

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static int[] reverse(int[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        int temp = -1;

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static long[] reverse(long[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        long temp = -1;

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static float[] reverse(float[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        float temp = -1;

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Reverses the order of the given array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static double[] reverse(double[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        double temp = -1;

        while(start <= end){
            temp = arr[start]; arr[start] = arr[end]; arr[end] = temp;
            start++; end--;
        }

        return arr;
    }

    /**
     * Produces a new byte array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static byte[] subarray(byte[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        byte[] result = new byte[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new short array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static short[] subarray(short[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        short[] result = new short[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new boolean array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static boolean[] subarray(boolean[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        boolean[] result = new boolean[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new char array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static char[] subarray(char[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        char[] result = new char[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new integer array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static int[] subarray(int[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        int[] result = new int[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new long array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static long[] subarray(long[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        long[] result = new long[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new float array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static float[] subarray(float[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        float[] result = new float[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new double array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static double[] subarray(double[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        double[] result = new double[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new generic array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static <N> N[] subarray(Class<N> clazz, N[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || start > stop) return null;
        @SuppressWarnings("unchecked")
        N[] result = (N[])Array.newInstance(clazz, stop - start + 1);
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Produces a new Object array containing the elements between the start and end indices.
     *
     * @param input the array
     * @param start the starting index (inclusive)
     * @param stop the end index (inclusive)
     * @return a new array containing the elements between the start and end indices.
     *         'null' array input produces 'null' output.
     */
    public static Object[] subarray(Object[] input, int start, int stop){
        if(start < 0 || stop < 0 || isEmpty(input) || stop >= input.length || start > stop) return null;
        Object[] result = new Object[stop - start + 1];
        System.arraycopy(input, start, result,0,stop - start + 1);
        return result;
    }

    /**
     * Converts an array of primitive bytes to objects.
     *
     * @param arr a byte array
     * @return a Byte array, 'null' if 'null' array input
     */
    public static Byte[] toObject(byte[] arr){
        Byte result[] = new Byte[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of primitive short to objects.
     *
     * @param arr a short array
     * @return a Short array, 'null' if 'null' array input
     */
    public static Short[] toObject(short[] arr){
        Short result[] = new Short[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of primitive boolean to objects.
     *
     * @param arr a boolean array
     * @return a Boolean array, 'null' if 'null' array input
     */
    public static Boolean[] toObject(boolean[] arr){
        Boolean result[] = new Boolean[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of primitive char to objects.
     *
     * @param arr a char array
     * @return a Character array, 'null' if 'null' array input
     */
    public static Character[] toObject(char[] arr){
        Character result[] = new Character[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of primitive int to objects.
     *
     * @param arr a int array
     * @return an Integer array, 'null' if 'null' array input
     */
    public static Integer[] toObject(int[] arr){
        Integer result[] = new Integer[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of primitive long to objects.
     *
     * @param arr a long array
     * @return a Long array, 'null' if 'null' array input
     */
    public static Long[] toObject(long[] arr){
        Long result[] = new Long[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of primitive float to objects.
     *
     * @param arr a float array
     * @return a Float array, 'null' if 'null' array input
     */
    public static Float[] toObject(float[] arr){
        Float result[] = new Float[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of primitive double to objects.
     *
     * @param arr a double array
     * @return a Double array, 'null' if 'null' array input
     */
    public static Double[] toObject(double[] arr){
        Double result[] = new Double[arr.length];
        for(int j = 0; j < arr.length; j++)
            result[j] = arr[j];

        return result;
    }

    /**
     * Converts an array of object Byte to primitives
     *
     * @param arr a Byte array
     * @return a byte array, 'null' if 'null' array input
     */
    public static byte[] toPrimitive(Byte[] arr){
        if(isEmpty(arr)) return null;

        byte[] result = new byte[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

    /**
     * Converts an array of object Shorts to primitives
     *
     * @param arr a Short array
     * @return a short array, 'null' if 'null' array input
     */
    public static short[] toPrimitive(Short[] arr){
        if(isEmpty(arr)) return null;

        short[] result = new short[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

    /**
     * Converts an array of object Boolean to primitives
     *
     * @param arr a Boolean array
     * @return a boolean array, 'null' if 'null' array input
     */
    public static boolean[] toPrimitive(Boolean[] arr){
        if(isEmpty(arr)) return null;

        boolean[] result = new boolean[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

    /**
     * Converts an array of object Character to primitives
     *
     * @param arr a Character array
     * @return a char array, 'null' if 'null' array input
     */
    public static char[] toPrimitive(Character[] arr){
        if(isEmpty(arr)) return null;

        char[] result = new char[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

    /**
     * Converts an array of object Integer to primitives
     *
     * @param arr an Integer array
     * @return an int array, 'null' if 'null' array input
     */
    public static int[] toPrimitive(Integer[] arr){
        if(isEmpty(arr)) return null;

        int[] result = new int[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

    /**
     * Converts an array of object Long to primitives
     *
     * @param arr a Long array
     * @return a long array, 'null' if 'null' array input
     */
    public static long[] toPrimitive(Long[] arr){
        if(isEmpty(arr)) return null;

        long[] result = new long[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

    /**
     * Converts an array of object Float to primitives
     *
     * @param arr a Float array
     * @return a float array, 'null' if 'null' array input
     */
    public static float[] toPrimitive(Float[] arr){
        if(isEmpty(arr)) return null;

        float[] result = new float[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

    /**
     * Converts an array of object Double to primitives
     *
     * @param arr a Double array
     * @return a double array, 'null' if 'null' array input
     */
    public static double[] toPrimitive(Double[] arr){
        if(isEmpty(arr)) return null;

        double[] result = new double[arr.length];
        for(int k = 0; k < arr.length; k++)
            result[k] = arr[k];
        return result;
    }

}
