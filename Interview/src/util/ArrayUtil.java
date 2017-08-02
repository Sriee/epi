package util;

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
     * @param arr the array to clone
     * @return the cloned array, null if input is null
     */
    public static <Z> Z[] clone(Class<Z> clazz, Z[] arr){
        if(isEmpty(arr)) return null;

        Z[] result = (Z[]) Array.newInstance(clazz, arr.length);
        System.arraycopy(arr, 0, result, 0, arr.length);

        return result;
    }
}
