package util;

import java.lang.reflect.Array;
import java.util.TreeSet;

public class ArrayUtil {

    private static final int INDEX_NOT_FOUND = -1;

    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static byte[] addAll(byte[] first, byte[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new byte[] {};
    	
    	byte[] united = new byte[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static short[] addAll(short[] first, short[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new short[] {};
    	
    	short[] united = new short[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static boolean[] addAll(boolean[] first, boolean[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new boolean[] {};
    	
    	boolean[] united = new boolean[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static char[] addAll(char[] first, char[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new char[] {};
    	
    	char[] united = new char[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static int[] addAll(int[] first, int[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new int[] {};
    	
    	int[] united = new int[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static long[] addAll(long[] first, long[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new long[] {};
    	
    	long[] united = new long[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static float[] addAll(float[] first, float[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new float[] {};
    	
    	float[] united = new float[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    public static double[] addAll(double[] first, double[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return new double[] {};
    	
    	double[] united = new double[firstLen + secondLen];
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Adds all the elements of the given arrays into a new array.
     * The new array contains all of the element of array1 followed by all of the elements array2. 
     * When an array is returned, it is always a new array. 
     * 
     * @param first the first array
     * @param second the second array
     * @return new byte[] array
     */
    @SuppressWarnings("unchecked")
    public static <W> W[] addAll(Class<W> clazz, W[] first, W[] second){
    	int firstLen = 0, secondLen = 0; 
    	firstLen = (first == null)? 0 : first.length;
    	secondLen = (second == null)? 0 : second.length;
    	
    	if(firstLen == 0 && secondLen == 0) return (W[])Array.newInstance(clazz, 0);
    	
    	W[] united = (W[])Array.newInstance(clazz, firstLen + secondLen);
    	System.arraycopy(first, 0, united, 0, firstLen);
    	System.arraycopy(second, 0, united, firstLen, secondLen);
    	return united;
    }
    
    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static byte[] append(byte[] src, byte valueToAppend){

        byte[] dst = null;
        if(isEmpty(src)){
            dst = new byte[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new byte[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static short[] append(short[] src, short valueToAppend){

        short[] dst = null;
        if(isEmpty(src)){
            dst = new short[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new short[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static boolean[] append(boolean[] src, boolean valueToAppend){

        boolean[] dst = null;
        if(isEmpty(src)){
            dst = new boolean[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new boolean[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static char[] append(char[] src, char valueToAppend){

        char[] dst = null;
        if(isEmpty(src)){
            dst = new char[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new char[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static int[] append(int[] src, int valueToAppend){

        int[] dst = null;
        if(isEmpty(src)){
            dst = new int[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new int[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static long[] append(long[] src, long valueToAppend){

        long[] dst = null;
        if(isEmpty(src)){
            dst = new long[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new long[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static float[] append(float[] src, float valueToAppend){

        float[] dst = null;
        if(isEmpty(src)){
            dst = new float[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new float[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    public static double[] append(double[] src, double valueToAppend){

        double[] dst = null;
        if(isEmpty(src)){
            dst = new double[1];
            dst[0] = valueToAppend;
            return dst;
        }

        dst = new double[src.length + 1];
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

    /**
     * Appends the given element at the end of the new array.
     *
     * @param src the array, can be null
     * @param valueToAppend the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     */
    @SuppressWarnings("unchecked")
	public static <Q> Q[] append(Class<Q> clazz, Q[] src, Q valueToAppend){

        Q[] dst = null;
        if(isEmpty(src)){
            dst = (Q[])Array.newInstance(clazz, 1);;
            dst[0] = valueToAppend;
            return dst;
        }

        dst = (Q[])Array.newInstance(clazz, src.length + 1);

        System.arraycopy(src, 0, dst, 0, src.length);
        dst[src.length] = valueToAppend;
        return dst;
    }

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
        
        @SuppressWarnings("unchecked")
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
            if(n == value) return true;
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
            if(n == value) return true;
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

        for(boolean bool : arr){
            if(bool == value) return true;
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

        for(char ch : arr){
            if(ch == value) return true;
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
            if(n == value) return true;
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

        for(long l: arr){
            if(l == value) return true;
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

        for(float f : arr){
            if(f == value) return true;
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

        for(double d : arr){
            if(d == value) return true;
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

        for(Y element : arr){
            if(element.equals(value)) return true;
        }
        return false;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static byte[] insert(byte[] array, int index, byte... valuesToInsert) {
        byte[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new byte[valuesToInsert.length];
            for (byte element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new byte[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (byte element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static short[] insert(short[] array, int index, short... valuesToInsert) {
        short[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new short[valuesToInsert.length];
            for (short element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new short[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (short element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static boolean[] insert(boolean[] array, int index, boolean... valuesToInsert) {
        boolean[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new boolean[valuesToInsert.length];
            for (boolean element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new boolean[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (boolean element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static char[] insert(char[] array, int index, char... valuesToInsert) {
        char[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new char[valuesToInsert.length];
            for (char element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new char[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (char element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static int[] insert(int[] array, int index, int... valuesToInsert){
        int[] result = null;
        int k = 0;

        if(array == null || array.length == 0){
            result = new int[valuesToInsert.length];
            for(int element : valuesToInsert) {
                result[k] = element; k++;
            }
            return result;
        }

        if(index < 0 || index > array.length)
            return null;

        result = new int[array.length + valuesToInsert.length]; k = index;
        System.arraycopy(array, 0, result, 0, index);

        for(int element : valuesToInsert) {
            result[k] = element; k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static long[] insert(long[] array, int index, long... valuesToInsert) {
        long[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new long[valuesToInsert.length];
            for (long element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new long[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (long element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static float[] insert(float[] array, int index, float... valuesToInsert) {
        float[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new float[valuesToInsert.length];
            for (float element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new float[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (float element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static double[] insert(double[] array, int index, double... valuesToInsert) {
        double[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new double[valuesToInsert.length];
            for (double element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new double[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (double element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @return A new array containing the existing elements and the new element
     */
    public static Object[] insert(Object[] array, int index, Object... valuesToInsert) {
        Object[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = new Object[valuesToInsert.length];
            for (Object element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = new Object[array.length + valuesToInsert.length];
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (Object element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
    }

    /**
     * Inserts the element(s) at the specified position in the array. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right.
     *
     * Example:
     *
     *  ArrayUtil.insert(null, 3, -12)      = [-12]
     *  ArrayUtil.insert([1], 0, 2)         = [2, 1]
     *  ArrayUtil.insert([1], 1, 2, -12, 3) = [1, 2, -12, 3]
     *
     * @param clazz - Type of Array
     * @param array - the array to insert the element
     * @param index - the position of the new object
     * @param valuesToInsert - the object to add
     * @param <I> - type of element
     * @return A new array containing the existing elements and the new element
     */
    @SuppressWarnings("unchecked")
    public static <I> I[] insert(Class<I> clazz, I[] array, int index, I... valuesToInsert) {
        I[] result = null;
        int k = 0;

        if (array == null || array.length == 0) {
            result = (I[]) Array.newInstance(clazz, valuesToInsert.length);
            for (I element : valuesToInsert) {
                result[k] = element;
                k++;
            }
            return result;
        }

        if (index < 0 || index > array.length)
            return null;

        result = (I[]) Array.newInstance(clazz, array.length + valuesToInsert.length);
        k = index;
        System.arraycopy(array, 0, result, 0, index);

        for (I element : valuesToInsert) {
            result[k] = element;
            k++;
        }

        System.arraycopy(array, index, result, index + valuesToInsert.length, array.length - index);
        return result;
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
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(byte[] arr, byte value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(byte b: arr){
            if(b == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(short[] arr, short value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(short s: arr){
            if(s == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(boolean[] arr, boolean value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(boolean b: arr){
            if(b == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(char[] arr, char value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(char ch: arr){
            if(ch == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(int[] arr, int value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(int i: arr){
            if(i == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(long[] arr, long value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(long l: arr){
            if(l == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(float[] arr, float value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(float f: arr){
            if(f == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static int indexOf(double[] arr, double value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(double d: arr){
            if(d == value) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Finds the index of the given value in the array
     *
     * @param arr the array to search
     * @param value the value to find
     * @return the index of the value if present else -1; 'null' and empty array returns -1
     */
    public static <I> int indexOf(I[] arr, I value){
        if(isEmpty(arr)) return INDEX_NOT_FOUND;
        int index = 0;
        for(I item: arr){
            if(item.equals(value)) return index;
            index++;
        }

        return INDEX_NOT_FOUND;
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
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static byte[] remove(byte[] array, int... index){
        byte[] removedArray = new byte[array.length - index.length];

        if(index.length == 1){
        	removedArray = new byte[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        removedArray = new byte[array.length - indices.size()];
        int k = 0;
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }

        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static short[] remove(short[] array, int... index){
        short[] removedArray = null;

        if(index.length == 1){
        	removedArray = new short[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        removedArray = new short[array.length - indices.size()];
        int k = 0;
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }
        return removedArray;
    }
    
    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static boolean[] remove(boolean[] array, int... index){
        boolean[] removedArray = null;

        if(index.length == 1){
        	removedArray = new boolean[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = new boolean[array.length - indices.size()];
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }
        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static char[] remove(char[] array, int... index){
        char[] removedArray = null;

        if(index.length == 1){
        	removedArray = new char[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = new char[array.length - indices.size()];
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static int[] remove(int[] array, int... index){
        int[] removedArray = new int[array.length - index.length];

        if(index.length == 1){
        	removedArray = new int[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = new int[array.length - indices.size()];
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static long[] remove(long[] array, int... index){
        long[] removedArray = new long[array.length - index.length];

        if(index.length == 1){
        	removedArray = new long[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = new long[array.length - indices.size()];
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static float[] remove(float[] array, int... index){
        float[] removedArray = null;

        if(index.length == 1){
        	removedArray = new float[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = new float[array.length - indices.size()];
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static double[] remove(double[] array, int... index){
        double[] removedArray = null;

        if(index.length == 1){
        	removedArray = new double[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = new double[array.length - indices.size()];
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    public static Object[] remove(Object[] array, int... index){
        Object[] removedArray = null;

        if(index.length == 1){
        	removedArray = new Object[array.length - 1];
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = new Object[array.length - indices.size()];
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }        return removedArray;
    }

    /**
     * Removes the element at the specified position from the specified array. All subsequent elements are shifted to
     * the left.
     *
     * Example:
     *  ArrayUtils.remove([1], 0)          = []
     *  ArrayUtils.remove([2, 6, 3], 1, 2) = [2]
     *
     * @param clazz Class type
     * @param array the array to remove the element(s) from
     * @param index the position(s) of the element to be removed
     * @param <B> type of element
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if (index < 0 || index >= array.length)
     * @return A new array containing the existing elements except the element at the specified position(s).
     */
    @SuppressWarnings("unchecked")
    public static <B> B[] remove(Class<B> clazz, B[] array, int... index){
        B[] removedArray = null;

        if(index.length == 1){
        	removedArray = (B[])Array.newInstance(clazz,array.length - 1);
            System.arraycopy(array, 0, removedArray, 0, index[0]);
            System.arraycopy(array, index[0] + 1, removedArray, index[0], array.length - index[0] - 1);
            return removedArray;
        }

        TreeSet<Integer> indices = new TreeSet<>();
        for(int element : index){
        	indices.add(element);
        }
        
        int k = 0;
        removedArray = (B[])Array.newInstance(clazz,array.length - indices.size());
        for(int j = 0; j < array.length; j++){
        	if(indices.contains(j)) continue;
            removedArray[k] = array[j]; k++;
        }        return removedArray;
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
     * Reverses the order of the given generic array.
     *
     * @param arr the array to reverse
     * @return array in reversed order; 'null' if input is 'null'
     */
    public static <R> R[] reverse(R[] arr){
        if(isEmpty(arr)) return null;
        int start = 0, end = arr.length - 1;
        R temp = null;

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
        if(isEmpty(arr)) return null;

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
        if(isEmpty(arr)) return null;

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
        if(isEmpty(arr)) return null;

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
        if(isEmpty(arr)) return null;

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
        if(isEmpty(arr)) return null;

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
        if(isEmpty(arr)) return null;

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
        if(isEmpty(arr)) return null;

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
        if(isEmpty(arr)) return null;

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
