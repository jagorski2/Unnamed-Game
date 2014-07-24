package com.mygdx.game.hex;

/**
 * @author Ian
 * 
 * 
 */
public class Math {
	
	public static float CalculateH(float side)
	{
	    return (float) (java.lang.Math.sin(DegreesToRadians(30)) * side);
	}

	public static float CalculateR(float side)
	{
	    return (float) (java.lang.Math.cos(DegreesToRadians(30)) * side);
	} 
	
	public static double DegreesToRadians(double degrees)
	{
	    return degrees * java.lang.Math.PI / 180;
	}
}
