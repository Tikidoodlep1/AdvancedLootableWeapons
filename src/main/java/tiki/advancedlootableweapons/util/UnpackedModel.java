package tiki.advancedlootableweapons.util;

import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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
						(vertexData[vertex+6] >> 16) & 0xFFFF,
						vertexData[vertex+6] & 0xFFFF
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
				TextureAtlasSprite sprite = quad.getSprite();
				
				//4 vertices for each quad
				for(int j = 0; j < 4; j++) {
					int vertex = j * 7;
					
					vertices[j] = new UnpackedVertex(
							Float.intBitsToFloat(vertexData[vertex]), //x
							Float.intBitsToFloat(vertexData[vertex + 1]), //y
							Float.intBitsToFloat(vertexData[vertex + 2]), //z
							vertexData[vertex+3],
							((Float.intBitsToFloat(vertexData[vertex + 4]) - sprite.getMinU()) / (sprite.getMaxU() - sprite.getMinU())) * 16f, //u
							((Float.intBitsToFloat(vertexData[vertex + 5]) - sprite.getMinV()) / (sprite.getMaxV() - sprite.getMinV())) * 16f, //v
							(vertexData[vertex+6] >> 16) & 0xFFFF,
							vertexData[vertex+6] & 0xFFFF
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
		private EnumFacing face;
		
		public UnpackedFace(EnumFacing face, UnpackedVertex... vertices) {
			if(vertices.length != 4) {
				Alw.logger.error(new IllegalArgumentException("4 vertices must be passed to the UnpackedFace. No more, no less. Received length " + vertices.length));
			}
			
			for(int i = 0; i < this.vertices.length; i++) {
				this.vertices[i] = vertices[i];
			}
			
			this.face = face;
		}

		public UnpackedVertex[] getVertices() {
			return vertices;
		}
		
		public EnumFacing getFace() {
			return face;
		}
		
	}
	
	public class UnpackedVertex implements Comparable<UnpackedVertex> {
		
		private final RotatablePos pos;
		private final float x, y, z, u, v, skyLight, blockLight;
		private final int color;
		
		public UnpackedVertex(float x, float y, float z, int color, float u, float v, float skyLight, float blockLight) {
			this.pos = new RotatablePos(x, y, z);
	        this.x = x;
	        this.y = y;
	        this.z = z;
	        
	        this.color = color;
	        
	        this.u = u;
	        this.v = v;
	        
	        this.skyLight = skyLight;
	        this.blockLight = blockLight;
	    }
		
		public RotatablePos getPos() {
			return this.pos;
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
		
		public float getSkyLight() {
			return this.skyLight;
		}
		
		public float getBlockLight() {
			return this.blockLight;
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
			return "\nUnpacked Vertex: <Pos: {" + this.x + ", " + this.y + ", " + this.z + "}\n"
					+ "----RGBA Color: {" + ((this.color >> 16) & 0xFF) + ", " + ((this.color >> 8) & 0xFF) + ", " + (this.color & 0xFF) + ", " 
						+ ((this.color >> 24) & 0xFF) + "}\n"
					+ "----UV: {" + this.u + ", " + this.v + "}\n"
					+ "----Lightmap (Sky, block): {" + skyLight + ", " + blockLight + "}>";
		}

	}
}
