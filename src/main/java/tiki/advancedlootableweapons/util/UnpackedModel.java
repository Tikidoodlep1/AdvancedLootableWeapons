package tiki.advancedlootableweapons.util;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import tiki.advancedlootableweapons.Alw;

public class UnpackedModel {

	private final ArrayList<IBakedModel> model = new ArrayList<IBakedModel>();
	private final ArrayList<IBlockState> state = new ArrayList<IBlockState>();
	private final ArrayList<ArrayList<UnpackedFace>> faces = new ArrayList<ArrayList<UnpackedFace>>();
	
	public UnpackedModel(IBakedModel model, IBlockState state) {
		this.model.add(model);
		this.state.add(state);
		
		for(BakedQuad quad : model.getQuads(state, null, 0L)) {
			int[] vertexData = quad.getVertexData();
			
			UnpackedVertex[] vertices = new UnpackedVertex[4];
			
			//4 vertices for each quad
			for(int i = 0; i < 4; i++) {
				int vertex = i * 7;
				
				vertices[i] = new UnpackedVertex(
						Float.intBitsToFloat(vertexData[vertex]), //x
						Float.intBitsToFloat(vertexData[vertex + 1]), //y
						Float.intBitsToFloat(vertexData[vertex + 2]), //z
						vertexData[vertex+3],
						Float.intBitsToFloat(vertexData[vertex + 4]), //u
						Float.intBitsToFloat(vertexData[vertex + 5]), //v
						vertexData[vertex+6]
					);
			}
			
			if(faces.size() < 1) {
				faces.add(new ArrayList<UnpackedFace>());
			}
			faces.get(0).add(new UnpackedFace(quad.getFace(), vertices));
		}
	}
	
	public UnpackedModel(IBakedModel[] models, IBlockState[] states) {
		if(models.length != states.length) {
			Alw.logger.error(new IllegalArgumentException("Models and States length must match. Received models of length " + models.length + " and states of length " + states.length + "."));
		}
		
		int len = models.length > states.length ? states.length : models.length;
		for(int i = 0; i < len; i++) {
			this.model.add(models[i]);
			this.state.add(states[i]);
			
			for(BakedQuad quad : models[i].getQuads(states[i], null, 0L)) {
				int[] vertexData = quad.getVertexData();
				
				UnpackedVertex[] vertices = new UnpackedVertex[4];
				
				//4 vertices for each quad
				for(int j = 0; j < 4; j++) {
					int vertex = j * 7;
					
					vertices[j] = new UnpackedVertex(
							Float.intBitsToFloat(vertexData[vertex]), //x
							Float.intBitsToFloat(vertexData[vertex + 1]), //y
							Float.intBitsToFloat(vertexData[vertex + 2]), //z
							vertexData[vertex+3],
							Float.intBitsToFloat(vertexData[vertex + 4]), //u
							Float.intBitsToFloat(vertexData[vertex + 5]), //v
							vertexData[vertex+6]
						);
				}
				
				while(faces.size() < i+1) {
					faces.add(new ArrayList<UnpackedFace>());
				}
				faces.get(i).add(new UnpackedFace(quad.getFace(), vertices));
			}
		}
	}
	
	public IBakedModel getModel() {
		return this.model.get(0);
	}
	
	/**
	 * @param group
	 * A zero-indexed grouping for UnpackedFace groupings
	 */
	public IBakedModel getModel(int group) {
		return this.model.get(group);
	}

	public IBlockState getState() {
		return this.state.get(0);
	}

	/**
	 * @param group
	 * A zero-indexed grouping for UnpackedFace groupings
	 */
	public IBlockState getState(int group) {
		return this.state.get(group);
	}
	
	public ArrayList<UnpackedFace> getFaces() {
		return faces.get(0);
	}

	/**
	 * @param group
	 * A zero-indexed grouping for UnpackedFace groupings
	 */
	public ArrayList<UnpackedFace> getFaces(int group) {
		return faces.get(group);
	}

	//=============================================================================================================================================
	//																SUBCLASSES
	//=============================================================================================================================================
	
	public class UnpackedFace {
		
		private UnpackedVertex[] vertices = new UnpackedVertex[4];
		private RotatablePos from = null;
		private RotatablePos to = null;
		private float minU = Float.NaN;
		private float minV = Float.NaN;
		private float maxU = Float.NaN;
		private float maxV = Float.NaN;
		private int avgLight = Integer.MAX_VALUE;
		private int avgColor = Integer.MAX_VALUE;
		private EnumFacing face;
		
		public UnpackedFace(EnumFacing face, UnpackedVertex... vertices) {
			if(vertices.length != 4) {
				Alw.logger.error(new IllegalArgumentException("4 vertices must be passed to the UnpackedFace. No more, no less. Received length " + vertices.length));
			}
			
			for(int i = 0; i < this.vertices.length; i++) {
				this.vertices[i] = vertices[i];
			}
			Arrays.sort(this.vertices);
			this.face = face;
		}

		public UnpackedVertex[] getVertices() {
			return vertices;
		}
		
		public RotatablePos getFrom() {
			if(this.from != null) {
				return this.from;
			}
			
			this.from = new RotatablePos();
			
			float minX = this.vertices[0].x;
			for(int i = 1; i < vertices.length; i++) {
				if(this.vertices[i].x < minX) {
					minX = vertices[i].x;
				}
			}
			
			float minY = this.vertices[0].y;
			for(int i = 1; i < vertices.length; i++) {
				if(this.vertices[i].y < minY) {
					minY = vertices[i].y;
				}
			}
			
			float minZ = this.vertices[0].z;
			for(int i = 1; i < vertices.length; i++) {
				if(this.vertices[i].z < minZ) {
					minZ = vertices[i].z;
				}
			}
			
			Alw.logger.info("Creating <From> Coordinates for Face " + Arrays.toString(this.vertices));
			
			this.from.setPos(minX, minY, minZ);
			return this.from;
		}
		
		public RotatablePos getTo() {
			if(this.to != null) {
				return this.to;
			}
			
			this.to = new RotatablePos();
			
			float maxX = this.vertices[0].x;
			for(int i = 1; i < vertices.length; i++) {
				if(this.vertices[i].x > maxX) {
					maxX = this.vertices[i].x;
				}
			}
			
			float maxY = this.vertices[0].y;
			for(int i = 1; i < vertices.length; i++) {
				if(this.vertices[i].y > maxY) {
					maxY = this.vertices[i].y;
				}
			}
			
			float maxZ = this.vertices[0].z;
			for(int i = 1; i < vertices.length; i++) {
				if(this.vertices[i].z > maxZ) {
					maxZ = this.vertices[i].z;
				}
			}
			
			Alw.logger.info("Creating <To> Coordinates for Face " + Arrays.toString(this.vertices));
			
			this.to.setPos(maxX, maxY, maxZ);
			return this.to;
		}
		
		public float getMinU() {
			if(!Float.isNaN(this.minU)) {
				return this.minU;
			}
			
			//Set the target vertex based on the facing value
			float x, y, z;
			switch(this.face) {
			case NORTH:
				x = this.getFrom().getX();
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMin is present on both for NORTH
				z = this.getFrom().getZ();
				break;
			case DOWN:
				x = this.getFrom().getX();
				y = this.getFrom().getY(); 
				z = this.getFrom().getZ(); //This coord doesn't actually matter - uMin is present on both for DOWN
				break;
			case EAST:
				x = this.getTo().getX();
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMin is present on both for NORTH
				z = this.getFrom().getZ();
				break;
			case SOUTH:
				x = this.getTo().getX(); 
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMin is present on both for SOUTH
				z = this.getTo().getZ();
				break;
			case UP:
				x = this.getFrom().getX();
				y = this.getTo().getY(); //This coord doesn't actually matter - uMin is present on both for NORTH
				z = this.getFrom().getZ();
				break;
			case WEST:
				x = this.getFrom().getX();
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMin is present on both for WEST
				z = this.getTo().getZ();
				break;
			default:
				x = Float.NaN;
				y = Float.NaN;
				z = Float.NaN;
			}
			
			for(int i = 0; i < vertices.length; i++) {
				if(this.vertices[i].x  == x && this.vertices[i].y  == y && this.vertices[i].z  == z) {
					this.minU = this.vertices[i].u;
				}
			}
			return this.minU;
		}
		
		public float getMinV() {
			if(!Float.isNaN(this.minV)) {
				return this.minV;
			}
			
			//Set the target vertex based on the facing value
			float x, y, z;
			switch(this.face) {
			case NORTH:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMin is present on both for NORTH
				y = this.getFrom().getY();
				z = this.getFrom().getZ();
				break;
			case DOWN:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMin is present on both for DOWN
				y = this.getFrom().getY(); 
				z = this.getTo().getZ();
				break;
			case EAST:
				x = this.getTo().getX();
				y = this.getFrom().getY();
				z = this.getFrom().getZ(); //This coord doesn't actually matter - vMin is present on both for NORTH
				break;
			case SOUTH:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMin is present on both for SOUTH
				y = this.getFrom().getY();
				z = this.getTo().getZ();
				break;
			case UP:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMin is present on both for NORTH
				y = this.getTo().getY();
				z = this.getFrom().getZ();
				break;
			case WEST:
				x = this.getFrom().getX();
				y = this.getFrom().getY();
				z = this.getFrom().getZ(); //This coord doesn't actually matter - vMin is present on both for WEST
				break;
			default:
				x = Float.NaN;
				y = Float.NaN;
				z = Float.NaN;
			}
			
			for(int i = 0; i < vertices.length; i++) {
				if(this.vertices[i].x  == x && this.vertices[i].y  == y && this.vertices[i].z  == z) {
					this.minV = this.vertices[i].v;
				}
			}
			return this.minV;
		}
		
		public float getMaxU() {
			if(!Float.isNaN(this.maxU)) {
				return this.maxU;
			}
			
			//Set the target vertex based on the facing value
			float x, y, z;
			switch(this.face) {
			case NORTH:
				x = this.getTo().getX();
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMax is present on both for NORTH
				z = this.getFrom().getZ();
				break;
			case DOWN:
				x = this.getTo().getX();
				y = this.getFrom().getY(); 
				z = this.getFrom().getZ(); //This coord doesn't actually matter - uMax is present on both for DOWN
				break;
			case EAST:
				x = this.getTo().getX();
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMax is present on both for NORTH
				z = this.getTo().getZ();
				break;
			case SOUTH:
				x = this.getFrom().getX();
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMax is present on both for SOUTH
				z = this.getTo().getZ();
				break;
			case UP:
				x = this.getTo().getX();
				y = this.getTo().getY();
				z = this.getFrom().getZ(); //This coord doesn't actually matter - uMax is present on both for NORTH
				break;
			case WEST:
				x = this.getFrom().getX();
				y = this.getFrom().getY(); //This coord doesn't actually matter - uMax is present on both for WEST
				z = this.getFrom().getZ();
				break;
			default:
				x = Float.NaN;
				y = Float.NaN;
				z = Float.NaN;
			}
			
			for(int i = 0; i < vertices.length; i++) {
				if(this.vertices[i].x  == x && this.vertices[i].y  == y && this.vertices[i].z  == z) {
					this.maxU = this.vertices[i].u;
				}
			}
			return this.maxU;
		}
		
		public float getMaxV() { // Returning Float.NaN - No vertex matches from and to vertices - are they being made incorrectly?
			if(!Float.isNaN(this.maxV)) {
				return this.maxV;
			}
			
			//Set the target vertex based on the facing value
			float x, y, z;
			switch(this.face) {
			case NORTH:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMax is present on both for NORTH
				y = this.getFrom().getY();
				z = this.getFrom().getZ();
				break;
			case DOWN:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMax is present on both for NORTH
				y = this.getFrom().getY(); 
				z = this.getTo().getZ();
				break;
			case EAST:
				x = this.getTo().getX();
				y = this.getFrom().getY();
				z = this.getFrom().getZ(); //This coord doesn't actually matter - vMax is present on both for NORTH
				break;
			case SOUTH:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMax is present on both for NORTH
				y = this.getFrom().getY();
				z = this.getTo().getZ();
				break;
			case UP:
				x = this.getFrom().getX(); //This coord doesn't actually matter - vMax is present on both for NORTH
				y = this.getTo().getY();
				z = this.getFrom().getZ();
				break;
			case WEST:
				x = this.getFrom().getX();
				y = this.getFrom().getY();
				z = this.getFrom().getZ(); //This coord doesn't actually matter - vMax is present on both for NORTH
				break;
			default:
				x = Float.NaN;
				y = Float.NaN;
				z = Float.NaN;
			}
			
			for(int i = 0; i < vertices.length; i++) {
				if(this.vertices[i].x  == x && this.vertices[i].y  == y && this.vertices[i].z  == z) {
					this.maxV = this.vertices[i].v;
				}
			}
			return this.maxV;
		}
		
		public int getAvgLight() {
			if(this.avgLight != Integer.MAX_VALUE) {
				return this.avgLight;
			}
			
			int sky = 0;
			int block = 0;
			for(UnpackedVertex v : this.vertices) {
				sky += (v.lightmap >> 16) & 0xFFFF;
				block += v.lightmap & 0xFFFF;
			}
			
			sky /= this.vertices.length;
			block /= this.vertices.length;
			this.avgLight = (sky << 16) | block;
			return this.avgLight;
		}
		
		public int getAvgColor() {
			if(this.avgColor != Integer.MAX_VALUE) {
				return this.avgColor;
			}
			
			int a = 0;
			int r = 0;
			int g = 0;
			int b = 0;
			for(UnpackedVertex v : this.vertices) {
				a += (v.color >> 24) & 0xFF;
				r += (v.color >> 16) & 0xFF;
				g += (v.color >> 8) & 0xFF;
				b += v.color & 0xFF;
			}
			a /= this.vertices.length;
			r /= this.vertices.length;
			g /= this.vertices.length;
			b /= this.vertices.length;
			this.avgColor = (a << 24) | (r << 16) | (g << 8) | b;
			return this.avgColor;
		}
		
		public EnumFacing getFace() {
			return face;
		}
		
	}
	
	public class UnpackedVertex implements Comparable<UnpackedVertex> {
		
		private float x, y, z, u, v;
		private int color, lightmap;
		
		public UnpackedVertex(float x, float y, float z, int color, float u, float v, int lightmap) {
	        this.x = x;
	        this.y = y;
	        this.z = z;
	        
	        this.color = color;
	        
	        this.u = u;
	        this.v = v;
	        
	        this.lightmap = lightmap;
	    }

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getZ() {
			return z;
		}
		
		public int getColor() {
			return color;
		}

		public float getU() {
			return u;
		}

		public float getV() {
			return v;
		}

		public int getLightmap() {
			return lightmap;
		}
		
		@Override
		public int compareTo(UnpackedVertex o) {
			float dif = (this.x - o.x) + (this.y - o.y) + (this.z - o.z);
			if(dif > 0) {
				return 1;
			}else if(dif < 0) {
				return -1;
			}
			
			return 0;
		}
		
		@Override
		public String toString() {
			return "Unpacked Vertex: <\n"
					+ "\t\tPos: {" + this.x + ", " + this.y + ", " + this.z + "}\n"
					+ "\t\tRGBA Color: {" + ((this.color >> 16) & 0xFF) + ", " + ((this.color >> 8) & 0xFF) + ", " + (this.color & 0xFF) + ", " 
						+ ((this.color >> 24) & 0xFF) + "}\n"
					+ "\t\tUV: {" + this.u + ", " + this.v + "}\n"
					+ "\t\tLightmap (Sky, block): {" + ((this.lightmap >> 16) & 0xFFFF) + ", " + (this.lightmap & 0xFFFF) + "}\n"
					+ ">";
		}

	}
}
