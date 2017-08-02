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
}
