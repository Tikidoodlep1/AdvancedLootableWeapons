package tiki.advancedlootableweapons.util;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector4f;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class RotatablePos {
	
	private Quaternion rot;
	private float x, y, z;
	private float readOnlyX, readOnlyY, readOnlyZ;
	
	public RotatablePos(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rot = new Quaternion();
	}
	
	public RotatablePos(BlockPos pos) {
		this(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public RotatablePos() {
		this(0f, 0f, 0f);
	}
	
	public static RotatablePos from(RotatablePos other) {
		RotatablePos newPos = new RotatablePos(other.x, other.y, other.z);
		newPos.rot = other.rot;
		return newPos;
	}
	
	public void to(RotatablePos other) {
		other.x = this.x;
		other.y = this.y;
		other.z = this.z;
		other.readOnlyX = this.readOnlyX;
		other.readOnlyY = this.readOnlyY;
		other.readOnlyZ = this.readOnlyZ;
		other.rot = this.rot;
	}
	
	public void setPos(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.readOnlyX = x;
		this.readOnlyY = y;
		this.readOnlyZ = z;
	}
	
	public void setX(float x) {
		this.x = x;
		this.readOnlyX = x;
	}
	
	public void setY(float y) {
		this.y = y;
		this.readOnlyY = y;
	}
	
	public void setZ(float z) {
		this.z = z;
		this.readOnlyZ = z;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getZ() {
		return this.z;
	}
	
	public Quaternion getRotation() {
		return new Quaternion(this.rot);
	}
	
	/**
	 * Important to note: This method overwrites any existing data the Quaternion stored before calling it. This method will not accumulate rotation data.
	 * If you want to accumulate rotation data, use {@link RotatablePos#rotateAxisAngle}
	 */
	public void rotationFromAxisAngle(float x, float y, float z, float angleDegrees) {
		this.rot.setFromAxisAngle(new Vector4f(x, y, z, (float)Math.toRadians(angleDegrees)));
	}
	
	/**
	 * Important to note: This method overwrites any existing data the Quaternion stored before calling it. This method will not accumulate rotation data.
	 * If you want to accumulate rotation data, use {@link RotatablePos#rotateEnumFacing}
	 * 
	 * @param facing
	 * The {@link EnumFacing} value to create a rotation from. {@link EnumFacing#NORTH} creates an identity Quaternion, {@link EnumFacing#SOUTH} is rotated 180 degrees around the Y-axis.
	 */
	public void rotationFromEnumFacing(EnumFacing facing) {
		rotationFromAxisAngle(0f, 1f, 0f, facing.getHorizontalAngle());
	}
	
	/**
	 * This method WILL accumulate rotation data, unlike {@link RotatablePos#rotationFromAxisAngle}
	 */
	public void rotateAxisAngle(float x, float y, float z, float angleDegrees) {
		Quaternion.mul(this.rot, new Quaternion(x, y, z, (float)Math.toRadians(angleDegrees)), this.rot);
	}
	
	/**
	 * This method WILL accumulate rotation data, unlike {@link RotatablePos#rotationFromAxisAngle}
	 * 
	 * @param facing
	 * The {@link EnumFacing} value to create a rotation from. {@link EnumFacing#NORTH} creates an identity Quaternion, {@link EnumFacing#SOUTH} is rotated 180 degrees around the Y-axis.
	 */
	public void rotateEnumFacing(EnumFacing facing) {
		this.rotateY(facing.getHorizontalAngle());
	}
	
	/**
	 * This method WILL accumulate rotation data, unlike {@link RotatablePos#rotationFromAxisAngle}
	 * This method also only rotates around the x-axis.
	 * See {@link RotatablePos#rotateY} and {@link RotatablePos#rotateZ}
	 */
	public void rotateX(float angleDegrees) {
		this.rotateAxisAngle(1f, 0f, 0f, angleDegrees);
	}
	
	/**
	 * This method WILL accumulate rotation data, unlike {@link RotatablePos#rotationFromAxisAngle}
	 * This method also only rotates around the y-axis.
	 * See {@link RotatablePos#rotateX} and {@link RotatablePos#rotateZ}
	 */
	public void rotateY(float angleDegrees) {
		this.rotateAxisAngle(0f, 1f, 0f, angleDegrees);
	}
	
	/**
	 * This method WILL accumulate rotation data, unlike {@link RotatablePos#rotationFromAxisAngle}
	 * This method also only rotates around the z-axis.
	 * See {@link RotatablePos#rotateX} and {@link RotatablePos#rotateY}
	 */
	public void rotateZ(float angleDegrees) {
		this.rotateAxisAngle(0f, 0f, 1f, angleDegrees);
	}
	
	/**
	 * This method must be called after all rotations are applied. This will set the x, y, and z coordinates based on the rotation stored in the quaternion.
	 */
	public void applyRotation() {
		Quaternion normalized = new Quaternion();
		this.rot.normalise(normalized);
		
		Quaternion vectorQuat = new Quaternion(this.x, this.y, this.z, 0f);
		
		Quaternion conjugate = new Quaternion();
		this.rot.negate(conjugate);
		
		Quaternion result = new Quaternion();
		Quaternion.mul(normalized, vectorQuat, result);
		Quaternion.mul(result, conjugate, result);
		
		this.x = result.x;
		this.y = result.y;
		this.z = result.z;
	}
	
	/**
	 * This method DOES NOT reset the rotation stored in the Quaternion!
	 * If you want to reset the Quaternion as well, see {@link RotatablePos#clean}
	 */
	public void reset() {
		this.x = this.readOnlyX;
		this.y = this.readOnlyY;
		this.z = this.readOnlyZ;
	}
	
	/**
	 * This method resets the working position and rotation stored in the Quaternion.
	 * This method DOES NOT reset the original x, y, and z values used to restore original position.
	 */
	public void clean() {
		this.reset();
		this.rot.setIdentity();
	}
}