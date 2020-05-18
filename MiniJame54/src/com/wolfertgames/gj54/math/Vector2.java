package com.wolfertgames.gj54.math;

public class Vector2 {
	
	public float x, y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	public Vector2 add(Vector2 v) {
		this.x += v.x;
		this.y += v.x;
		return this;
	}
	
	public float dot(Vector2 v) {
		return this.x * v.x + this.y * v.y;
	}
	
	public void zero() {
		this.x = 0;
		this.y = 0;
	}
	
	public void scale(float s) {
		this.x *= s;
		this.y *= s;
	}
}
