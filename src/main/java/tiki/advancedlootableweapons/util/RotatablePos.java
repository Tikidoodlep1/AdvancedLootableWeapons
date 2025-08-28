package tiki.advancedlootableweapons.util;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector4f;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class RotatablePos {
	
	private Quaternion rot, n, q, c, r, temp;
	private float x, y, z, m, s, px, py, pz;
	private float readOnlyX, readOnlyY, readOnlyZ;
	private boolean scaled = false;
	private boolean dirty = false;
	Vector4f v;
	
	public RotatablePos(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.readOnlyX = x;
		this.readOnlyY = y;
		this.readOnlyZ = z;
		this.rot = new Quaternion();
		
		this.px = 0f;
		this.py = 0f;
		this.pz = 0f;
		
		//Variables to prevent excess garbage collector runs
		this.n = new Quaternion();
		this.q = new Quaternion();
		this.c = new Quaternion();
		this.r = new Quaternion();
		this.temp = new Quaternion();
		this.v = new Vector4f();
		this.m = 0f;
		this.s = 0f;
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
		v.set(x, y, z, (float)Math.toRadians(angleDegrees));
		this.rot.setFromAxisAngle(v);
		this.dirty = true;
		v.set(0f, 0f, 0f, 1f);
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
		this.dirty = true;
	}
	
	public void rotateAxisAngleAroundPivot(float x, float y, float z, float px, float py, float pz, float angleDegrees) {
		this.px = px;
		this.py = py;
		this.pz = pz;
		
		this.rotateAxisAngle(x, y, z, angleDegrees);
	}
	
	public void rotateEnumFacingAroundPivot(EnumFacing facing) {
		this.rotateYAroundPivot(facing.getDirectionVec().getX(), facing.getDirectionVec().getY(), facing.getDirectionVec().getZ(), facing.getHorizontalAngle());
	}
	
	public void rotateXAroundPivot(float px, float py, float pz, float angleDegrees) {
		this.rotateAxisAngleAroundPivot(1f, 0f, 0f, px, py, pz, angleDegrees);
	}
	
	public void rotateYAroundPivot(float px, float py, float pz, float angleDegrees) {
		this.rotateAxisAngleAroundPivot(0f, 1f, 0f, px, py, pz, angleDegrees);
	}

	public void rotateZAroundPivot(float px, float py, float pz, float angleDegrees) {
		this.rotateAxisAngleAroundPivot(0f, 0f, 1f, px, py, pz, angleDegrees);
	}
	
	/**
	 * This method WILL accumulate rotation data, unlike {@link RotatablePos#rotationFromAxisAngle}
	 */
	public void rotateAxisAngle(float x, float y, float z, float angleDegrees) {
		this.m = (float)Math.sqrt(x*x + y*y + z*z);
		this.s = (float)(Math.sin(0.5 * Math.toRadians(angleDegrees)) / m);
		
		this.temp.set(x*this.s, y*this.s, z*this.s, (float)Math.cos(0.5 * Math.toRadians(angleDegrees)));
		Quaternion.mul(this.rot, this.temp, this.rot);
		
		this.dirty = true;
		this.temp.setIdentity();
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
		if(!this.dirty) {
			return;
		}
		
		//Alw.logger.debug("Applying rotation in RotatablePos#applyRotation. Pos => [{}, {}, {}], Read Only Pos => [{}, {}, {}]", this.x, this.y, this.z, this.readOnlyX, this.readOnlyY, this.readOnlyZ);
		
		if(this.px > 0 || this.py > 0 || this.pz > 0) {
			this.x -= px;
			this.y -= py;
			this.z -= pz;
		}
		
		this.rot.normalise(n);
		
		q.set(this.readOnlyX, this.readOnlyY, this.readOnlyZ, 0f);
		
		n.negate(c);
		
		Quaternion.mul(c, q, r);
		Quaternion.mul(r, n, r);
		
		this.x = r.x;
		this.y = r.y;
		this.z = r.z;
		
		if(this.px > 0 || this.py > 0 || this.pz > 0) {
			this.x += px;
			this.y += py;
			this.z += pz;
		}
		
		this.dirty = false;
		
		//Alw.logger.debug("After applying rotation => [{}, {}, {}]", this.x, this.y, this.z);
	}
	
	public boolean hasPendingRotation() {
		return this.dirty;
	}
	
	/**
	 * Checks if the stored Quaternion has any applied or pending rotation data.
	 */
	public boolean hasRotation() {
		return this.rot.x != 0f || this.rot.y != 0f || this.rot.z != 0f || this.rot.w != 1f || this.dirty;
	}
	
	public void scaleAroundPivot(float sx, float sy, float sz, float px, float py, float pz) {
		this.x = px + (this.x - px) * sx;
		this.y = py + (this.y - py) * sy;
		this.z = pz + (this.z - pz) * sz;
		this.scaled = true;
	}
	
	public void scaleAroundPivot(float s, float px, float py, float pz) {
		this.scaleAroundPivot(s, s, s, px, py, pz);
	}
	
	public void scaleAroundPivotP(float sx, float sy, float sz, float p) {
		this.scaleAroundPivot(sx, sy, sz, p, p, p);
	}
	
	public void scaleAroundPivot(float s, float p) {
		this.scaleAroundPivot(s, s, s, p, p, p);
	}
	
	public void scaleAroundCenter(float sx, float sy, float sz) {
		this.scaleAroundPivot(sx, sy, sz, 0.5f, 0.5f, 0.5f);
	}
	
	public void scaleAroundCenter(float s) {
		this.scaleAroundCenter(s, s, s);
	}
	
	public void scaleXAroundCenter(float s) {
		this.scaleAroundCenter(s, 1, 1);
	}
	
	public void scaleYAroundCenter(float s) {
		this.scaleAroundCenter(1, s, 1);
	}
	
	public void scaleZAroundCenter(float s) {
		this.scaleAroundCenter(1, 1, s);
	}
	
	public void scale(float sx, float sy, float sz) {
		this.x *= sx;
		this.y *= sy;
		this.z *= sz;
		this.scaled = true;
	}
	
	public void scale(float s) {
		this.scale(s, s, s);
	}
	
	public void scaleX(float s) {
		this.scale(s, 1, 1);
	}
	
	public void scaleY(float s) {
		this.scale(1, 2, 1);
	}
	
	public void scaleZ(float s) {
		this.scale(1, 1, s);
	}
	
	public boolean isScaled() {
		return this.scaled;
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
		this.px = 0f;
		this.py = 0f;
		this.pz = 0f;
	}
}